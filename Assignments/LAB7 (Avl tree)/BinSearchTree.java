/*  Binary Search Trees  

Requires Tree.java, BstGraph.java, to be extended by:

class ExtBst<T extends Comparable<T>&java.io.Serializable> extends BinSearchTree<T>

The "Serializable" interface is Java's way of writing objects in binary form
to a file or socket (generalized into an "OutputStream").  
*/
    
import java.util.Comparator; // more flexible than Comparable
import java.util.function.Consumer;
import java.io.Serializable;

/*  Recall in Tree.java
interface Tree<T>
{
    boolean empty();
    int height();  // height of tree
    T search(T x); // search for something .compareTo(x)==0
    Tree<T> insert(T x); // insert into tree, usages: t = t.insert(x);
    Tree<T> delete(T x); // search and delete x from tree, t=t.delete(x);
    int size();
}// Tree interace, designed to have two subclasses (nil and vertex)
*/
    
public class BinSearchTree<T extends Comparable<? super T> & Serializable> implements Serializable
{
    //// Inner classes ////
    // The benefit of writing these classes nested inside BinSearchTree is that
    // they could refer to T, as well as cmp

    class nil implements Tree<T>, Serializable
    {
	public boolean empty() { return true; }
	public int height() { return 0; }
	public T search(T x) { return null; } // null means not found
	public Tree<T> insert(T x)
	{
	    count++; // records actual vertex added to BinSearchTree object
	    return new vertex(x,this,this);
	} // reuse nil instance
	public Tree<T> delete(T x) { return this; } // nothing to delete
	public int size() { return 0; }
	@Override
	public String toString() { return ""; }
        @Override
        public void map_inorder(Consumer<? super T> f) {}
	@Override
	public boolean isBst(T min, T max) { return true; }
    }//nil inner class

    class vertex implements Tree<T>, Serializable
    {
	T item;  // data stored at this vertex	
	Tree<T> left;   // left subtree
	Tree<T> right;  // right subtree
	int height; 
	vertex(T x, Tree<T> l, Tree<T> r) //constructor
	{ item = x;  left=l;  right=r;  setheight();}

	int setheight() // sets height, O(1) time, returns height diff
	{
	    int lh = left.height(), rh = right.height();
	    height = Math.max(lh,rh)+1;
	    return lh - rh; // difference between left and right heights
	}

	// this method should be overridden to do more.
	void adjust() // called after destructive changes to tree
	{
	    setheight();  // default adjust resets height variable
	}

	public int height() { return height; }
	public boolean empty() { return false; }	
        public int size() { return left.size() + right.size() + 1; }
	@Override
	public String toString() // do in-order traversal
	{ return left+" "+item+" "+right; } // auto calls left.toString...
        @Override
        public void map_inorder(Consumer<? super T> f)
        {
            left.map_inorder(f);
            f.accept(item);
            right.map_inorder(f);
        }
	@Override
	public boolean isBst(T min, T max)
	{
	    return (min==null || cmp.compare(min,item)<0)
		&& (max==null || cmp.compare(item,max)<0)
		&& left.isBst(min,item) && right.isBst(item,max);
        }
        
	public T search(T x)
	{
	    int c = cmp.compare(x,item);  // similar to x.compareTo(item)
	    //	    return c==0 || (c<0 && left.search(x)) || (c>0 && right.search(x));
	    if (c==0) return item;
	    else if (c<0) return left.search(x);
	    else return right.search(x);
	}
	public Tree<T> insert(T x)
	{
	    int c = cmp.compare(x,item);
	    if (c<0) left=left.insert(x);
	    else if (c>0) right=right.insert(x);
	    else if (c==0) item=x; // replace item with x
	    adjust();
	    return this; // change to this is destructive
	}//insert
	public Tree<T> delete(T x)
	{
	    int c = cmp.compare(x,item); // find x first
	    if (c<0) left=left.delete(x);
	    else if (c>0) right=right.delete(x);
	    else {   // found it, replace with largest item on left tree:
		count--; // affects wrapper object
		if (left.empty()) return right; // just move up;
		else left=((vertex)left).delmax(this); // call auxillary
	    }
	    adjust();
	    return this;
	}//delete

	protected Tree<T> delmax(vertex modnode)
	{
	    if (!right.empty()) right = ((vertex)right).delmax(modnode);
	    else {  // this node has no right child, so item is largest
		modnode.item = this.item; // replace delete with new item
		return left; // there could still be a left subtree beneath
	    }
	    adjust();
	    return this;
	}// delmax
    }// vertex inner class

    //////////////////////// OUTER CLASS COMPONENTS ///////////////////
    //////////////// instance variables of outer class:
    class defaultcmp implements Comparator<T>, Serializable
    {
	public int compare(T x, T y) { return x.compareTo(y); }
    }
    protected Comparator<T> cmp = new defaultcmp();
	//(x,y)->x.compareTo(y); // lambdas are not serializable
    protected Tree<T> root; // root of entire tree (nil or vertex)
    protected int count=0; // size of bst (named to distinguish with size())

    /////////////// Outer class methods

    public BinSearchTree() { root = new nil(); } //constructor creates empty tree
    public int size() { return count; }
    Tree<T> root() { return root; } // for BstGraph
    public int depth() { return root.height(); }
    public void setComparator(Comparator<T> c)
    {
	if (c!=null && count<2) cmp = c;
    }
    public boolean add(T x) // returns true if add successful
    {
	if (x==null) throw new RuntimeException("invalid arg");
	int c = count; // previous count
	root = root.insert(x);
	return c+1==count;
    }    
    public boolean remove(T x) // returns true if tree changed
    {
	if (x==null) throw new RuntimeException("invalid arg");	
	int c = count;
	root = root.delete(x);
	return c-1==count;
    }
    public boolean contains(T x)
    {
	if (x==null) throw new RuntimeException("invalid arg");		
	return null != root.search(x);
    }

    public void map_inorder(Consumer<? super T> f) {root.map_inorder(f);}

    @Override
    public String toString() { return root.toString(); }
    public boolean checkbst() { return root.isBst(null,null); }

    ////// main for testing
    
    public static void main(String[] av)
    {
	int n = 1000;
	BinSearchTree<Integer> tree = new BinSearchTree<Integer>();
	tree.setComparator( (x,y)->y.compareTo(x) ); // invert comparator
	while(n-->0) tree.add((int)(Math.random()*n*n));
	System.out.println(tree);
	System.out.println("size: "+tree.size());  // 1000 - duplicates
	BstGraph W = new BstGraph(1024,768); 
	W.draw(tree);
    }//main
    
}// BinSearchTree outer class

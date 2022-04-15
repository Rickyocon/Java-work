class ExtBst<T extends Comparable<? super T> & java.io.Serializable> extends BinSearchTree<T> implements java.io.Serializable
{

  class extnil extends nil
  {  //.. new/different methods
      // must include following:  ///////////////////////
      public Tree<T> insert(T x)
      { count++; return new extvertex(x,this,this); }
  }

  class extvertex extends vertex
  {
    public extvertex(T x, Tree<T> l, Tree<T> r) //constructor
    { super(x,l,r); }
    // .. new/different instance variables and methods
  }

  //// .. new/different instance variables and methods of the outer class
  public ExtBst() { root = new extnil(); } //constructor should look like this
  // ..

}// outer ExtBst class

public class treelab
{
    public static void main(String[] argv)
    {
	BstGraph W = new BstGraph(1024,768);  // graphical display of tree
        ExtBst<Integer> mytree = new ExtBst<Integer>();
        // do something with mytree
        W.draw(mytree); // graphically render tree

	// perform some other tests.
    }//main
}//treelab class
/*
class ExtBst<T extends Comparable<? super T> & java.io.Serializable> extends BinSearchTree<T> implements java.io.Serializable
{

  class extnil extends nil
  {  //.. new/different methods
      // must include following:  ///////////////////////
      public Tree<T> insert(T x)
      { count++; return new extvertex(x,this,this); }
      public Tree<T> makeclone() 
      {
        return this; 
      }
  }

  class extvertex extends vertex
  {
    public extvertex(T x, Tree<T> l, Tree<T> r) //constructor
    { super(x,l,r); }
    // .. new/different instance variables and methods
    public Tree<T> makeclone() 
    {
        return new extvertex(item, left.makeclone(), right.makeclone());
    }


    public void LL()
    {
        extvertex x = (extvertex) root;
        if(x.left.empty())
        {
            return;
        }
        else
        {
            extvertex y = (extvertex) x.left;
            extvertex r = (extvertex) x.right;
            extvertex ll = (extvertex) y.left;
            extvertex lr = (extvertex) y.right;
            r.right = r;
            r.left = lr;
            r = x;
            x = y;
            y = ll;
        }
    }//LL

    public void RR()
    {
        extvertex x = (extvertex) root;
        if(x.right.empty())
        {
            return;
        }
        else
        {
            extvertex y = (extvertex) x.right;
            extvertex r = (extvertex) x.left;
            extvertex rr = (extvertex) y.right;
            extvertex rl = (extvertex) y.left;
            r.left = r;
            r.right = rl;
            r = x;
            x = y;
            y = rr;
        }
    }//RR

    public void deterRot()
    {
        if(right.height() - left.height() > 1)
        {
            //apply LL or LR
        }
        else
        {
            //apply RR or rl
        }
        return;
    }

    @Override
    void adjust() {
        // TODO Auto-generated method stub

        super.adjust();
    }

  }

  //// .. new/different instance variables and methods of the outer class
  public ExtBst() { root = new extnil(); } //constructor should look like this
  // ..
  public ExtBst<T> clonetree() 
  {
        return (ExtBst<T>) root.makeclone();
  }

  public T smallest() 
  {
        if(root.empty()) {return null;}
        else
        {
            extvertex move = (extvertex)root;
            while(!(move.left.empty()))
            {
                move = (extvertex)move.left;
            }
            return move.item;
        }
        

  }

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

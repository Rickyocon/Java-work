import java.util.Scanner;
import java.awt.event.WindowEvent;
public class avltest
{
  public static void main(String[] av)
    {
	int n = 10;
        if (av.length>0) n = Integer.parseInt(av[0]);
        BinSearchTree<Integer> tree;
	tree = new ExtBst<Integer>(); // MAY HAVE TO CHANGE CLASS NAME
	//tree.setComparator( (x,y)->y.compareTo(x) ); // invert comparator
	while(n-->0) tree.add((int)(Math.random()*n*n));
	System.out.println(tree);  // prints in-order
	BstGraph W = new BstGraph(1024,768);
	W.draw(tree);

        // interactive loop
        Scanner scin = new Scanner(System.in);
        String req = "";  // user request
        do {
            System.out.print("add n or del n or quit: ");
            req = scin.next(); // read next token
            if (req.equals("add")) tree.add(scin.nextInt());
            else if (req.equals("del")) tree.remove(scin.nextInt());
            W.draw(tree);
        } while (req.equals("add") || req.equals("del"));
	System.out.println("size: "+tree.size()); 
        System.out.println("height: "+tree.depth());
        System.out.println("is binary search tree: "+tree.checkbst());
        W.dispatchEvent(new WindowEvent(W, WindowEvent.WINDOW_CLOSING));
    }//main
}//avltest
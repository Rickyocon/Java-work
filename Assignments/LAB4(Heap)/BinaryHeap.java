// implementation of PriorityQueue interface
import java.util.Comparator;
public class BinaryHeap<T extends Comparable<? super T>> implements PriorityQueue<T>
{
    public static final int default_cap = 16;
    protected int size;
    protected Comparator<T> cmp;
    protected T[] H;

    protected T[] makearray(int n) // need to override in subclass
    {
	return (T[]) new Comparable[n];
    }

    // constructor allows selection of max (true) or min (false) heap
    public BinaryHeap(int cap, boolean maxmin) // constructor
    {
	if (cap<1) cap=default_cap;
        H = makearray(cap);
	size = 0;
	cmp = (x,y) -> {
	    int c = x.compareTo(y);
	    if (maxmin) return c; else return -1*c;
	};
    }
    public BinaryHeap() // default constructor
    {  this(default_cap,true); }
    public int size() {return size; }
    public int current_capacity() { return H.length; }
    public void setComparator(Comparator<T> c)
    { if (c!=null) cmp = c; }
    
    protected int left(int i) {return 2*i+1;}
    protected int right(int i) {return 2*i+2;}
    protected int parent(int i) {return (i-1)/2; }

    public void resize() // double capacity
    {
	T[] H2 = makearray(H.length*2);
	for(int i=0;i<size;i++) H2[i]=H[i];
	H = H2;
    }

    protected void swap(int i, int k) // swap heap values at H[i] and H[k]
    { T tmp = H[i]; H[i]=H[k]; H[k]=tmp; }	

    // assuming maxheap, parent larger than children
    // generic swapup procedure, returns index of final value
    protected int swapup(int i) // swap up value at index i
    {
	if (i<0 || i>=size) throw new RuntimeException("invalid index "+i);
	int pi = parent(i);
	while (i>0 && cmp.compare(H[i],H[pi])>0) 
        {
	    swap(i,pi);
	    i = pi;  pi = parent(pi);
	}
	return i;
    }//swapup
    
    protected int swapdown(int i) // swap downwards, return end index
    {
	if (i<0 || i>=size) throw new RuntimeException("invalid index "+i);
	boolean stop = false;
	int ci = 0;  // candidate swap index, -1 means no more swaps possible
	while (ci != -1) 
	{
            ci = -1; // candidate swap index
	    int li = left(i), ri = right(i);
	    if (li<size && cmp.compare(H[i],H[li])<0) ci = li;
	    if (ri<size && cmp.compare(H[li],H[ri])<0 && cmp.compare(H[i],H[ri])<0) ci = ri;
	    if (ci != -1) {
		swap(i,ci);
		i = ci;
	    }
	}//while
	return i;
    }//swapdown


    ///////// interface procedures, finally
    public void add(T x) // add x to heap
    {
	if (size>=H.length) resize();
	H[size] = x; // place temporarily at end;
	size++;
	swapup(size-1);
    }
    public T poll()
    {
	if (size<1) return null;
	T answer = H[0];
        H[0] = H[--size];
	if (size>0) swapdown(0);
	return answer;
    }
    public T peek() 
    { if (size<1) return null; else return H[0]; }

    public boolean isheap()
    {
	for (int i=size-1;i>0;i--) 
	    if (cmp.compare(H[i],H[parent(i)])>0) return false;
	return true;
    }

    public void display(HeapDisplay hd) { hd.drawtree(H,size); }

    public static void main(String[] av)
    {
	int n = 40;
	if (av.length>0) n = Integer.parseInt(av[0]);
	BinaryHeap<Integer> PQ = new BinaryHeap<Integer>(8,false); //maxheap
	for(int i=0;i<n;i++) PQ.add((int)(Math.random()*n*10));
	HeapDisplay hd = new HeapDisplay(1000,700);
	PQ.display(hd);
	for(int i=0;i<n;i++) System.out.print(PQ.poll()+", ");
	System.out.println();
	
	//PQ.setComparator( (x,y)->x.compareTo(y) );
    }//main
}//BinaryHeap  march 2022



/*
Mathematical Properties of Complete Binary Trees and Heaps:

1. The number of leaf nodes (nodes with no children) is (size+1)/2 without
   the remainder.

   PROOF.

      First, call a complete binary tree "perfect" if all nodes on the 
      last level of the tree has zero children.  Every complete binary 
      tree has a perfect subtree with n perfect levels.  For example:

        8
      /   \
     4     7
    / \   / \
   2   3 5   3
  / \
 1   0

 This tree has n=3 perfect levels and 4 total levels.  The total
 number of nodes in the perfect subtree is (2**n)-1 (using python
 syntax for exponent).  The number of nodes on the last level of the
 perfect subtree is 2**(n-1).  The number of nodes on the very last
 level of the entire tree is either 2*m (even) or 2*m+1 (odd) for some m.
 In the above example, m==1 and there are 2*m==2 nodes on the last level.

 In the general case, the number of leaf nodes on the tree is always
 2**(n-1) + m.  This is because if a node on the last perfect level has
 only one child, then no extra child is added (one-one tradeoff - picture
 the 2 without the right child).  If a node on the last perfect level has
 two children, then one extra child is added.  We have two cases:

 a. if size == (2**n)-1 + 2*m, then (size+1)/2 == (2**n + 2*m)/2 ==
    2**(n-1) + m, which is the expected number of leafs.

 b. if size == (2**n)-1 + 2*m+1 == 2**n+2*m, then (size+1)/2 ==
    (2**n+2*m+1)/2.  This is an odd number dividing an even number
    (for n>0) and throwing away the remainder, we also get 2**(n-1)+m.


2. The worst case time complexity of peek() and size() is O(1).

3.  The worst case time complexity of .add is O(log n) without resize and 
     O(n) with resize. However, for n .add operations the total cost of 
     resizing stays within O(n).  This is similar to the Circular Queue.
    We can say that .add has *amortized* time complexity O(log g)

3b. The average and worst case complexity of .poll() is O(log n);

4.  The average case time complexity of .add is O(1).  This is the
most interesting property.  Notice that half of all nodes are leaf
nodes, (size+1)/2 to be exact but we'll just call it n/2. Thus there
is a 50% or 1/2 chance that whatever number you insert will end up as
a leaf, requiring no further swaps upwards.  (To be technically
correct, there is a 50% chance as n approches infinity, thus it is
valid to ignore the +1 in size+1).

Each level of the tree has twice the number of nodes as the previous
level.  Thus there is a 25% or 1/4 chance that the node inserted will
be just above a leaf node, requiring one additional swapup operation.
Similarly, there is 1/8 chance that the inserted node
will require 2 swaps, 1/16 chance that it will require 3 swaps, and so
on...  In general, there will be a 1/2**m chance that .add will require
m-1 swaps.

We can write down the average number of steps to insert a value as a
weighted average of these cases.  For example (1/4)a + (3/4)b is a
weighted average of a and b where b has three times the weight of a.
The sum of the weights (probabilities) must add up to one.  But since
we don't know how large the tree is, we will have to write down the
weighted average as an infinite series (grab your calculus textbook).

Let inf = infinity.  The weighted average of the number of steps to insert
a value into an arbitrary binary heap is 

 inf  
Sigma  (n-1)/2**n   =  0*1/2 + 1*1/4 + 2*1/8 + 3*1/16 + 4*1/32 ...
 n=1 

Using established theorems concerning the convergence of series, we
observe that the ratio between successive values in the series is

      n
     --------
     2**(n+1)
  ---------------
      (n-1)
     --------
       2**n

This cancels out to be   n / (2*n-2). And 

limit     n/(2*n-2)   ==  1/2 
n->inf  

Since the limit of this ratio is less than one, we know that the
series converges to some number S.  This is enough to conclude that
.add is O(1) on average, but we want to know what S actually is.

Notice that the probabilities of each possible outcome form the series

 inf 
Sigma  1/2 * (1/2)**n  = 1/2 + 1/4 + 1/8 + 1/16 ...
 n=0

This is a *geometric* series and it converges to 1: the body of the sum
can be written in the form a*r**n with a=1/2 and r=1/2.  Such series
converges to a/(1-r), which in this case is (1/2)/(1/2) == 1.

Thus all the probabilities added together is indeed 1.  Furthermore, let
ONE be the geometric series above and let HALF = ONE -1/2, which is the
series 1/4 + 1/8 + 1/16..., which clearly converges to 1/2.
we can observe the following equality

  S - HALF = S / 2

Because S - HALF is 0 + 1/8 + 2/16 + 3/32 + 4/64 ...

But S/2 gives us the same series.  Thus S-1/2 == S/2 and therefore we have

      2*S-1==S  and thus 2*S-S==1 which means S == 1

This tells us that it takes on average one swap to insert a
value into a binary heap.

Our analysis is not perfect because there are actually (size+1)/2 nodes that
do not require swaps.  Experimental data shows that the constant is close
to 1.28
*/

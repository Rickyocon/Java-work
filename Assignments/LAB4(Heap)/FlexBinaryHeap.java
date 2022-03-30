public class FlexBinaryHeap<T extends HeapValue<T>> extends BinaryHeap<T> implements PriorityQueue<T>
{
    public FlexBinaryHeap(int cap) { super(cap,false); }
    public FlexBinaryHeap() { super(); }

    @Override
    @SuppressWarnings("unchecked") /* don't use unless you're REALY sure...*/
    protected T[] makearray(int n) { return (T[]) new HeapValue[n]; }

    public FlexBinaryHeap(T[] A, int s)
    {   if (A==null || A.length<1 || s<0 || s>=A.length)
	    throw new RuntimeException("invalid heap array");
	H = A;  size = s;
	heapify();
    }

    //Part 2 heapify
    protected void heapify()
    {
        int i = (size/2) - 1;
        while (i>=0) swapdown(i--); 
    }

    @Override
    protected void swap(int i,int j)
    {
        T temp = H[i];
        H[i]=H[j];
        H[i].setIndex(j);
        H[j]=temp;
        H[j].setIndex(temp.getIndex());
    }

    @Override
    public void add(T x)
    {
        if(size>=H.length) resize();
        H[size] = x;
        H[size].setIndex(size);
        size++;
        swapup(size-1);

    }

    //Part 1 reposition
    public boolean reposition(T x) 
    {
        //get parent left right 
        int i = x.getIndex();
        int parent = parent(i);
        int left = left(i);
        int right = right(i);
        //compare variables
        //check > parent if so swap up
        if(H[i].compareTo(H[parent])>0) 
        {
            swapup(i);
            return true;
        }

        //if less then left or right swap down
        if(H[i].compareTo(H[left])>0 && left>size || H[i].compareTo(H[right])>0 && right<size)
        {
            swapdown(i);
            return true;
        }
        return false;
    }


    //Part 3
    // Java program for implementation of Heap Sort
    public void sort(int arr[])
    {
        FlexBinaryHeap<Integer> heapSort = new FlexBinaryHeap<Integer>(arr.length);
        
        for(int i=0;i<arr.length;i++)
        {
            heapSort.add(arr[i]);
        }

        for(int i=0;i<arr.length;i++)
        {
            heapSort.pop();
        }

    }
        
    /**class numbers
    {
        public final String name;
        public void number(int) // constructor
        {
            if (arr = null) throw new RuntimeException("invalid: array is null");
        }
        public Integer compareTo(scholar other)
        {
        return (Integer)(gpa*100+0.5) - (Integer) (other.gpa*100+0.5);
        }

        public void updateGPA(double g)  // OOPS, the "priority" can change!
        {
        if (g>0 && g<=4.0) gpa = g;
        }

        protected int hi = -1; // heap index, -1 means index invalid
        public int getIndex() { return hi; }
        public void setIndex(int n) { if (n>=0) hi=n; }
  
    }//numbers class **/


    public static void main(String[] av)
    {
        scholar a = new scholar("A",1.5);
        scholar b = new scholar("B",2.5);
        scholar c = new scholar("C", 3.3);
        scholar d = new scholar("D",3.0);
        scholar e = new scholar("E",3.2);
        scholar[] Scholars = {a,b,c,d,e};
        FlexBinaryHeap<scholar> FPQ = new FlexBinaryHeap<scholar>();
        for (scholar x:Scholars) FPQ.add(x);
        System.out.println("top scholar: "+FPQ.peek().name);
        // priorities change over time...
        d.updateGPA(3.5);  FPQ.reposition(d);
        e.updateGPA(3.7);  FPQ.reposition(e);
        System.out.println("after GPA updates...");
        while (FPQ.size()>0) System.out.println(FPQ.poll().name);
        //test heapify
        System.out.println("testing heapify: ");
        scholar Colin = new scholar("Colin", 1.79);
        scholar Jake = new scholar("Jake", 3.80);
        scholar Josh = new scholar("Josh", 3.00);
        scholar Dan = new scholar("Dan", 4.00);
        scholar[] scholarNew = {Colin,Jake,Josh,Dan};
        FlexBinaryHeap<scholar> heap = new FlexBinaryHeap<scholar>(scholarNew, 10);
    }
}

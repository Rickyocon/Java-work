import javax.lang.model.util.ElementScanner14;

public class CircCmpQueue<T extends Comparable<T>> extends CircQueue<T> implements CmpQueue<T>
{
    protected boolean sorted = true;   
    public boolean forced_check() // force O(n) check for sorted, FOR DEBUGGING
    {
        for(int i=0;i<size-1;i++)
            if (A[ri(i)].compareTo(A[ri(i+1)])>0) return false;
        return true;
    } //forced_check

    /// This is needed because of a java peculiarity (the type erasure of T 
    /// in the subclass is Comparable, not Object):
    @Override
    protected T[] makearray(int cap) { return (T[]) new Comparable[cap]; }

    public CircCmpQueue(int initcapacity) // constructor
    {
        super(initcapacity);
    } //CircCmpQueue


    @Override
    public void push(T x)
    {
        super.push(x); // invokes inherited version of push
        if (size>1 && A[ri(0)].compareTo(A[ri(1)])>0)
            sorted = false;
    } //push


    @Override
    public void enqueue(T x) 
    {
        super.enqueue(x);
        if(A[ri(size)].compareTo(x) < 0)
        {
            sorted = false;
        }
        A[ri(size+1)] = x;
    } //enqueue


    @Override
    public boolean is_sorted()
    {
        return sorted;
    } //is_sorted


    @Override
    public T setnth(int n, T x)
    {
        if (n>=size || n<0)
            throw new RuntimeException("value doesn't exists: "+n);  
    
        int ni = (front+n)%A.length; // index of nth value
        T ax = A[ni];
        if(A[ri(n)].compareTo(x) < 0 && A[ri(n-1)].compareTo(x) < 0 && A[ri(n+1)].compareTo(x) > 0) 
        {sorted = false;}

        A[ni] = x;
        return ax;
    } //setnth



    public int search(T x)
    
    {
        int index = -1;
        if(sorted==true)
        {
            int l = front; //index of first value
            int r = lasti(); //real index of last value
            while(l<=r)
            {
                int mid = 1+(r-1)/2;
                if(A[mid]==x) {return mid;}
                else if (A[mid].compareTo(x)>0) {r=mid-1;}
                else {l=mid+1;}
                index = mid;
            }
        }
        else 
        {
            for (int i=0;i<size;i++)
            {
                if(x.compareTo(A[i])==0)
                {
                    System.out.println("Value present at index: ");
                    index = i;
                }
                else
                { 
                    System.out.println("Value does not exist");
                }
            } 
        
        }
        return index;
    } //search



    public int insert_sorted(T x)
    {
        int index = -1;
        if (sorted==true)
        {
            super.push(x);
            int i = 0;
            while(size>2 && A[ri(i)].compareTo(A[ri(i+1)])<0)
            {
                T tmp = A[i];
                A[i] = A[i+1];
                A[i+1] = tmp;
                i++;
                index = i;
            }
        }
        else if(sorted==false)
        {
            index = -1;
        }
        return index;

    } //insert sorted





    //----------------------------------------------------------------------------------
   // ... won't compile until you've implemented the interfaces

   public static void main(String[] av)
    {
        int n = 200;
        CircCmpQueue<Double> cq = new CircCmpQueue<Double>(1);
        // insert in order n random values
        while (--n>0) cq.insert_sorted(Math.random()*1000);
        System.out.println("sorted: "+cq.is_sorted());
        System.out.println("forced check: "+cq.forced_check());    
	    System.out.println("testing search: "+ cq.search(123.456));
        Double x = cq.getnth(10); // get the 10th value from queue
        System.out.println("search2: "+ cq.search(x)); //should find it
        cq.printinfo();
    }

} //class
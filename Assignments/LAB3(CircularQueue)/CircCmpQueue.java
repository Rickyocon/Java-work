public class CircCmpQueue<T extends Comparable<T>> extends CircQueue<T> implements CmpQueue<T>
{

    public interface Comparable<T>
    {
        int compareTo(T x);
    }//comparable 






   protected boolean sorted = true;   
   public boolean forced_check() // force O(n) check for sorted, FOR DEBUGGING
    {
        for(int i=0;i<size-1;i++)
            if (A[ri(i)].compareTo(A[ri(i+1)])>0) return false;
        return true;
    }//forced check





   /// This is needed because of a java peculiarity (the type erasure of T 
   /// in the subclass is Comparable, not Object):
   @Override
   protected T[] makearray(int cap) { return (T[]) new Comparable[cap]; }

   public CircCmpQueue(int initcapacity) // constructor
    {
        super(initcapacity);
    }//makeArray






    public interface Queue<T>
    {
        int size();      // number of values stored in Queue
        void push(T x);  // add to front of queue (queue used like a stack)
        T pop();         // delete from front of queue
        T peek();        // value at front without delete
        void enqueue(T x); // add to end of queue
        T dequeue();       // delete from end of queue
        T last();          // last value in queue, without delete
    }



    

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

}//class

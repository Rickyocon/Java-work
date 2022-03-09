import java.util.Iterator;  // interface enables for loops

public class CircQueue<T> implements Iterable<T>, Queue<T>
{
    protected T[] A; // underlying array representing the queue
    protected int front; // index of first value in queue
    protected int size;  // number of values in queue, not same as A.length
    public int size() {return size;} // readonly accessor for size.
    public int capacity() { if (A==null) return 0; else return A.length;}

    protected int ri(int i)    // real index of virtual index i:
    {
        return (front+i)%A.length;
    }
    protected int lasti() // real index of last value, if it exists  O(1)
    {
	return (front+size-1)%A.length;
    }
    protected int end() { return (front+size)%A.length; } // one past lasti
    protected int right(int i) // real index to "right" of real index i
    { return (i+1)%capacity(); }

    protected int left(int i) // index of cell to the "left" of index i O(1)
    {
        return (i+capacity()-1) % A.length;
    }

    //// this function is needed because of a quirk of java generics, and
    //  should be @Overridden in subclasses where type T is further constrained
    protected T[] makearray(int cap) // create a generic array of size cap
    {
        return (T[]) new Object[cap]; // will get compiler warning
    }

    // constructor
    public CircQueue(int cap) // create a queue with an initial capacity
    {
        if (cap<1) cap=16;
	// A = new T[cap]; // won't compile in Java
	//A = (T[]) new Object[cap]; // compiles with compiler warning
        A = makearray(cap);
	front = 0;
	size = 0;
    }//constructor

    public CircQueue(T[] B) { A = B; front=0; size=0; } //alternate constructor

    public void resize() // doubles size, copy
    {
	T[] B = makearray(A.length*2); //(T[]) new Object[A.length*2];
	for(int i=0;i<size;i++)  B[i] = A[(front+i)%A.length];
	A = B; // new array now represents queue
	front = 0;
    }//resize
    public void doublesize() // alias
    {
        resize();
    }
    public int shrink() // shrink capacity to 90% full, return amount shrunk
    {
        // want size/capacity==.9 so target capacity = size/.9 = 10*size/9
        int targetcap = (1000*size)/900;
        if (capacity() <= targetcap) return 0; // not possible to shrink
	T[] B = (T[]) new Object[targetcap];
	for(int i=0;i<size;i++)  B[i] = A[(front+i)%A.length];
        A = B;
        front = 0;
        return capacity()-targetcap;
    }

    // insert x into front of queue (push)   O(1)
    public void push(T x) 
    {
	if (size>=capacity()) resize();
	// special case: front not valid if size is 0
	if (size>0) front = left(front);
	A[front] = x;
	size++;
    }

    // delete and return from front of queue  O(1)
    public T pop()
    {
	if (size<1) throw new RuntimeException("buffer underflow");
	size--;
	T answer = A[front];
        A[front] = null; // helps garbage collector
	front = right(front);
	return answer;
    }
    public T peek() // return first value without delete, O(1)
    {
	if (size<1) return null;
	else return A[front];
    }
    public T first() { return peek(); } // alias for peek

    // insert into back of queue (enqueue)  O(1)
    public void enqueue(T x)
    {
	if (size>=capacity()) resize();
	int nexti = (front+size)%capacity(); // if size==0, nexti==front
	A[nexti] = x;
	size++;
    }

    // delete from back of queue  O(1) - constrast with singly linked list
    public T dequeue()
    {
	if (size<1) throw new RuntimeException("buffer underflow");
	T answer = A[lasti()];
        A[lasti()] = null;
	size--;
	return answer;
    }
    public T last() // returns last value value in list, O(1)
    {
	if (size<1) return null;
	else return A[lasti()];
    }

    // finding the nth value from the front, 0th = first
    public T getnth(int n) // return value at virtual index n,  O(1)
    {
	if (n>=size || n<0)
	    throw new RuntimeException("value doesn't exist at "+n);
	return A[ (front+n)%A.length ];
    }

    // set the nth value from front to x, return previous value  O(1)
    public T setnth(int n, T x)
    {
	if (n>=size || n<0)
	    throw new RuntimeException("value doesn't exists: "+n);
	int ni = (front+n)%A.length; // index of nth value
	T ax = A[ni];
	A[ni] = x;
	return ax;
    }

    // for DEBUGGING PURPOSES:
    void printinfo()
    {
	System.out.println("Internal array:");
	for(int i=0;i<A.length;i++) System.out.print(i+":"+A[i]+"--");
	System.out.println();
	System.out.printf("front==%d, last==%d\n",front,lasti());
    }
    
    /////////////// For Iterator and Iterable interfaces...

    class Qiterator implements Iterator<T>  // internal class has access
    {                                       // to front, size, etc
	int i = 0;
	//public Qiterator() {} // default constructor
	public boolean hasNext()
	{
	    return i<size;
	}
	public T next()
	{
           return getnth(i++);
	}
    }//Qiterator

    public Iterator<T> iterator() // required by Iterable interface
    {
	return new Qiterator();
    }


    // main is only for testing
    public static void main(String[] av)
    {
	CircQueue<Integer> Q = new CircQueue<Integer>(1);
	for(Integer i=2;i<16;i+=2) {Q.push(i); Q.enqueue(i+1); }
	Q.dequeue();
	Q.pop();
	Q.printinfo();        
	for(Integer x:Q) System.out.println(x);
	Q.printinfo();
    }
}//CircQueue



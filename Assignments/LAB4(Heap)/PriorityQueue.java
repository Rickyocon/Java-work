public interface PriorityQueue<T>
{
    int size();  // number of values of type T in queue
    void add(T x); // insert x into heap
    T poll();  // returns and deletes the value with highest priority, or null
    T peek();  // returns value with highest priority or null

    // the following procedure repositions a value x in the queue after
    // a change to its priority.  returns false on failure.
    default boolean reposition(T x) { return false; }
}
//march 2022

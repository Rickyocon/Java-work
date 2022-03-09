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
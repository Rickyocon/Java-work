
public interface CmpQueue<T> extends Queue<T>
{
    boolean is_sorted(); // determines if queue is sorted in increasing order
    int search(T x); // first index of value: x.compareTo(value)==0, or -1
    T setnth(int n, T x); //change value at virtual index n to x
    int insert_sorted(T x); // insert new x into a sorted queue
    //default void sort() {} // (OPTIONAL) sorts queue if it's not sorted
}
package SpecialMidtermLab;


class moreuseful<T extends Comparable<T>> // some algorithms on integer arrays
{
    T[] A;
    public moreuseful(T[] B) { A=B; }
    
    // function to check if A is sorted in increasing order
    public boolean is_sorted()
    {  
	for(int i=0;i<=A.length-2;i++)
	    if (A[i].compareTo(A[i+1])<0) return false;
	return true;
    }//is_sorted

    // function to do binary search for value x in array A
    public boolean binsearch(T x) // assumes that A is sorted
    {
	boolean answer = false;
	int min = 0, max = A.length-1;  // indices to search between
	while (A[min].compareTo(A[max])<0 && !answer)
	  {
	      int mid = (min+max)/2; // middle index
	      if (x.compareTo(A[mid])==0) answer=true;
	      else if (x.compareTo(A[mid])<0) max = mid-1;
	      else min = mid+1;
	  }//while
	return answer;
    }//binsearch
}//moreusefull.


//=========================================


public class AgeLab
{
    public static void main(String[] argv)
    {
        person a = new person("Jake",70);
        person b = new person("Jaime",40);
        person c = new person("Colin",12);
        person d = new person("Derik",20);
        person e = new person("Engler",17);
        person[] P = { c,e,d,b,a };

        moreuseful<person> MU = new moreuseful<person>(P);
        System.out.println(MU.is_sorted()); // false
        System.out.println(MU.binsearch(d)); // true
        System.out.println(MU.binsearch(new person("nobody",-1))); //nobody has age -1
        // you may change the definition of compareTo to run these tests.
    }//main

}//GenericLab
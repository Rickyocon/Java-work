import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

interface twowaymap<TA,TB> // bijective map between values of types TA and TB
{
   void set(TA x, TB y);  // insert or change pair, x,y must be non-null.
   TB get(TA x);   // (or getA) get corresponding TB value given TA value x
   TA reverse_get(TB x); // get TA value given TB value
   void removeKey(TA x); // remove pair associated with TA x as key
   void removeVal(TB y); // remove pair associated with TB y as key
   int size();       // number of pairs in map. (equal to number of keys)
}

public class Bimap1<TA,TB> implements twowaymap<TA,TB>
{ 
    HashMap<TA,TB> Grades = new HashMap<TA,TB>(); //TA = Letter, TB = Number

    HashMap<TB,TA> Points = new HashMap<TB,TA>(); //TA = Number, TB = Letter

//----------------------------------
    public void set(TA x, TB y)
    {
        if(Grades.containsKey(x)) { removeKey(x); System.out.println("Removing Grade: ");}
        if(Points.containsKey(y)) { removeVal(y); System.out.println("Removing point: ");}
       
            Grades.put(x,y);
            Points.put(y,x);
        
    } //Set

//-----------------------------------
    public TB get(TA x) 
    {
        TB y = Grades.get(x);
        return y;
    } //get

//------------------------------------
    public TA reverse_get(TB x) 
    {
        TA y = Points.get(x);
        return y;

    } //reverse get

//------------------------------------
    public int size() 
    {
        int x = Grades.size();
        int y = Points.size();
        if(x==y) {return Grades.size();}
        else {return 0;}
    } //Size

//-------------------------------------
    public void removeVal(TB y) 
    {
        TA temp = Points.get(y);
        Points.remove(y);
        Grades.remove(temp);

    } //Remove val

//--------------------------------------
    public void removeKey(TA x)
    {
        TB temp = Grades.get(x);
        Grades.remove(x);
        Points.remove(temp);
    } //Remove key






        public static void main(String[] argv)
        {
            // bijective map between letter grades and grade point values
            Bimap1<String,Double> GP = new Bimap1<String,Double>();  //Deleted 16 from ()
            String[] Grades = {"A","A-","B+","B","B-","C+","C","C-","D+","D","F"};
            Double[] Points = {4.0,3.7,3.3,3.0,2.7,2.3,2.0,1.7,1.3,1.0,0.0};
            for(int i=0;i<Grades.length;i++) GP.set(Grades[i],Points[i]);
            for(String g:Grades) System.out.println(GP.get(g));
            for(Double p:Points) System.out.println(GP.reverse_get(p));	

            GP.set("A-",3.75); // CHANGE value of A- from 3.7 to 3.75
            GP.set("B+",3.25); // change another key/value
            GP.removeVal(1.3); // remove the D+ grade

            System.out.println(GP.get("A-")); // should print 3.75
            System.out.println(GP.reverse_get(3.75)); // should print A-
            System.out.println(GP.get("D+")); // should print null
            System.out.println(GP.reverse_get(1.3)); // should print null

            /// THIS ONE IS THE MOST IMPORTANT, EASY TO GET WRONG:

            System.out.println("3.7: "+GP.reverse_get(3.7)); //should print null****

            // because you already changed A- to correspond to 3.75, there
            // should be no value associated with 3.7.

                // Also be sure the check this one:  ****
            //GP.set("A+",4.0); // this will also erase value for "A". why?
            System.out.println(GP.size());  // should print 10

            // Pay special attention to cases marked with **** above
        }

}//Bimap

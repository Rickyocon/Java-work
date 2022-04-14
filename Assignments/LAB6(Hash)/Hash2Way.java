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

public class Hash2Way<TA,TB> implements twowaymap<TA,TB>
{ 
    HashMap<TA,TB> Grades = new HashMap<TA,TB>(); //TA = Letter, TB = Number

    HashMap<TB,HashSet<TA>> Points = new HashMap<TB,HashSet<TA>>(); //TA = Number, TB = Letter

//----------------------------------
    public void set(TA x, TB y)
    {
        if(Grades.containsKey(x)) { removeKey(x); System.out.println("Removing Grade: ");}
        if(Points.get(y) != null)
        {
            Points.get(y).add(x);
        }
        else  
        {  
            HashSet<TA> test = new HashSet<TA>();
            test.add(x); 
            Points.put(y, test);
        }
        Grades.put(x,y);

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






    
    public static void main(String[] av)
        {
            // create a Hash2Way hashmap to map student names to GPAs
            Hash2Way<String,Double> GPA = new Hash2Way<String,Double>();
            GPA.put("Ian",3.7);
            GPA.put("Chris",4.0);
            GPA.put("Andrew",4.0);
            GPA.put("Diego",4.0);
            GPA.put("Jan",3.5);
            GPA.put("Skylar",3.5);
        
            System.out.println("Andrew's GPA: "+GPA.get("Andrew"));
            System.out.println("People with 4.0 gpas:");
        
            HashSet<String> set = GPA.reverse_get(4.0); //*** note HashSet returned
            for(String s:set) System.out.println(s);
        
            GPA.put("Andrew", 3.7); // oops too much partying
            System.out.println("\nAndrew's new GPA: "+GPA.get("Andrew"));
            System.out.println("People with 4.0 gpas:"); 
            set = GPA.reverse_get(4.0);  // Andrew not in list anymore
            for(String s:set) System.out.println(s);
            System.out.println("People with 3.7 gpas:");
            set = GPA.reverse_get(3.7);
            for(String s:set) System.out.println(s);
        }//main

}//Hash2Way


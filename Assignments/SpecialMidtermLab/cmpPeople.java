
interface Comparable<T>
{
   int compareTo(T x);
}

class person implements Comparable<person>
{
    public final String name;
    public int age;
    public person(String n, int a)
    {
	name = n;
    age = a;
    }//constructor 
    
    public int ageGroups()
    {
        //int ageGroup = 0; //create age groups
        if(age>=0 && age<=18) {return 1;}  //age groups 1 - 4, groups respectly by there age
        if(age>=19 && age<=34) {return 2;}
        if(age>=35 && age<=64) {return 3;}
        else {return 4;}
    }

    @Override
    public int compareTo(person other)
    { 
        if(this.ageGroups() < other.ageGroups()) {return -1;}
        if(this.ageGroups() == other.ageGroups()) {return 0;}
        return 1;
        
    }

}//person class

public class cmpPeople 
{

    public static void main(String[] argv)
    {
        person person1 = new person("Jake", 20); 
        person person2 = new person("stacy", 56); 
        person person3 = new person("colin", 1); 
        person person5 = new person("ford", 90); 
        person person6 = new person("Daniel", 90); 

    }
    
}//cmp People



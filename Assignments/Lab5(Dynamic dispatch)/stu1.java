/*class student
{
    public final String name;
    protected boolean isgraduate = false; // is this student a grad student?
    protected double gpa;
    protected int credits; // number of credit hours taken
    protected String major=null; // used only for undergraduates
    protected String thesistopic=null; // used only for graduates
    public boolean isgraduate() { return isgraduate;}

    static String[] mjs= {"comp sci", "poly sci", "engineering", "math", "physics","biology","psychology", "history", "leisure studies"};
    static String[] topics = {"p equals np", "halting problem", "distributed os", "real time fault tolerance", "compiler design", "cryptography", "machine learning"};
    
    // constructor takes name and graduate/undergrad flag, randomizes rest
    public student(String n, boolean g)
    {
	name = n;
	isgraduate = g;
	gpa = ((int)(Math.random()*401))/100.0; // set random gpa
	credits = 3 + (int)(Math.random()*15); //number of credits being taken
	if (isgraduate) 
	    thesistopic = topics[(int)(Math.random()*topics.length)];
	else
	    major = mjs[(int)(Math.random()*mjs.length)];
    }//student constructor

    // compute tuition: undergrads pay $1200 per credit, grads pay $1600
    public double tuition()
    {
	if (isgraduate) return 1600*credits;
	else return 1200*credits;
    }
}// student class


public class stu1
{
    public static void main(String[] argv)
    {
	String[] names = {"Matthew","Taiga","Michael","Stefan","Piper","Jules","Ahmed","Christopher","Ricky","Yonghyeon","Dylan","Peyton","Tito","Kevin","Max","Nishad","David","Justin"};
	student[] roster = new student[names.length];
	// create random roster of students, alternating between grads and
	// undergrads
	for(int i=0;i<roster.length-1;i+=2)
	    {
		roster[i] = new student(names[i],true);  // grad
		roster[i+1] = new student(names[i+1],false); //undergrad
	    }
	//compute total tuition of all students
	double total = 0;
	for(student s:roster) total += s.tuition();
	System.out.println("total tution is "+total);

    // undergrad students with gpa < 2.0 are on academic probation,
    // while grad students with gpa < 3.0 are also on academic probation.
    // the following loop prints names of all students on probation, plus
    // some additional information about each student:
	for(student s:roster)
	    {
		if (!s.isgraduate() && s.gpa<2.0)
		    System.out.println("Undergraduate student "+s.name+", majoring in "+s.major+", has an amazingly low gpa of "+s.gpa);
		else if (s.gpa < 3.0)
		    System.out.println("Graduate student "+s.name+", who is writing a thesis on "+s.thesistopic+", has a gpa of only " + s.gpa);
	    }
    }//main
}
*/
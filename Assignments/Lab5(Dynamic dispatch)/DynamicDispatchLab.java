abstract class student
{
    public String name;
    protected double gpa;
    protected int credits; // number of credit hours taken
    protected double tuition;
    public student(String n)
    {
	name = n;
	gpa = ((int)(Math.random()*401))/100.0; // set random gpa
	credits = 3 + (int)(Math.random()*15); //number of credits being taken
    }//student constructor

    // compute tuition: undergrads pay $1200 per credit, grads pay $1600
    abstract boolean CheckProbation();
    abstract void ProbationStatus();
    

}//abstract class stu1

class graduates extends student
{
    protected String thesistopic=null; // used only for graduates
    static String[] topics = {"p equals np", "halting problem", "distributed os", "real time fault tolerance", "compiler design", "cryptography", "machine learning"};
    public graduates(String n)
    {
        super(n);
        tuition = 1600*credits;
        thesistopic = topics[(int)(Math.random()*topics.length)];
    }
    @Override
    public boolean CheckProbation()
    {
        return this.gpa<3.0;
    }
    @Override
    public void ProbationStatus()
    {
        System.out.println("Graduate student "+this.name+", who is writing a thesis on "+this.thesistopic+", has a gpa of only " + this.gpa);
    }

}//class graduates


class undergrads extends student
{
    protected String major=null; // used only for undergraduates
    static String[] mjs= {"comp sci", "poly sci", "engineering", "math", "physics","biology","psychology", "history", "leisure studies"};
    public undergrads(String n)
    {
        super(n);
        tuition = 1200*credits;
        major = mjs[(int)(Math.random()*mjs.length)];
    }
    @Override
    public boolean CheckProbation()
    {
        return this.gpa<2.0;
    }
    @Override
    public void ProbationStatus()
    {
        System.out.println("Undergraduate student "+this.name+", majoring in "+this.major+", has an amazingly low gpa of "+this.gpa);
    }

}//class undergrads

class StudentAth extends undergrads
{
    static String[] sports= {"Baseball", "Football", "VolleyBall", "Golf", "Swimming","Cross country","Track"};
    public String sport = null;
    public StudentAth(String n)
    {
        super(n);
        sport = sports[(int)(Math.random()*sports.length)];
    }

    @Override
    public boolean CheckProbation()
    {
        return this.gpa<1.5;
    }

    @Override
    public void ProbationStatus()
    {
        System.out.println("Student Athlete "+this.name+", majoring in "+this.major+ "Is playing the sport"+ this.sport+ ",has an amazingly high gpa of" +this.gpa);
    }
}


public class DynamicDispatchLab
{
    public static void main(String[] argv)
    {
        String[] names = {"Matthew","Taiga","Michael","Stefan","Piper","Jules","Ahmed","Christopher","Ricky","Yonghyeon","Dylan","Peyton","Tito","Kevin","Max","Nishad","David","Justin"};
        student[] roster = new student[names.length];
        // create random roster of students, alternating between grads and
        // undergrads
        for(int i=0;i<roster.length-1;i+=3)
            {
            roster[i] = new graduates(names[i]);  // grad
            roster[i+1] = new undergrads(names[i+1]); //undergrad
            roster[i+2] = new StudentAth(names[i+2]); 
            }
        //compute total tuition of all students
	    double total = 0;
	    for(student s:roster) total += s.tuition;
	    System.out.println("total tution is "+total);

        // undergrad students with gpa < 2.0 are on academic probation,
        // while grad students with gpa < 3.0 are also on academic probation.
        // the following loop prints names of all students on probation, plus
        // some additional information about each student:
        for(student s:roster)
        {
        if (s.CheckProbation()) {s.ProbationStatus();}
    }
}//main

    }

//public class DynamicDispatchLab




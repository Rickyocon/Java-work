import java.util.Random;
import java.util.ArrayList;
import java.lang.Math.*;
//-----------------------


class gasStation
{
    protected double regularprice;  //Price for a gallon of regular
    protected double superprice;   //Price for a gallon of super
    protected double sales;       //Total sales of station in dollars
    protected double currentRegSupply = 1000;
    protected double currentSupSupply = 1000;
    protected double added;


    //constructor for gas stations
    public gasStation(double r, double s)
    {
            regularprice = r;
	    superprice = s;
	    sales = 0;
    }

    //Methods to sell gas, parameter indicates number of gallons
    //Sell Regular
    void sellregular(double gallons)
    {
        sales += regularprice * gallons; //This * is multiplication
        currentRegSupply -= gallons;
        if(currentRegSupply > 200)
	{
            gouge();
	}	
    }


    //Sell Super
    void sellsuper(double gallons)
    {
        sales += superprice * gallons; //Again the * is multiplication
        currentSupSupply -= gallons;	
	if(currentSupSupply > 200)
	{
  	    gouge();
	}
    }


    //Accessor method (return sales)
    double getsales()
    {
        return sales;
    }


    //Method to compare the total sales of gas staion versus another
    boolean moreprofit(gasStation other)//Multiplication
    {
        return sales > other.sales;
    }


    //Method to double prices
    private void gouge()
    {
        superprice *= 2;
	regularprice *= 2;
    }

    //Method to print current supply of gas
    double currentGasSupply()
    {
	added = currentRegSupply + currentSupSupply;    
	return added;
    }
    
} //End of gasStation class 
//--------------------------------------------------------------------------


public class Final //Note that main must be in a class and that class must be the name of the file main is contained in

{
    public static void main(String[] argv)  //Syntax for main function 
    {

	int Ammount = 10; //The amount gas stations
        gasStation A = new gasStation(3.69, 4.09);
        gasStation B = new gasStation(3.49,3.99);

        A.sellregular(10);
	A.sellsuper(8);
	B.sellsuper(11);
	B.sellregular(12);

	if (A.moreprofit(B)) 
	{
            System.out.println("Station A is more profitable");

        }
        else
	{
            System.out.println("Station B is more profitable");
	}

	System.out.println("----------------------------");

        
	//You've made a lot of money and now own a whole array of gas stations
	gasStation G[] = new gasStation[Ammount];
	
	for (int i=0;i<Ammount;i++)
	{
	    G[i] = new gasStation(3.59,4.19);
	}


	//Some test transactions 
	for (int i=0;i<Ammount;i++)
	{
            G[i].sellregular((int) (Math.random()*20));
            G[i].sellsuper((int) (Math.random()*12));
            G[i].sellregular((int) (Math.random()*11));
            G[i].sellsuper((int) (Math.random()*4));
            G[i].sellregular((int) (Math.random()*9));
            G[i].sellsuper((int) (Math.random()*13));
            G[i].sellregular((int) (Math.random()*19));
            G[i].sellsuper((int) (Math.random()*6));
            G[i].sellregular((int) (Math.random()*2));
            G[i].sellsuper((int) (Math.random()*5));
	    
	}


	//loop to find gas station with highest sales value
	gasStation highest = G[0];
	for(int i=1;i<Ammount;i++)
	{
            if(G[i].moreprofit(highest))
	    {
                highest = G[i];
		System.out.println("Highest Total Sales is "+ highest.getsales()); 
	    }
	}


    } //End of main function

} //End of progname class

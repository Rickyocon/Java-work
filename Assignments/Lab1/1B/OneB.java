import java.io.*;
import java.util.Scanner;

class methods
{
    //Method that searchs for string s
    public static boolean inarray(String s, String[] SA)
    {
	for(String x:SA)
	{	
	    if(x.equals(s)) 
	    {
		return true;
            }
	}
	return false;

    }//inarray



    //Method that adds up the values in a given array (A)
    public static int sum(int[] A)
    {
        int[] B = new int[] {2,4,6};
	int sum = 0;
	for (int i = 0; i < B.length; i++)
	{
            sum = sum + B[i];
	}

	return sum;
	
    }//sum

    
    //Mehtod to return the smallest string
    public static String smallest(String[] SA)
    {
        if (SA == null || SA.length < 1) 
	{
            return null;
	}


        String smallest = SA[0];
        for (int i = 1; i < SA.length; i++) 
	{
            if (SA[i].length() < smallest. length()) 
	    {
                smallest = SA[i];
            }
        }
        return smallest;
    }// smallest



    //Mehtod to reverse a string
    public static String Reverse(String nstr)
    {
        String str = "csc17", tstr = "";
	char ch;
	
	for (int i=0; i<nstr.length(); i++)
	{
		ch = nstr.charAt(i); //extracts each character
		tstr = ch+tstr; //adds each character in front of the existing string
	}
	
	return tstr;

    }//Reverse


    //  
    //Method to test if a string is a palindrome
    public static boolean palindrome(String s)
    {
        int c = 0, r = s.length() - 1; //point to the begining of a string and the end of the string
        while(c<r)
	{
 	    if (s.charAt(c) != s.charAt(r)) //Test to see string eqaulity 
		    return false;
	    c++;
	    r++;
	}
	return true;
     };



    public static boolean Dupe(String[] dstr)
    {
        duplicates = false;

	int i;
	int j;
	int k;

        for(i = 0; i < dstr.length; i++)
       	{	    
	    for(j = i + 1; k < dstr.length; j++) 
	    {
  		if(j != i && dstr[j] == dstr[i]) 
		{
   	  	    duplicates = true;
		}
	    }
        }


    }


}//methods

public class OneB
{

    public static void main(String[] args)
    {
       
	//  5.)    
	//inArray function test
	String[] SA = new String[a,b,c,s];
	inArray(SA);
        
        //Sum function test
	System.out.println(sum);
        
	//Smallest function test
	string[] SA = new string[];
        SA.smallest("bug","hi","guy","equip","rain");
        System.out.println(SA);	
        	
    
        //Reverse Function test
        System.out.println(tstr);

	//Palindrome Function test    
        String s = "HannaH";
	if(palindrome(s))
	    System.out.println("Yes This is a palindrome");
	else
	    System.out.println("No this is not a palindrome");

        //duplicate function test
	String[] dstr = new String[];
        dstr.Dupe("ah","bc","ah","hj","kl")
	
    }//main










}//OneB

import java.io.*;

class needelmen
{
    int w = 0;
    String A, B;
    int [][] twoDarr;
    byte [][] gaveMaxArr;
    int AlignmentScore; 

    public needelmen (String A,String B)
    {
        this.A = "." + A;
        this.B = "." + B;
        twoDarr = new int [A.length()][B.length()]; 
        AlignmentScore = (twoDarr[A.length()-1][B.length()-1]);
    }

    public void fill()
    {
        twoDarr [0][0] = 0;
        for (int x=0;x<A.length()-1;x++) 
        {
            for(int q=0;q<B.length();q++)
            {
                int A = -9000;
                int B = -9000;
                int C = -9000;
                if (x>=1 && q>=1){
                A = twoDarr [x-1][q-1] + score(x,q); //corner
                }
                if (x>=1){
                B = twoDarr[x-1][q] + w; 
                }
                if(q>=1){
                C = twoDarr[x][q-1] + w;
                }
                
                int max = Math.max(A,Math.max(B,C));
                twoDarr[x][q] = max;

                if(max == A) {gaveMaxArr [x][q] = 1;}
                if(max == B) {gaveMaxArr [x][q] = 2;}
                if(max == B) {gaveMaxArr [x][q] = 3;}
                
            }  
        }

    }

    public void traceBack()
    {
        int AInd = this.A.length()-1;
        int BInd = this.B.length()-1;
        String middle = "";
        String AOutput = "";
        String BOutput = "";
        

        while (AInd!=0 && BInd!=0)
        {
             int val = gaveMaxArr[AInd][BInd];
             if (val==1) 
             {
                AOutput = A.charAt(AInd)+ AOutput;
                BOutput = B.charAt(BInd)+ BOutput;
                middle = "|"+middle;
                AInd--; 
                BInd--;

             }
             else if(val==2)
             {
                AOutput = A.charAt(AInd)+ AOutput;
                BOutput = "_"+ BOutput;
                middle = " "+middle;
                AInd--; 
             }
            
             else if(val==3)
             {
                AOutput = "_"+ AOutput;
                BOutput = B.charAt(BInd)+ BOutput;
                middle = " "+middle;
                BInd--; 
             }

        }



    }

    public int score(int i,int k)
    {
        if (A.charAt(i) == B.charAt(k)) {return 1;}
        else {return 0;}
    }

}





public class DnaLab  
{
    public static void main(String[] av)
    {
       String A = "AACTG";
       String B = "ACTAG";
       needelmen debug = new needelmen(A, B);
       debug.fill();  
       for(int x=0;x<A.length()-1;x++)
       {
            for(int q=0;q<B.length();q++)
            {
                System.out.println(twoDarr[x][q] + "\t");
            }
            System.out.println();
       }
       System.out.println("Final Score is:",AlignmentScore)
    } 


} //DnaLab
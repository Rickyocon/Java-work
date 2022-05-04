abstract class needelmen
{
    int w = 0;
    String A, B;
    static int [][] twoDarr;
    byte [][] gaveMaxArr;
    static int AlignmentScore; 

    public needelmen(String A,String B)
    {
        this.A = "." + A;
        this.B = "." + B;
        twoDarr = new int [this.A.length()][this.B.length()]; 
        gaveMaxArr = new byte [this.A.length()][this.B.length()]; 
    }

    public void fill()
    {
        twoDarr [0][0] = 0;
        for (int x=0;x<A.length();x++) 
        {
            for(int q=0;q<B.length();q++)
            {
                int A = -9000;
                int B = -9000;
                int C = -9000;
                if (x>0 && q>0){
                A = twoDarr [x-1][q-1] + score(x,q); //corner
                }
                if (x>=1){
                B = twoDarr[x-1][q] + w; 
                }
                if(q>=1){
                C = twoDarr[x][q-1] + w;
                }
                if(x!=0 || q!=0)
                {
                    int max = Math.max(A,Math.max(B,C));
                    twoDarr[x][q] = max;
                    if(max == A) {gaveMaxArr [x][q] = 1;}
                    if(max == B) {gaveMaxArr [x][q] = 2;}
                    if(max == C) {gaveMaxArr [x][q] = 3;}
                }

                
                
            }  
        }
        //System.out.println(Arrays.deepToString(twoDarr)); 

    }

    public void traceBack()
    {
        int AInd = this.A.length()-1;
        int BInd = this.B.length()-1;
        String middle = "";
        String AOutput = "";
        String BOutput = "";
        

        while (AInd!=0 || BInd!=0)
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
        System.out.println(AOutput);
        System.out.println(middle);
        System.out.println(BOutput);
        System.out.println("Optimial allignment score is "+ twoDarr[A.length()-1][B.length()-1]);
    

    }

    public int score(int i,int k)
    {
        if (A.charAt(i) == B.charAt(k)) {return 1;}
        else {return 0;}
    }


}

class score2 extends needelmen
{
    public score2(String A,String B)
    {
        super(A,B);
    }
    
    
    @Override
    public int score(int A1,int B1)
    {
        if((A.charAt(A1) | 32) == (B.charAt(B1) | 32))
        {return 1;}
        else{return 0;}

    }

}





public class DnaLab
{
    public static String randseq(int n)
    {
        char[] S = new char[n];  // faster than building string char by char
        String DNA = "ACGT";
        for(int i=0;i<n;i++)
            S[i] = DNA.charAt((int)(Math.random()*4));
        return new String(S); // constructor converts char[] to String
    } // randseq

    public static void main(String[] av)
    {
        //String A = "AcGt";
        //String B = "aCgT";
       needelmen debug = new score2(randseq(5), randseq(5));  //chnage scoring scheme here
       debug.fill();  
       debug.traceBack();
    } 


} //DnaLab
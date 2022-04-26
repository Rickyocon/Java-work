import java.io.*;

class needelmen
{
    int w = 0;
    String A, B;
    int [][] twoDarr;

    public needelmen (String A,String B)
    {
        this.A = "." + A;
        this.B = "." + B;
        twoDarr = new int [A.length()][B.length()]; 
    }

    public void fill()
    {
        twoDarr [0][0] = 0;
        for (int x=0;x<A.length();x++) 
        {
            for(int q=0;q<B.length();q++)
            {
                int A = twoDarr [x-1][q-1] + score(x,q); //corner 
                int B = twoDarr[x-1][q] + w; 
                int C = twoDarr[x][q-1] + w;
                int max = Math.max(A,Math.max(B,C));
                twoDarr[x][q] = max;
            }  
        }

    }
}








class ScoringScheme1
{
    @Override
    public int score(int i, int k)
    {

    }

    @Override
    String fill (char i, char k)
    {
      
        
    }//fill


    String indices(char A, char B)
    {
        
    }
    
}//ScoringScheme 1

class ScoringScheme2 
{

    @Override
    public int score(int i, int k)
    {

    }

    @Override
    String fill (char i, char k)
    {
        
    }//fill


}


class ScoringScheme3 
{

    @Override
    public int score(int i, int k)
    {

    }

    @Override
    String fill (char i, char k)
    {
        
    }//fill


}


public class DnaLab ()
{

    public static void main(String[] argv)
    {
        // read and return string from file: String arman = read("arman.dna");
        public static String read(String file)
        {
        String s = "";
        try
            {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            s = br.readLine();
            br.close();
            }
            catch (IOException ie) {System.out.println("IO Error"); System.exit(1); }
        return s;
        }// read
    } 


} //DnaLab
public class maze extends mazebase
{
    // default constructor suffices and is equivalent to
    // public maze() { super(); }

 @Override
 public void digout(int y, int x)   // modify this function
 {
     // The following is a skeleton program that demonstrates the mechanics
     // needed for the completion of the program.

     // We always dig out two spaces at a time: we look two spaces ahead
     // in the direction we're trying to dig out, and if that space has
     // not already been dug out, we dig out that space as well as the
     // intermediate space.  This makes sure that there's always a wall
     // separating adjacent corridors.

     M[y][x] = 1;  // digout maze at coordinate y,x
     drawblock(y,x); 
     // change graphical display to reflect space dug out
     // nextframe(40); // show next animation frame after 40ms delay

     // But the following won't work (but will compile)

     // sample code that tries to digout one space to the left:
        //  if (x-1>=0) digout(y,x-1);
     // sample code that tries to digout TWO space to the right IF it's not
     // already dug out:

    
     int[] DX = {0,1,0,-1}, DY = {1,0,-1,0}; //These are the "corordinates" for the grid of the maze
 


     //This for loop is used to randomly scrable directions the maze will go in
     int[] P = {0,1,2,3}; // initial identity permutation //each number correspondes to a direction
     for (int i=0;i < P.length;i++)
     { 
         int r = i+(int)(Math.random()*P.length-i); // r is between i and P.length-1
	 int temp = P[i];   // swap each element with some random element.
         P[i] = P[r];
         P[r] = temp;
     }//Scrambel


     //This for loop is used to take a random selction of the scramble loop, and recursivly dig out and back track throughtout the maze
     for (int i=0; i<P.length; i++)
     {
         int nx = x+2 * DX[P[i]];
	 int ny = y+2 * DY[P[i]];

	 if(nx>0&&nx<mwidth && ny>=0&&ny<mheight && M[ny][nx]==0)
         {
             int mx = x+DX[P[i]];
	         int my = y+DY[P[i]];
	         M[ny][nx]=1;
	         drawblock(ny,nx);
	         M[my][mx]=1;
	         drawblock(my,mx);
	         digout(ny,nx); //Recursive call for P.length
         }



     }


 }//digout



//------------------------------------------------------------------------------------------------------------------------------------

 protected records[][] PATH;
 @Override
     // This method is used to solve the maze using a depth first search algorithum (finding the optimal path)
     public void solve()
     {
 
     PATH = new records[mheight][mwidth];
	 //These two lines are used to maunally digout the exit for the maze 
         M[mwidth-2] [mwidth-1] =  1;
         drawblock(mheight-2,mwidth-1); 

         int x = 1, y = 1; //initial cords of red dot

	 	
	 while (y!=mheight-2 ||  x!=mwidth-1)
         {
  
	         int bestx = 0;
	         int besty = 0;
	         int bestval;
      	     bestval = 0x7fffffff;


             //look east
	         if(x+1<mwidth && M[y][x+1]!=0 && M[y][x+1]<bestval)
             {
                 bestx = x+1;
	             besty = y;
	             bestval = M[y][x+1];
             }


	        //look south
	         if(y+1<mheight && M[y+1][x]!=0 && M[y+1][x]<bestval)
             {
                 bestx = x;
	             besty = y+1;
	             bestval = M[y+1][x];
	         }


             //look west
	         if(x-1>=0 && x<=mwidth && M[y][x-1]!=0 && M[y][x-1]<bestval)
             {
                 bestval = M[y][x-1];
	             bestx = x-1;
	             besty = y;
	         } 



             //look north
	         if(y-1>= 0 && y<=mheight && M[y-1][x] !=0 && M[y-1][x] < bestval)
             {
                 bestval = M[y-1][x];
	             bestx = x;
	             besty = y-1;
             }

             
             records B = new records(y,x);
             
             drawblock(y,x);
             y = besty;
             x = bestx;
             drawdot(y,x);
             nextframe(40);
	         M[y][x]++;


             if(PATH[y][x] == null)
             {
                 PATH[y][x] = B;
             }       

	 }//end of while loop
	 
       
     }//solve


//------------------------------------------------------------------------------------------------------------------------------------

public class records  // you can define this class inside or outside of your class
{
    int y;
    int x;
    public records(int a, int b) {y=a; x=b;}
} // simple class to record the a coordinate.

@Override
public void trace()
{
    int nx = mwidth-1;
    int ny = mheight-2;
    int x;
    int y;

    while(ny!=1 ||  nx!=1)
    {
          drawdot(ny,nx);
          nextframe(40);
          x = PATH[ny][nx].x;
          y = PATH[ny][nx].y;      
          nx = x;
          ny = y;

    }

    

}


//-------------------------------------------------------------------------------------------------------------------------------------

    public static void main(String[] av)
    {
	new maze(); // constructor of superclass will initiate everything
    }

    // other hints:  override customize to change maze parameters:
    @Override
    public void customize()
    {
	// ... can change mwidth, mheight, bw,bh, colors here
	//showvalue = true;
    }

}//maze subclass



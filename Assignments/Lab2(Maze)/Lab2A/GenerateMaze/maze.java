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
     drawblock(y,x);  // change graphical display to reflect space dug out
     nextframe(40); // show next animation frame after 40ms delay

     // But the following won't work (but will compile)

     // sample code that tries to digout one space to the left:
        //  if (x-1>=0) digout(y,x-1);
     // sample code that tries to digout TWO space to the right IF it's not
     // already dug out:
     /*
     if (x+2<mwidth && M[y][x+2]==0) // always check for maze boundaries
	 {
	     M[y][x+1] = 1;
	     drawblock(y,x+1);
	     digout(y,x+2);
	 }
    
	 ny = y+2*DY[dir];
     }
     */

    
     int[] DX = {0,1,0,-1}, DY = {1,0,-1,0};


     int[] P = {0,1,2,3}; // initial identity permutation
     for (int i=0;i < P.length;i++)
     { 
         int r = i+(int)(Math.random()*P.length-i); // r is between i and P.length-1
	 int temp = P[i];   // swap each element with some random element.
         P[i] = P[r];
         P[r] = temp;
     }

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
	     digout(ny,nx);
         }



     }





 }//digout

    public static void main(String[] av)
    {
	new maze(); // constructor of superclass will initiate everything
    }

    // other hints:  override customize to change maze parameters:
    @Override
    public void customize()
    {
	// ... can change mwidth, mheight, bw,bh, colors here
    }

}//maze subclass



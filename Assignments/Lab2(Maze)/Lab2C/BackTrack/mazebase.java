import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.*;

public class mazebase extends JFrame implements KeyListener
{
/* default values: */
protected static int mheight = 41;	
protected static int mwidth = 41;
// some config vars are static so they can be changed in main

protected int[][] M;	// the array for the maze
public static final int SOUTH = 0;
public static final int EAST = 1;
public static final int NORTH = 2;
public static final int WEST = 3;
    
protected boolean showvalue = false; // affects drawblock
protected boolean autodelay = false;  //delays automatically between drawdot
protected boolean usegif = false;
    
// graphical properties:
protected static int bh = 20; 	// height of a graphical block
protected static int bw = 20;	// width of a graphical block
protected int ah, aw;	// height and width of graphical maze (don't change)
protected static int yoff = 40;    // init y-cord of maze
protected Image screen1;    // static background buffer
protected Image screen2;    // animation frame buffer
protected Graphics g;  // draw to screen1
protected Graphics g2;  // draw to screen2    
protected Graphics dg; // draw to actual display
protected int dtime = 30;   // ms delay time (for autodelay)
protected Color wallcolor = Color.green;
protected Color pathcolor = Color.black;
protected Color dotcolor = Color.red;
protected Color pencolor = Color.yellow;    
protected Image animatedgif;
protected String gifname = "miner.gif";
protected int startdigx = 1;  // initial coordinates, first call to digout
protected int startdigy = 1;    


// constructor calls customize first, which can change above parameters
 public mazebase()
 { 
     //   bh = bw = bh0;  mheight = mh0;  mwidth = mw0;
   customize(); // optional startupcode  - change all vars here          
   ah = bh*mheight;
   aw = bw*mwidth;
   M = new int[mheight][mwidth];  // initialize maze (all  0's - walls).
   this.setBounds(0,0,aw+10,10+ah+yoff);	
   this.setVisible(true);
   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   this.addKeyListener(this);
   try{Thread.sleep(500);} catch(Exception e) {} // Synch with system
   screen1 = createImage(aw+10,10+ah+yoff);
   screen2 = createImage(aw+10,10+ah+yoff);   
   g = screen1.getGraphics();
   g2 = screen2.getGraphics();   
   dg = getGraphics();    //g.setColor(Color.red);
   setup();
 }

    // utility to load animated gif, called from setup after customize()
protected void loadgif(String filename)
 {
     try {
      animatedgif = Toolkit.getDefaultToolkit().getImage(filename);
      prepareImage(animatedgif,this);
      Thread.sleep(100); // Synch with system
     } catch(Exception e) {animatedgif=null; usegif=false;} 
 }//loadgif
    
    
public void paint(Graphics g) {} // override automatic repaint

public void setup()
   {
     g.setColor(wallcolor);
     g.fill3DRect(0,yoff,aw,ah,true);  // fill raised rectangle
     g.setColor(pathcolor);
     try {
        g.setFont(new Font("Serif",Font.BOLD,bh*3/4));      // might not work
     } catch(Exception gfe) {}
     /*if (usegif)*/ loadgif(gifname); // placed here so user can define gif usage
     //     showStatus("Generating maze...");
     //digout(mheight-2,mwidth-2); // start digging!  (lab 2)
     digout(startdigy,startdigx);  // for dragon game
     // draw to screen
     clear();
     refresh();
     /*
     // digout exit square (if digout complete, works for odd dimensions)
     if (M[mheight-2][mwidth-2]!=0)
	 {
	     //M[mheight-1][mwidth-2] =
	     M[mheight-2][mwidth-1] = 1;
	     drawblock(mheight-2,mwidth-1);
	 }
     */
     solve();  // this is the function you will write for lab 3, part 1
     trace();  // for part 2
     play();   // for part 3
   }   


    public void clear()
    {
        g2.drawImage(screen1,0,0,this);        //draw to screen2
    }
    public void refresh()
    {
     dg.drawImage(screen2,0,0,this);             
    }
    public void nextframe()
    { clear(); refresh(); }
    public void nextframe(int msdelay) // with delay time in ms
    { delay(msdelay); clear(); refresh(); }
    
public void delay(int ms)
    {   
	try {Thread.sleep(ms);} catch(Exception e) {}
    }

public void drawblock(int y, int x)
    {
        drawblock(g,y,x);
    }
public void drawblock(Graphics g,int y, int x)
    {
	g.setColor(pathcolor);
	g.fillRect(x*bw,yoff+(y*bh),bw,bh);
	g.setColor(pencolor);
	// following line displays value of M[y][x] in the graphical maze:
	if (showvalue)
	  g.drawString(""+M[y][x],(x*bw)+(bw/2-4),yoff+(y*bh)+(bh/2+6));
    }    

public void drawdot(int y, int x)
    {
        drawdot(g,y,x);
    }
public void drawdot(Graphics g, int y, int x)
    {
	if (usegif && animatedgif!=null)
	    {
		g.drawImage(animatedgif,x*bw,yoff+(y*bh),bw,bh,null);
	    }
	else
	    {
		g.setColor(dotcolor);
		g.fillOval(x*bw,yoff+(y*bh),bw,bh);
	    }
        if (autodelay) try{Thread.sleep(dtime);} catch(Exception e) {} 
    }    
public void drawgif(int y, int x) { drawdot(y,x); }  //alias

public void drawgif(Graphics g, Image gif, int y, int x)
    {
        g.drawImage(gif,x*bw,yoff+(y*bh),bw,bh,null);        
    }

public void drawMessage(String m)
    {
	g.setColor(wallcolor);
	g.fillRect(0,yoff,bw*mwidth,bh);
	g.setColor(pencolor); // erase line
        g.drawString(m,10,yoff+bh-4);	
    }

////// the following functions are to be overriden in subclass:

public void customize()  // user-defined initialization code
{} // this is called before digout

/* function to generate random maze */

public void digout(int y, int x)    // override for lab 2 (maze generation)
 {
     // generates maze - code in subclass
 } // digout


    /* Write a routine to solve the maze.
       Start at coordinates x=1, y=1, and stop at coordinates
       x=mwidth-1, y=mheight-2.  This coordinate was especially dug out
       after the program called your digout function (in the "actionPerformed"
       method).
    */
  public void solve()    // override for lab 3 part 1
  {
      int x=1, y=1;
      //      drawdot(y,x);
      // drawblock(y,x) will erase the dot
      // modify this function to move the dot to the end of the maze.  That
      // is, when the dot reaches y==mheight-2, x==mwidth-2
  } // solve

  public void trace()     // override for lab 3 part 2, 
  {  // draw a dot (without erasing it) along the OPTIMAL path
  }

    ///////////////////////////////////////////////////////////////
    /// For part three (save a copy of part 2 version first!), you
    // need to implement the KeyListener interface.

    public void play() // override for lab 3, final part
    {
	// code to setup game
    }
    // for this part you may also define some other instance vars outside of
    // the play function.

   // skeleton implementation of KeyListener interface
   public void keyReleased(KeyEvent e) {}
   public void keyTyped(KeyEvent e) {}
   public void keyPressed(KeyEvent e) // override for key event handling
    {
	int key = e.getKeyCode();       // code for key pressed      
	System.out.println("YOU JUST PRESSED KEY "+key);
    }
    
    /*
    // main:   
  public static void main(String[] args) throws Exception
    {
       int n = args.length;
       if (n==1 || n==4) subclass = args[0];
       int blocksize = bh, mh = 45, mw = 45; // width/height need to be odd
       if (n==4 || n==3)
	   {
	       mh=Integer.parseInt(args[n-3]);
	       mw=Integer.parseInt(args[n-2]);
	       blocksize=Integer.parseInt(args[n-1]);
	   }
       //       mazebase W = new mymazecode(blocksize,mh,mw);
    }//main
   */

    // main should be in subclass
} // mazebase

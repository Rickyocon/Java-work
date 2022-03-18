/* graphical representation of complete binary trees - see main for usage*/

import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;

/*
    HeapDisplay draws an array of objects as a complete binary tree, 
    upto index size-1.
*/

public class HeapDisplay extends JFrame
{

    public int XDIM, YDIM;  // window dimensions
    public Graphics display;
    public static int delaytime = 0; // animation delay

    Object[] H; // array to draw.
    int size; // size of portion of array to draw

    public void paint(Graphics g) {drawtree(H,size);} // override method

    // constructor sets window dimensions
    public HeapDisplay(int x, int y)
    {
	XDIM = x;  YDIM = y;
	this.setBounds(0,0,XDIM,YDIM);
	this.setVisible(true); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	display = this.getGraphics();
	// draw static background as a black rectangle
	display.setColor(Color.black);
	display.fillRect(0,0,x,y);
        display.setColor(Color.red);
	try{Thread.sleep(500);} catch(Exception e) {} // Synch with system
    }  // drawingwindow


    // internal vars used by drawtree routines:
    private int bheight = 50; // default branch height
    private int yoff = 30;  // static y-offset

    // N is index of node,
    // lv is level, lb,rb are the bounds (position of left and right child)
    private void drawnode(int N,int lv, int lb, int rb)
    {
	if (N<0 || N>=size) return;
	try{Thread.sleep(delaytime);} catch(Exception e) {} // slow down
	// draw the value at node index N:
        display.setColor(Color.green);
	display.fillOval(((lb+rb)/2)-10,yoff+(lv*bheight),20,20);
	display.setColor(Color.black);
	display.drawString(H[N]+"",((lb+rb)/2)-8,yoff+15+(lv*bheight));
	// draw the index number N:
	display.setColor(Color.red);
	display.drawString(N+"",(lb+rb)/2+10,yoff+(lv*bheight));

	// draw branches:
	display.setColor(Color.blue); 
	int l = 2*N+1, r = 2*N+2; // indices of left, right

        if (l<size) // if l exists
	    {
   	       display.drawLine((lb+rb)/2,yoff+10+(lv*bheight),
			((3*lb+rb)/4),yoff+(lv*bheight+bheight));
               drawnode(l,lv+1,lb,(lb+rb)/2);
	    }
        if (r<size)
	    {
               display.drawLine((lb+rb)/2,yoff+10+(lv*bheight),
			((3*rb+lb)/4),yoff+(lv*bheight+bheight));
               drawnode(r,lv+1,(lb+rb)/2,rb);
	    }
    } // drawnode

    public void drawtree(Object[] T, int s)
    {
        if (T==null || s<1 || s>T.length) return;
	H = T; size = s; 
	// compute depth:
	int d = (int)(Math.log(size)/Math.log(2))+1;
	//System.out.println("depth:"+ d);  System.out.flush();
	bheight = (YDIM/d); // graphical height of each level
	display.setColor(Color.white);
	display.fillRect(0,0,XDIM,YDIM);  // clear background
        drawnode(0,0,0,XDIM);
    }

    public static Object[] cast(int[] A)
    {
	Object[] B = new Object[A.length];
	for(int i=0;i<A.length;i++) B[i] = (Integer)A[i];
	return B;
    }


    public static Object[] cast(double[] A)
    {
	Object[] B = new Object[A.length];
	for(int i=0;i<A.length;i++) B[i] = (Double)A[i];
	return B;
    }

    /* sample use:  (put this in another file) **************
    public static void main(String[] args)
    {
      HeapDisplay W = new HeapDisplay(1024,768); //create window of size
      //      int[] H = {0,1,2,3,4,5,6,7,8,9};
      int n = 20;
      if (args.length>0) n = Integer.parseInt(args[0]);
      int[] H = new int[n];  // can also have array of doubles
      for(int i=0;i<n;i++) H[i] = (int)(Math.random()*100);
      W.drawtree(HeapDisplay.cast(H),H.length);
      W.display.drawString("Do you like my heap?",20,W.YDIM-50);

      try{Thread.sleep(5000);} catch(Exception e) {} // 5 sec delay

      String[] S = {"ab","cd","ef","gh","zz","aa","ee","rr","qq","ii"};
      W.drawtree(S,S.length);
    }  // main

    // Additional notes: to cast arrays of ints or doubles so it can be
    // displayed, call HeapDisplay.cast(..) as above.  If you already have
    // an array of objects, such as String[], you can pass it directly.
    ********************/

} // HeapDisplay


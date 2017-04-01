package Tetris;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class Pane extends JPanel implements MouseListener,MouseMotionListener,MouseWheelListener  
{
    // get the current cursor coordination;
    private int x = 0;
    private int y = 0;
    
//    MouseListener listener = new MouseListener();
    
    
	public Pane() {
	        this.addMouseMotionListener(this);
	        this.addMouseListener(this);
	        this.addMouseWheelListener(this);
	      
	    }

	 
	 public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //draw quit button
        DrawButtons(g);

        //draw the whole JFrame with JPanel
        DrawSence(g);

        //draw the next object area
        DrawNexobj(g);

        //Once cursor on the main area, show "PAUSE"
        DetectArea(g);


    }

    public void DetectArea(Graphics g)
    {

        //set font detail for the "PAUSE" in main area
        Font ff = new Font(null, 1, 30);
        g.setFont(ff);

        //if mouse move in main area then set thread.start equal to false
        if((x<=252)&&(x>=10)&&(y>=10)&&(y<=504))
        {
            g.drawString("PAUSE!", 100,250);
            MyThread.start = false;

        }
        else{
            MyThread.start = true;
        }

    }

    public void DrawButtons(Graphics g)
    {

        //draw quit button
        g.drawRect(270,440,70,30);
        g.setColor(Color.RED);
        g.fillRect(271,441,68,28);
        g.setColor(Color.BLACK);
        Font f = new Font(null, 2, 17);
        g.setFont(f);
        g.drawString("QUIT", 285,460);
    }

    public void DrawNexobj(Graphics g)
    {

        g.setColor(Color.BLUE);
        Font f = new Font(null, 1, 20);
        g.setFont(f);
        g.drawString("Next:", 270, 40);
        int sx = 6;
        switch(MyThread.NexObj.type)
        {
        case 1:
            g.setColor(Color.RED);
            break;
        case 2:
            g.setColor(Color.CYAN);
            break;
        case 3:
            g.setColor(Color.YELLOW);
            break;
        case 4:
            g.setColor(Color.BLUE);
            break;
        case 5:
            g.setColor(Color.PINK);
            break;
        case 6:
            g.setColor(Color.green);
            break;
        case 7:
            g.setColor(Color.ORANGE);
            break;
        }
        for(int i = 0; i < 4; i++)
        {
            //reset the coordinate x to "0"
            int x = MyThread.NexObj.pos[i].x - sx;
            int y = MyThread.NexObj.pos[i].y;
            //every time draw the square with 20 width and 20 height. totally 4 squares.
            g.fillRect(310 + x * 21, 60 + y * 21, 20, 20);
        }
    }
    public void DrawWords(Graphics g)
    {
        g.setColor(Color.BLUE);
        Font f = new Font(null, 1, 18);
        g.setFont(f);
        float speedf=(float)MyThread.Speed/10;
        speedf+=0.1;
        g.drawString((new StringBuilder("Score: ")).append(MyThread.Score * 10).toString(), 285, 170);
        g.drawString((new StringBuilder("Speed: ")).append(speedf).toString(), 285, 200);
        g.drawString((new StringBuilder("Level: ")).append(MyThread.N_Number_of_Rows).toString(), 285, 230);
        g.drawString((new StringBuilder("Obstacle: ")).append(MyThread.Level).toString(), 285, 260);
        g.setColor(Color.BLACK);
        g.drawString("Designed ", 285, 320);
        g.drawString("by Fan", 285, 340);
        g.drawString("2016.09", 285, 360);

    }

    public void DrawSence(Graphics g)
    {


        // set the background color as black
        g.setColor(Color.BLACK);
        g.fill3DRect(1, 1, 253, 505, false);

        DrawWords(g);

        int n = 0;
        int i = 0;

        //start drawing squares frome here
        switch(MyThread.CurObj.type)
        {
        case 1:
            g.setColor(Color.RED);
            break;
        case 2:
            g.setColor(Color.CYAN);
            break;
        case 3:
            g.setColor(Color.YELLOW);
            break;
        case 4:
            g.setColor(Color.BLUE);
            break;
        case 5:
            g.setColor(Color.PINK);
            break;
        case 6:
            g.setColor(Color.green);
            break;
        case 7:
            g.setColor(Color.ORANGE);
            break;
        }
        //draw the current object before touch the bottom obstacle
        for(i = 0; i < 4; i++)
            g.fillRect(2 + MyThread.CurObj.pos[i].x * 21, 1 + MyThread.CurObj.pos[i].y * 21, 20, 20);

        //draw the dropped object when touch the bottom obstacle
        for(n = 0; n < 24; n++)
            for(i = 0; i < 12; i++){
                if(MyThread.EV[i][n] != 0){
                	switch(MyThread.EV[i][n])
                    {
                    case 1:
                        g.setColor(Color.RED);
                        break;
                    case 2:
                        g.setColor(Color.CYAN);
                        break;
                    case 3:
                        g.setColor(Color.YELLOW);
                        break;
                    case 4:
                        g.setColor(Color.BLUE);
                        break;
                    case 5:
                        g.setColor(Color.PINK);
                        break;
                    case 6:
                        g.setColor(Color.green);
                        break;
                    case 7:
                        g.setColor(Color.ORANGE);
                        break;
                    }
                    g.fillRect(2 + i * 21, 1 + n * 21, 20, 20);
                }
            }
    }

	@Override
	public void mouseDragged(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void mouseMoved(MouseEvent me) {
		// TODO Auto-generated method stub
		 x = me.getX();
	     y = me.getY();
	        repaint();
	}




	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(SwingUtilities.isLeftMouseButton( e)){


            // if the mouse click the quit button area, then the program quit!
	        if((x<=340)&&(x>=270)&&(y>=440)&&(y<=470)){
	        	System.exit(0);
	        }
			
			

	            	System.out.println("move-left");
	                MyThread.Move(-1);

			  
			  
			  
		}else if(SwingUtilities.isRightMouseButton(e)){


	            	System.out.println("move-right");
	                MyThread.Move(1);

			 
			 
		}
		
	}




	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
            {
                // TODO Auto-generated method stub
                    if(e.getWheelRotation()==1){
                        if(MyThread.CurObj.type != 1)
                        MyThread.Filp();
                            }
                                else if(e.getWheelRotation()==-1){
                                    if(MyThread.CurObj.type != 1)
                                    MyThread.FilpUnclock();
                                }

		
	        }
}

package Tetris;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame
    implements KeyListener
{
    JMenuBar menu;
    JMenu menu1,menu2;
    JMenuItem menuitem1;
    JMenuItem menuitem2;
    JMenuItem menuitem3;
//    JMenuItem menuitem4;
    JMenuItem menuitem5;
    public MyThread th;
    public int i;
    public MainFrame()
    {
    	
    	
    	setTitle("UT-Tetris");
       
        menu = new JMenuBar();
        menu1 = new JMenu("Menu");
  
        menuitem1 = new JMenuItem("Start");
        menuitem2 = new JMenuItem("Pause/Resume");
        menuitem3 = new JMenuItem("End");
//        menuitem4 = new JMenuItem("Quit");
        menuitem5 = new JMenuItem("Settings");
        i = 0;
        Pane panel = new Pane();
       
        // set the whole pane for the Main area and right edge
        setSize(440, 560);
        setResizable(true);
        menu1.add(menuitem1);
        menu1.add(menuitem2);
        menu1.add(menuitem3);
//        menu1.add(menuitem4);
        menu1.add(menuitem5);
        menu.add(menu1);

        this.setJMenuBar(menu);
        menuitem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                th = new MyThread();
                th.start();
              
            }
        }
);
        menuitem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                i++;
                if(i >= 2)
                    i = 0;
                if(i == 1){
                    MyThread.start = false;
                    
                }
                if(i == 0){
                    MyThread.start = true;
                   
                }
            }
        }
);
        menuitem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                MyThread.start1 = false;
                MyThread.isDead = true;
                MyThread.SetCruObjZero();
            }
        }
);
        
//        menuitem4.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e)
//            {
//                System.exit(0);
//            }
//        }
//);
       
        menuitem5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
                                        {
                                           Setting sets = new Setting();
                                           
                                        }
                                    }
        );
        addKeyListener(this);

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void keyPressed(KeyEvent ke)
    {
        switch(ke.getKeyCode())
        {
        default:
            break;
//W
        case 0x57:
            if(!MyThread.start)
            {
                MyThread.Level++;
                if(MyThread.Level > 14)
                    MyThread.Level = 0;
                MyThread.main.repaint();
            } else
            {
                MyThread.Filp();
            }
            break;
            // keyboard left arrow
            case 0x25:MyThread.Move(-1);break;
            // keyboard up arrow
            case 0x26:MyThread.Filp();break;
            // keyboard right arrow
            case 0x27:MyThread.Move(1);break;
            // keyboard down arrow
            case 0x28: MyThread.fall();break;
//A
        case 0x41:
            if(!MyThread.start)
            {
                MyThread.Speed--;
                if(MyThread.Speed < 0)
                    MyThread.Speed = 8;
                MyThread.main.repaint();
            } else
            {
                MyThread.Move(-1);
            }
            break;
//D
        case 0x44:
            if(!MyThread.start)
            {
                MyThread.Speed++;
                if(MyThread.Speed > 8)
                    MyThread.Speed = 0;
                MyThread.main.repaint();
            } else
            {
                MyThread.Move(1);
            }
            break;
//S
        case 0x53:
            if(!MyThread.start)
            {
                MyThread.Level--;
                if(MyThread.Level < 0)
                    MyThread.Level = 14;
                MyThread.main.repaint();
            } else
            {
                MyThread.fall();
            }
            break;
        }
    }

    public void keyReleased(KeyEvent ke)
    {
        if(ke.getKeyCode() == 40)
            MyThread.fall = false;
    }
}

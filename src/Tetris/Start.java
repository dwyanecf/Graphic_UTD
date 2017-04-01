package Tetris;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Start {
	 public Start()
	    {
	    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        Dimension frameSize = MyThread.main.getSize();
	        
	        if(frameSize.height > screenSize.height)
	            frameSize.height = screenSize.height;
	        if(frameSize.width > screenSize.width)
	            frameSize.width = screenSize.width;
	        MyThread.main.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	        MyThread.main.setVisible(true);
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
    	{
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception exception){
        	exception.printStackTrace();
        }
        new Start();
		
	}

}



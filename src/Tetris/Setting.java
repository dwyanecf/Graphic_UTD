package Tetris;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;

public class Setting {

	private JFrame frame;

	public Setting() {
		
		
		initialize();
		frame.setVisible(true);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setLocation(600,300);
		frame.setSize(600, 288);
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][][][][][][][]", "[][][][][][][][][][][][]"));
		
		JLabel lblMscoringFactor = new JLabel("Score difficulty");
		frame.getContentPane().add(lblMscoringFactor, "cell 0 1");
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		frame.getContentPane().add(spinner, "cell 2 1,alignx center");
		
		JLabel lblNlevelDifficulty = new JLabel("Level difficulty");
		frame.getContentPane().add(lblNlevelDifficulty, "cell 4 1");
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(20, 20, 50, 1));
		frame.getContentPane().add(spinner_1, "cell 5 1");
		
		JLabel lblSspeed = new JLabel("Speed");
		frame.getContentPane().add(lblSspeed, "cell 8 1");
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		frame.getContentPane().add(spinner_2, "cell 9 1");
		
		JLabel lblSizeOfSquares = new JLabel("Size of squares");
		frame.getContentPane().add(lblSizeOfSquares, "cell 0 3");
		
		JSlider slider = new JSlider();
		frame.getContentPane().add(slider, "cell 2 3");
		
		JLabel lblWidth = new JLabel("Width");
		frame.getContentPane().add(lblWidth, "cell 0 4");
		
		JSlider slider_1 = new JSlider();
		frame.getContentPane().add(slider_1, "cell 2 4");
		
		JLabel lblHeight = new JLabel("Height");
		frame.getContentPane().add(lblHeight, "cell 0 5");
		
		JSlider slider_2 = new JSlider();
		frame.getContentPane().add(slider_2, "cell 2 5");
		
		JLabel lblAllowOneSquare = new JLabel("Allow one square");
		frame.getContentPane().add(lblAllowOneSquare, "cell 0 7");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Check for yes");
		frame.getContentPane().add(chckbxNewCheckBox, "cell 2 7");
		
		JLabel lblAllowTwoSquares = new JLabel("Allow two squares?");
		frame.getContentPane().add(lblAllowTwoSquares, "cell 0 8");
		
		JCheckBox chckbxCheckForYes_1 = new JCheckBox("Check for yes");
		frame.getContentPane().add(chckbxCheckForYes_1, "cell 2 8");
		
		JLabel lblAllowThreeSquares = new JLabel("Allow three squares?");
		frame.getContentPane().add(lblAllowThreeSquares, "cell 0 9");
		
		JCheckBox chckbxCheckForYes = new JCheckBox("Check for yes");
		frame.getContentPane().add(chckbxCheckForYes, "cell 2 9");
		
		JButton btnNewButton = new JButton("Save & quit");
		frame.getContentPane().add(btnNewButton, "cell 9 11");
		
		btnNewButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 frame.setVisible(false);
	        	 frame.dispose();
	         }
	     }
	);
		
	}

	
	
}

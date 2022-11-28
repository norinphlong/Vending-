// Basic GUI for vending machine keypad. 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class VirtualKeyboard extends JPanel implements ActionListener {
	
	JLabel display;
	JButton numButton;
	JButton clearButton;
	String displayContent = "";
	String[] numPadContent = {"1","2","3","4","5","6","7","8","9","*","0","#", "A","B","C"};
	ArrayList<JButton> buttonList;
	
	// Keypad constructor class
	public VirtualKeyboard(Container pane) {
		// sets the size of the Keypad display
		pane.setPreferredSize(new Dimension(320, 335));
		
		// initialize display to hold displayContent
		display = new JLabel(displayContent);
		display.setPreferredSize(new Dimension(320, 25));
		// create lowered bevel border around the display
		display.setBorder(BorderFactory.createLoweredBevelBorder());
		// add the display to the panel
		pane.add(display, BorderLayout.PAGE_START);
		
		// initialize the buttonList
		buttonList = new ArrayList<JButton>(16);
		JPanel numberPanel = new JPanel();
		// set the numberPanel to have a 5row by 3col grid layout
		numberPanel.setLayout(new GridLayout(5,3,0,0));
		// set the size of the numberPanel
		numberPanel.setPreferredSize(new Dimension(320,260));
		// create the buttons and add them to the buttonList, properly displaying the numbers 
		for (int i = 0; i < numPadContent.length; i++) {
			numButton = new JButton(numPadContent[i]);
			buttonList.add(numButton);
		}
		// add the buttonList to the number panel
		for (int n = 0; n < buttonList.size(); n++) {
			buttonList.get(n).addActionListener(this);
			numberPanel.add(buttonList.get(n));
		}
		
		// create black border around the number panel
		numberPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		// add number panel to center part of display
		pane.add(numberPanel, BorderLayout.LINE_END);
		
		// create Clear button that is actionable
		clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(320, 30));
		clearButton.addActionListener(this);
		// add Clear button to bottom of display
		pane.add(clearButton, BorderLayout.PAGE_END);
	}
	
	// update the display depending on clicked button(s)
	public void actionPerformed(ActionEvent e) {
		String textThere = display.getText();
		String additionalText = "";
		// add clicked number button text to display
		for (int a = 0; a < buttonList.size(); a++) {
			if (e.getSource().equals(buttonList.get(a))) {
				additionalText = buttonList.get(a).getText();
			}
		}
		
		// clear display if "Clear" button is clicked
		if (e.getSource().equals(clearButton)) {
			textThere = "";
		}
		display.setText(textThere.concat(additionalText));
	}
	
	public static void main(String[] args) {
		
		//create and set up the window.
		JFrame frame = new JFrame("Numeric Keyboard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set up the content pane.
		frame.getContentPane().add(new VirtualKeyboard(frame));
        
		frame.pack();
		frame.setVisible(true);
	}
}

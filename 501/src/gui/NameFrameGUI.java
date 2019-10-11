package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;

/**
 * The Class generates the JFrame to get the number of players and each players names. 
 * It also allows the user which board to use.
 * @author Sehajveer Bring
 *
 */
public class NameFrameGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * sets the text of lables and buttons, and text fields 
	 */
	
	private int numOfPlayers;
	
	private JLabel msg1= new JLabel("Enter name for Player 1:");
	private JLabel msg2= new JLabel("Enter name for Player 2:");
	private JLabel msg3= new JLabel("Enter name for Player 3:");
	private JLabel msg4= new JLabel("Enter name for Player 4:");
	private JLabel msg5= new JLabel("Enter name for Player 5:");
	private JLabel msg6= new JLabel("Enter name for Player 6:");
	private JLabel msg7= new JLabel("Enter name for Player 7:");
	private JLabel msg8= new JLabel("Enter name for Player 8:");
	private JLabel players = new JLabel("How many players (2-8)?");

	private JPanel blank= new JPanel();
	private JPanel content= new JPanel();
	private JPanel playerNamePanel = new JPanel();
	private JPanel numOfPlayersPanel = new JPanel();

	private JTextField entry1= new JTextField(1);
	private JTextField entry2= new JTextField(1);
	private JTextField entry3= new JTextField(1);
	private JTextField entry4= new JTextField(1);
	private JTextField entry5= new JTextField(1);
	private JTextField entry6= new JTextField(1);
	private JTextField entry7= new JTextField(1);
	private JTextField entry8= new JTextField(1);
	
	public ArrayList <JTextField> textFeild = new ArrayList<JTextField> (Arrays.asList(entry1, entry2, entry3, entry4, entry5, entry6, entry7, entry8));
	private ArrayList <JLabel> nameLabels = new ArrayList <JLabel>(Arrays.asList(msg1, msg2, msg3, msg4, msg5, msg6, msg7, msg8));
	
	/**
	 * The dropdown menu
	 */
	
	public JComboBox <Integer> numOfPlayer = new JComboBox <Integer>();
	
	private JButton nextButton = new JButton ("Declare Names");
	private JButton regularBoard= new JButton("Regular Game");
	private JButton canadaianBoard = new JButton("Canadian Game");

	/**
	 * initilization of frame
	 * Sets the jframe and adds components to it
	 * @param listener is for listening to when buttons are pressed
	 */
	public NameFrameGUI(ActionListener listener){
		super("Monopoly Game");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		setJTextFieldVisability ();
		content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
		content.add(setNumOfPlayersPanel(listener));
		content.add(blank);
		content.add(getPlayerNamesPanel());
		regularBoard.setActionCommand("REGULAR");
		regularBoard.addActionListener(listener);
		canadaianBoard.setActionCommand("CANADIAN");
		canadaianBoard.addActionListener(listener);
		super.getContentPane().add(content);
		super.setSize(new Dimension (400, 350));
	}

	/**
	 * Jpanel of all the text fields and lables
	 * @return a JPanel that contains the text fields, buttons and the labels present in the JFrame
	 */
	public JPanel getPlayerNamesPanel() {
		playerNamePanel.setLayout(new BoxLayout(playerNamePanel, BoxLayout.Y_AXIS));		
		for (int y=0; y < 8; y++) {
			JPanel o3 = new JPanel();
			o3.setLayout(new BoxLayout(o3, BoxLayout.X_AXIS));
			o3.add(nameLabels.get(y));
			o3.add(textFeild.get(y));
			playerNamePanel.add(o3);
			playerNamePanel.add(blank);
		}
		playerNamePanel.add(blank);
		regularBoard.setAlignmentX(Component.RIGHT_ALIGNMENT);
		canadaianBoard.setAlignmentX(Component.LEFT_ALIGNMENT);
		playerNamePanel.add(regularBoard);
		playerNamePanel.add(canadaianBoard);
		playerNamePanel.setVisible(true);
		return playerNamePanel;
	}
	/**
	 * Sets the text fields to be editable, depending on number of players requested
	 */
	public void visibleTextFeilds() {
		setJTextFieldVisability ();
		for (int x = 0; x < numOfPlayers; x++) {
			JTextField a = textFeild.get(x);
			if (a.isEditable()== false) {
				a.setEditable(true);
			}
		}

	}
	
	/**
	 * JPanel that the set button is added to. Once pressed, confirms the number of players returned by 
	 * the JComboBox and makes that number of text fields visible
	 * @param listen for when the button is pressed
	 * @return the JPanel with the JCombobox and button on it
	 */
	public JPanel setNumOfPlayersPanel(ActionListener listen) {
		numOfPlayersPanel.setLayout(new BoxLayout(numOfPlayersPanel, BoxLayout.X_AXIS));
		numOfPlayersPanel.add(players);
		numOfPlayersPanel.add(numberOfPlayersMenu(listen));
		numOfPlayersPanel.add(setNextButton(listen));
		nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		numOfPlayersPanel.setVisible(true);
		return numOfPlayersPanel;
	}
	
/**
 * Selects number of requested players, from 2-8. returns that number
 * @param listen to which number is selected
 * @return the created JCombobox
 */
	public JComboBox numberOfPlayersMenu (ActionListener listen) {
		for (int x=2; x < 9; x++) {
			numOfPlayer.addItem(x);
		}
		numOfPlayer.addActionListener(listen);
		return numOfPlayer;
	}

/**
 * The button used in setNumOfPlayersPanel
 * @param listen for when the button is pressed
 * @return the created button
 */
	public JButton setNextButton (ActionListener listen) {
		nextButton.addActionListener(listen);
		nextButton.setActionCommand("NEXT");
		return nextButton;
	}
	
	/**
	 * Sets the text fields to uneditable
	 */
	public void setJTextFieldVisability () {
		entry1.setEditable(false);
		entry2.setEditable(false);
		entry3.setEditable(false);
		entry4.setEditable(false);
		entry5.setEditable(false);
		entry6.setEditable(false);
		entry7.setEditable(false);
		entry8.setEditable(false);
	}
	
	/**
	 * 
	 * @param set the visabliity of the top half of the JFrame
	 */
	public void setNumbersVisibilityPanel(boolean visible) {
		numOfPlayersPanel.setVisible(visible);
	}
	/**
	 * 
	 * @param set the visibility of the bottom half on the JFrame
	 */
	public void setNamesPanelVisibilty (boolean visible) {
		playerNamePanel.setVisible(visible);
	}

	/**
	 * 
	 * @return the number of players playing the game
	 */
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	/**
	 * Sets the number of players playing the game
	 * @param players the number of players to set
	 */
	public void setNumOfPlayers(int players) {
		this.numOfPlayers = players;
	}
	
	/**
	 * 
	 * @return the Arraylist for the number of players selected
	 */
	public ArrayList<JTextField> getTextFeild() {
		return textFeild;
	}
}
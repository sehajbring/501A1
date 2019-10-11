package gui;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

/**
 * This class creates the main JFrame and and displays everything on that JFrame.
 * @author Sehjaveer Bring
 *
 */

public class BoardGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *instance variables for the picture displayed, the nextTurn button, the roll button, the exit button, the pass button, the player's name("nameLabel")label
	 * the player's money("moneyLabel")label, the property's rent("rentLable")label, the cost of property()label, property display("propertyLabel")label,
	 */

	private ImageIcon canadianBoard ;
	private ImageIcon regularBoard;

	private boolean isRegular;

	private ImageIcon diceBlank;
	private ImageIcon dice1;
	private ImageIcon dice2;
	private ImageIcon dice3;
	private ImageIcon dice4;
	private ImageIcon dice5;
	private ImageIcon dice6;
	private ImageIcon chanceImage;

	private JButton buy = new JButton ("Buy");
	private JButton exit = new JButton ("Exit game");
	private JButton roll = new JButton ();
	private JButton pass = new JButton ("Pass");
	private JButton nextTurn = new JButton ("Next turn");
	private JButton communityCard = new JButton ();

	private JLabel regularBoardImage;
	private JLabel canadianBoardImage;
	
	private JLabel emptyLabel= new JLabel("  ");
	private JLabel emptyLabel2= new JLabel("  ");
	private JLabel emptyLabel3= new JLabel("  ");
	private JLabel emptyLabel4= new JLabel("  ");
	private JLabel emptyLabel5= new JLabel("  ");
	private JLabel rollButtonInstruction = new JLabel("Dice Instruction: ");
	private JLabel rollButtonInstruction2 = new JLabel("Click the dice image to roll the dice. ");
	private JLabel nextTurnButtonInstruction = new JLabel("Next Turn Button Instruction: ");
	private JLabel nextTurnButtonInstruction2 = new JLabel("Once your turn end");
	private JLabel nextTurnButtonInstruction3 = new JLabel("Click the next turn button the change to the next player's turn ");
	private JLabel endGameButtonInstruction = new JLabel("End Turn Button Instruction: ");
	private JLabel endGameButtonInstruction2 = new JLabel("If you want to end the game click end game button ");
	private JLabel passButtonInstruction = new JLabel("Pass Button Instruction: ");
	private JLabel passButtonInstruction2 = new JLabel("Click the pass button if you don't want to buy the property. " );
	private JLabel buyButtonInstruction = new JLabel("Buy Button Instruction: ");
	private JLabel buyButtonInstruction2 = new JLabel("Click the buy button if you want to buy the property. " );

	
	private JLabel propertyNameLabel = new JLabel("Property name: ");
	private JLabel valueLabel = new JLabel("Value: ");
	private JLabel rentLabel = new JLabel("Rent: ");
	private JLabel ownerLabel= new JLabel("Owner: ");
	private JLabel playerNameLabel = new JLabel("Player name: ");
	private JLabel moneyLabel = new JLabel("Money: ");
	private JLabel chanceLabel = new JLabel();

	private JPanel buttonPanel = new JPanel();
	private JPanel gameBoard = new JPanel();
	private JPanel InstructionPanel = new JPanel();
	private JPanel informationPanel = new JPanel();
	private JPanel container = new JPanel ();
	private JPanel board = new JPanel ();
	private JPanel empty = new JPanel();
	private JPanel Instruction = new JPanel();

	private int screenWidth;
	private int screenHeight;
	private int regularBoardLayer = 0;
	private int canadianBoardLayer = 0;
	private int xDimension;
	private int yDimension;
	private int buttonImageXSize;
	private int buttonImageYSize;
	private int buttonSizeX;
	private int buttonSizeY;
	private int buttonBoundX;
	private int buttonBoundY;

	private JLayeredPane boardPane = new JLayeredPane();

	public ArrayList<Shape> shapes = new ArrayList<Shape>();
	public ArrayList<Avatar> avatars = new ArrayList<Avatar>();

	/**
	 * Constructor Creates the JFrame and adds components to the JFrame
	 * @param abc: the action listener needed for the JButtons
	 */
	public BoardGUI(ActionListener abc){
		super("Monopoly Game");
		super.setFocusable(true);
		super.requestFocusInWindow();
		setImages();
		super.setExtendedState(JFrame.MAXIMIZED_BOTH);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameBoard.setLayout(new BoxLayout(gameBoard, BoxLayout.Y_AXIS));
		gameBoard.setPreferredSize(new Dimension(xDimension, yDimension));
		gameBoard.add(boardPanel(abc));
		gameBoard.add(gameButtons(abc));
		container.setLayout(new BorderLayout());
		Instruction.setPreferredSize(new Dimension(500, 500));
		container.add(empty, BorderLayout.EAST);
		Instruction.setBackground(Color.lightGray);
		empty.setPreferredSize(new Dimension(500, 500));
		empty.setBackground(Color.lightGray);
		container.add(empty, BorderLayout.WEST);
		container.add(gameBoard, BorderLayout.CENTER);
		container.add(playerPanel(), BorderLayout.EAST);
		container.add(playerInstructionPanel (), BorderLayout.WEST);
		container.setBackground(Color.lightGray);
		super.getContentPane().add(container);
		super.setVisible(true);
	}

	/**
	 * Paints the players name on screen which property they bought
	 */

	public void paint(Graphics canvas) {
		super.paint(canvas);

		for (Shape c : shapes) {
			c.draw(canvas);
		}
		for (Avatar a : avatars){
			a.draw(canvas);
		}
	}

	/**
	 * Creates the JPanel for the buttons
	 * adds all buttons to the panel
	 * @param abc: the action listener needed for the JButtons
	 * @return buttonPanel: JPanel with all the buttons added to it
	 */
	public JPanel gameButtons(ActionListener abc) {
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(nextTurnButton(abc));
		buttonPanel.add(buyButton(abc));
		buttonPanel.add(passButton(abc));
		buttonPanel.add(exitButton(abc));
		buttonPanel.add(chanceButton(abc));
		buttonPanel.setBackground(Color.lightGray);
		return buttonPanel;
	}

	public JPanel playerInstructionPanel () {
		InstructionPanel.setLayout(new BoxLayout(InstructionPanel, BoxLayout.Y_AXIS));
		InstructionPanel.add(emptyLabel);
		InstructionPanel.add(rollButtonInstruction);
		InstructionPanel.add(rollButtonInstruction2);
		InstructionPanel.add(emptyLabel2);
		InstructionPanel.add(nextTurnButtonInstruction);
		InstructionPanel.add(nextTurnButtonInstruction2);
		InstructionPanel.add(nextTurnButtonInstruction3);
		InstructionPanel.add(emptyLabel3);
		InstructionPanel.add(endGameButtonInstruction);
		InstructionPanel.add(endGameButtonInstruction2);
		InstructionPanel.add(emptyLabel4);
		InstructionPanel.add(passButtonInstruction);
		InstructionPanel.add(passButtonInstruction2);
		InstructionPanel.add(emptyLabel5);
		InstructionPanel.add(buyButtonInstruction);
		InstructionPanel.add(buyButtonInstruction2);
		InstructionPanel.setPreferredSize(new Dimension(500, 500));
		InstructionPanel.setBackground(Color.lightGray);
		return InstructionPanel;
	}
	
	/**
	 * Creates the JPanel for the board image
	 * Sets the bounds for the image
	 * Adds the image to a JPanel
	 * @return board: the jpanel with the board image
	 */
	public JPanel boardPanel(ActionListener abc) {

		regularBoardImage.setBounds(0,0,xDimension,yDimension);
		canadianBoardImage.setBounds(0,0,xDimension,yDimension);
		boardPane.setPreferredSize(new Dimension(xDimension,yDimension));
		roll.setBounds(buttonBoundX, buttonBoundY, buttonImageXSize, buttonImageYSize);
		boardPane.add(rollButton(abc, false, 0), new Integer (3));
		board.add(boardPane);
		board.setPreferredSize(new Dimension (xDimension,yDimension));
		board.setBackground(Color.lightGray);
		return board;
	}

	/**
	 * Creates the panel for the players current turn information
	 * Adds JLabels as needed to display the necessary player attributes
	 * @return: the JPanel with all the player attributes as necessary
	 */
	public JPanel playerPanel () {
		informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
		informationPanel.add(playerNameLabel);
		informationPanel.add(propertyNameLabel);
		informationPanel.add(valueLabel);
		informationPanel.add(rentLabel);
		informationPanel.add(ownerLabel);
		informationPanel.add(moneyLabel);
		informationPanel.add(chanceLabel);
		informationPanel.setPreferredSize(new Dimension(500, 500));
		informationPanel.setBackground(Color.lightGray);
		return informationPanel;
	}

	/**
	 * Creates the JButtons made and adds ActionListener for each individual button
	 * @return: the Buy button
	 */
	public JButton buyButton(ActionListener abc) {
		buy.setActionCommand("BUY");
		buy.addActionListener(abc);
		buy.setAlignmentX(Component.CENTER_ALIGNMENT);
		buy.setBackground(Color.pink);
		buy.setPreferredSize(new Dimension (buttonSizeX, buttonSizeY));
		buy.setVisible(false);
		return buy;
	}

	/**
	 * Creates the JButtons made and adds ActionListener for each individual button
	 * @return: the Exit button
	 */
	public JButton exitButton(ActionListener abc) {
		exit.setActionCommand("EXIT");
		exit.addActionListener(abc);
		exit.setBackground(Color.red);
		exit.setPreferredSize(new Dimension (buttonSizeX, buttonSizeY));
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		return exit;
	}

	/**
	 * Creates the JButtons made and adds ActionListener for each individual button
	 * @return: the Roll button
	 */
	public JButton rollButton (ActionListener abc, boolean actionListener, int rolledValue) {
		roll.setActionCommand("ROLL DICE");
		if (actionListener == false) {
			roll.removeActionListener(abc);
		}

		else if (actionListener == true) {
			roll.addActionListener(abc);
		}
		setDiceLabel(rolledValue);
		roll.setAlignmentX(Component.CENTER_ALIGNMENT);
		roll.setPreferredSize(new Dimension (buttonImageXSize, buttonImageYSize));
		return roll;
	}

	/**
	 * Creates the JButtons made and adds ActionListener for each individual button
	 * @return: the Pass button
	 */
	public JButton passButton (ActionListener abc) {
		pass.setActionCommand("PASS");
		pass.addActionListener(abc);
		pass.setBackground(Color.yellow);
		pass.setAlignmentX(Component.CENTER_ALIGNMENT);
		pass.setPreferredSize(new Dimension (buttonSizeX, buttonSizeY));
		pass.setVisible(false);
		return pass;
	}

	/**
	 * Creates the JButtons made and adds ActionListener for each individual button
	 * @return: the Next Turn button
	 */
	public JButton nextTurnButton (ActionListener abc) {
		nextTurn.setActionCommand("NEXT TURN");
		nextTurn.addActionListener(abc);
		nextTurn.setBackground(Color.cyan);
		nextTurn.setAlignmentX(Component.CENTER_ALIGNMENT);
		nextTurn.setPreferredSize(new Dimension (buttonSizeX, buttonSizeY));
		return nextTurn;
	}

	/**
	 * Creates the JButtons made and adds ActionListener for each individual button
	 * @return: the community card button
	 */
	public JButton chanceButton(ActionListener abc) {
		communityCard.setActionCommand("Chance");
		communityCard.addActionListener(abc);
		communityCard.setIcon(chanceImage);
		communityCard.setAlignmentX(Component.CENTER_ALIGNMENT);
		communityCard.setPreferredSize(new Dimension (buttonImageXSize, buttonImageYSize));
		communityCard.setVisible(false);
		return communityCard;
	}

	/**
	 * Sets the visibility of the "Buy" button
	 * @param visible: a boolean for if to set the button to visible or not
	 */

	public void setBuyButtonVisibility(boolean visible) {
		buy.setVisible(visible);
	}

	/**
	 * Sets the visibility of the "Community" button
	 * @param visible: a boolean for if to set the button to visible or not
	 */
	public void setCommunityCardButtonVisibility(boolean visible) {
		communityCard.setVisible(visible);
	}
	/**
	 * Sets the visibility of the "Roll" button
	 * @param visible: a boolean for if to set the button to visible or not
	 */
	public void setRollButtonVisibility(boolean visible) {
		roll.setVisible(visible);
	}

	/**
	 * Sets the visibility of the "Pass" button
	 * @param visible: a boolean for if to set the button to visible or not
	 */
	public void setPassButtonVisibility (boolean visible) {
		pass.setVisible(visible);
	}

	/**
	 * Sets the visibility of the "Next turn" button
	 * @param visible: a boolean for if to set the button to visible or not
	 */
	public void setNextButtonVisibility (boolean visible) {
		nextTurn.setVisible(visible);
	}

	/**
	 * Sets the JLabel to display the current property name the player is on
	 * @param newPropertyName: the name of the property the player is on
	 */

	public void setPropertyNameLabel(String newPropertyName) {
		propertyNameLabel.setText("Property name: " + newPropertyName);
		propertyNameLabel.setFont(new Font("Serif", Font.BOLD, 17));
	}

	/**
	 * Set the JLabel for the price to buy the property
	 * @param newPropertyValue: the price of the property the player is currently on
	 */
	public void setValueLabel(int newPropertyValue) {
		valueLabel.setText("Property value: " + newPropertyValue);
		valueLabel.setFont(new Font("Serif", Font.BOLD, 17));
	}

	/**
	 * Sets the JLabel to display the current rent for the property
	 * @param propertyRent: the rent for the property
	 */
	public void setRentLabel(int propertyRent) {
		rentLabel.setText("Rent: " + propertyRent);
		rentLabel.setFont(new Font("Serif", Font.BOLD, 17));
	}

	/**
	 * Sets the JLabel to which player owns the property
	 * @param proppertyOwner: name of the player who owns the property
	 */
	public void setOwnerLabel(String proppertyOwner) {
		ownerLabel.setText("Property owner : " + proppertyOwner);
		ownerLabel.setFont(new Font("Serif", Font.BOLD, 17));
	}

	/**
	 * Sets the JLabel to the current player playing's name
	 * @param playerName: name of player playing currently
	 */
	public void setPlayerNameLabel(String playerName) {
		playerNameLabel.setText("Player: " + playerName);
		playerNameLabel.setFont(new Font("Serif", Font.BOLD, 17));
	}

	/**
	 * Sets the JLabel or the amount of money for each player
	 * @param playerMoney: Players current money count
	 */
	public void setMoneyLabel(int playerMoney) {
		moneyLabel.setText("Money: " + playerMoney);
		moneyLabel.setFont(new Font("Serif", Font.BOLD, 17));
	}

	/**
	 * Sets the JLabel or the amount of money for each player
	 * @param card: The random card chosen
	 */
	public void setChanceLabel(String card) {
		chanceLabel.setText("Community card: " + "\n" + card);
		chanceLabel.setFont(new Font("Serif", Font.BOLD, 17));
	}

	/**
	 * sets the chance label to blank
	 */

	public void setChanceLabel() {
		chanceLabel.setText(null);
	}


	/**
	 * 
	 * Do Conditional polymorphism here
	 * */
	/**
	 * Sets the JLabel to the value the player rolled
	 * @param rolledDice: the value the player rolled
	 */
	public void setDiceLabel (int rolledValue) {
		switch (rolledValue){
		case 0:	roll.setIcon(diceBlank);
		break;

		case 1: roll.setIcon(dice1);
		break;

		case 2: roll.setIcon(dice2);
		break;

		case 3: roll.setIcon(dice3);
		break;

		case 4: roll.setIcon(dice4);
		break;

		case 5: roll.setIcon(dice5);
		break;

		case 6: roll.setIcon(dice6);
		break;
		default:
			roll.setIcon(diceBlank);
			break;
		}
	}

	/**
	 * Selects the board image depending on user's screen size
	 * sets the size of the board and buttons.
	 */
	public void setImages() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();;
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getHeight();
		screenHeight -= screenHeight;
		if (screenHeight > 900) {
			buttonBoundX = 400;
			buttonBoundY = 200;
			buttonImageXSize= 100;
			buttonImageYSize = 100;
			xDimension = 900;
			yDimension = 900;
			buttonSizeX = 175;
			buttonSizeY = 100;
			settinglargeDice();
			canadianBoard = new ImageIcon ("src/Images/BoardImages/CanadianBoard.png");
			regularBoard =  new ImageIcon ("src/Images/BoardImages/RegularBoard.png");

		}
		else if (screenHeight < 900){
			buttonBoundX = 200;
			buttonBoundY = 150;
			buttonImageXSize = 50;
			buttonImageYSize = 50;
			xDimension = 500;
			yDimension = 500;
			buttonSizeX = 120;
			buttonSizeY = 60;
			settingSmallDice();
			canadianBoard = new ImageIcon ("src/Images/BoardImages/SmallerCanadianBoard.png");
			regularBoard =  new ImageIcon ("src/Images/BoardImages/SmallerReg.png");

		}
		canadianBoardImage = new JLabel(canadianBoard);
		regularBoardImage = new JLabel(regularBoard);

	}

	/**
	 * Set the board image to which ever the user chooses
	 */

	public void setBoardType(){
		if (isRegular == true){
			boardPane.add(regularBoardImage, new Integer(regularBoardLayer));
			regularBoardImage.setVisible(true);
		}

		else {
			boardPane.add(canadianBoardImage, new Integer(canadianBoardLayer));
			canadianBoardImage.setVisible(true);
		}
	}

	/**
	 * Sets the dice image to the smaller dice images
	 * if user screen is less then 900 pixels tall
	 */
	public void settingSmallDice() {
		setDiceBlank("src/Images/DiceImages/SmallerDie/SmallerBlankDie.png");
		setDice1("src/Images/DiceImages/SmallerDie/SmallerDie1.png");
		setDice2("src/Images/DiceImages/SmallerDie/SmallerDie2.png");
		setDice3("src/Images/DiceImages/SmallerDie/SmallerDie3.png");
		setDice4("src/Images/DiceImages/SmallerDie/SmallerDie4.png");
		setDice5("src/Images/DiceImages/SmallerDie/SmallerDie5.png");
		setDice6("src/Images/DiceImages/SmallerDie/SmallerDie6.png");
		setChanceImage("src/Images/SmallerChance.png");
	}

	/**
	 * Sets the dice image to the larger dice images
	 * if user screen is taller then 900 pixels tall
	 */
	public void settinglargeDice() {

		setDiceBlank("src/Images/DiceImages/DiceBackground.png");
		setDice1("src/Images/DiceImages/Dice1.png");
		setDice2("src/Images/DiceImages/Dice2.png");
		setDice3("src/Images/DiceImages/Dice3.png");
		setDice4("src/Images/DiceImages/Dice4.png");
		setDice5("src/Images/DiceImages/Dice5.png");
		setDice6("src/Images/DiceImages/Dice6.png");
		setChanceImage("src/Images/Chance.png");

	}

	/**
	 * 
	 * @param isRegular: the boolean for if the user chooses the regular board
	 */
	public void setRegular(boolean isRegular) {
		this.isRegular = isRegular;
	}
	/**
	 * 
	 * @param regularBoardLayer: sets the regular board layer in the JLayered pane
	 */
	public void setRegularBoardLayer(int regularBoardLayer) {
		this.regularBoardLayer = regularBoardLayer;
	}

	/**
	 * 
	 * @param picture: sets the dice image to blank
	 */
	public void setDiceBlank(String picture) {
		this.diceBlank = new ImageIcon(picture);
	}

	/**
	 * 
	 * @param picture: sets the dice image to the one sided die image
	 */
	public void setDice1(String picture) {
		this.dice1 = new ImageIcon(picture);
	}

	/**
	 * 
	 * @param picture: sets the dice image to the two sided die image
	 */
	public void setDice2(String picture) {
		this.dice2 = new ImageIcon(picture);
	}

	/**
	 * 
	 * @param picture: sets the dice image to the three sided die image
	 */
	public void setDice3(String picture) {
		this.dice3 = new ImageIcon(picture);
	}

	/**
	 * 
	 * @param picture: sets the dice image to the four sided die image
	 */
	public void setDice4(String picture) {
		this.dice4 = new ImageIcon(picture);
	}

	/**
	 * 
	 * @param picture: sets the dice image to the five  sided die image
	 */
	public void setDice5(String picture) {
		this.dice5 = new ImageIcon(picture);
	}

	/**
	 * 
	 * @param picture: sets the dice image to the six sided die image
	 */
	public void setDice6(String picture) {
		this.dice6 = new ImageIcon(picture);
	}

	/**
	 * 
	 * @param picture: community card image to set
	 */
	public void setChanceImage(String picture) {
		this.chanceImage = new ImageIcon(picture);
	}



	/**
	 * Checks if the roll button has been pressed
	 * @return buttonPressed: returns true if the roll button is pressed
	 */
	public boolean getRollButtonPress () {
		boolean buttonPressed;
		if (buy.getModel().isPressed()) {
			buttonPressed = true;
		}
		else {
			buttonPressed = false;
		}
		return buttonPressed;
	}

	/**
	 * 
	 * @return the one sided dice image
	 */
	public ImageIcon getDice1 () {
		return dice1;
	}

	/**
	 * 
	 * @return the two sided dice image
	 */
	public ImageIcon getDice2() {
		return dice2;
	}

	/**
	 * 
	 * @return the three sided dice image
	 */
	public ImageIcon getDice3() {
		return dice3;
	}

	/**
	 * 
	 * @return the four sided dice image
	 */
	public ImageIcon getDice4() {
		return dice4;
	}

	/**
	 * 
	 * @return the five sided dice image
	 */
	public ImageIcon getDice5() {
		return dice5;
	}

	/**
	 * 
	 * @return the six sided dice image
	 */
	public ImageIcon getDice6() {
		return dice6;
	}


	/**
	 * 
	 * @return the community card image
	 */
	public ImageIcon getChanceImage() {
		return chanceImage;
	}

	/**
	 * 
	 * @return the Canadian board image
	 */
	public ImageIcon getCanadianBoard() {
		return canadianBoard;
	}
	/**
	 * 
	 * @return regular board image
	 */
	public ImageIcon getRegularBoard() {
		return regularBoard;
	}

	/**
	 * Checks if the buy button has been pressed
	 * @return buttonPressed: returns true if the roll button is pressed
	 */
	public boolean 	buyButtonPressed() {
		System.out.println("Pressed");
		return (buy.getModel().isPressed()) ? true : false;
	}
}

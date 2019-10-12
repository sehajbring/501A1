package Logic;
import java.util.ArrayList;
import java.util.Random;

import gui.Avatar;
import gui.BoardGUI;
import gui.NameFrameGUI;
import gui.Point;
import gui.PointPosition;

import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import java.util.concurrent.ThreadLocalRandom;
/**
 * The main class
 * Holds all instances of player
 * Passes nessacary parameters to classes as needed
 * @authors Sehajveer Bring
 *
 */
public class ProjectMain implements ActionListener{
	private int iterate = 0;
	private Board game = new Board();
	private ArrayList <Player> playerList = new ArrayList<Player>();	
	private Random dice = new Random();
	private BoardGUI gui = new BoardGUI(this);
	private int valueRolled = 0;
	private Player person;
	private NameFrameGUI plName= new NameFrameGUI(this);
	private int screenX = 0;
	private int screenY = 0;
	
	/**
	 * startGame is called when the game begins. It invokes the setter methods that set the names of the players.
	 */
	public void startGame (){	
		game.newGameBoard();
	}

    /**
	 *@param: x is a value set to know whose turn is currently displayed on the boardGUI 
	 *person is of type player and sets the player information to display on the boardGUI
	*/

	public void nextPlayer (int x) {
		person = playerList.get(x);
		guiDisplay(person);
		drawPerson();
	}

	/**
	 * gameFinished is called when the game is ended. It checks for the player who wins the game and displays a congratulationary message.
	 */
	public void gameFinished (){
		for (int people =0 ; people < playerList.size(); people++) {
			Player x = playerList.get(people);
			if (x.getMoney() > 10000) {
				System.out.println(x.getName() + "WINS!!!!!!");
				System.exit(0);
			}
		}
	}
    
    /**
	 * this method is the action event listener it also sets what buttons should be visible and should not be visible to reduce error of a player pressing a button twice
	 * when not needed.
	 * @param event: for when a JButton is activated
	*/

	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("NEXT")) {
			playerPanels();
		}
		if(event.getActionCommand().equals("REGULAR")){
			game.setProprtyName(game.getRegularPropertyNames());
			startGame();
			gui.setRegularBoardLayer(1);
			gui.setRegular(true);
			gui.setBoardType();
			initilizePlayers();
			if (playerList.isEmpty()== false) {
				plName.setVisible(false);
				gui.setVisible(true);
				drawAvatar();
			}
		}
		
		if(event.getActionCommand().equals("CANADIAN")){
			game.setProprtyName(game.getCanadianPropertyNames());
			startGame();
			gui.setRegularBoardLayer(1);
			gui.setRegular(false);
			gui.setBoardType();
			initilizePlayers();
			if (playerList.isEmpty()== false) {
				plName.setVisible(false);
				gui.setVisible(true);
				drawAvatar();
			}
		}
		
		if (event.getActionCommand().equals("NEXT TURN")) {
			gameFinished();
			nextTurnCommand();
		}
		if (event.getActionCommand().equals("BUY")){
			buyProperty();
			afterBuy();
			gui.setButtonVis(gui.getNextTurn(),true);
		}
		//when a player click or presses the roll button it iterates whose turn and calls the nextPlayer method  
		if (event.getActionCommand().equals("ROLL DICE")){
			rolledDie(person);
			drawAvatar();
			drawPerson();
			}
		
		if (event.getActionCommand().equals("EXIT")){
			System.exit(0);
		}
		if (event.getActionCommand().equals("PASS")){
			afterBuy();
			gui.setButtonVis(gui.getNextTurn(),true);
		}
		
		if (event.getActionCommand().equals("Chance")) {
			chanceCommand();
		}
		drawPerson();
	}

	/**
	 * rolledDie randomizes the value of the dice if a player chooses to roll.
	 * It also invokes the method move which moves the player according to the value displayed on the dice.
	 * @param: subject: Of type player. Passes on all the attributes from the player class.
	 */
	public void rolledDie (Player subject) {
		iterate++;
		this.valueRolled = dice.nextInt(6) +1;
		subject.move(valueRolled);
		guiDisplay(subject);
		gui.rollButton(this, false,valueRolled);
		System.out.println("Position: " + subject.getPosition());
		boolean buyAble = game.newTile(subject.getPosition(), subject);
		
		if (buyAble == true) {
			beforeBuy();

		}else if (buyAble == false) {
			boolean chance = game.communityCardCheck(subject.getPosition());
			if (chance == true) {
				gui.setButtonVis(gui.getCommunityCard(),true);
				gui.setButtonVis(gui.getNextTurn(),false);
				
				drawPerson();
			}
			else {
				afterBuy();
				gui.setButtonVis(gui.getNextTurn(),true);
			}
		}
		buyAble = false;
		guiDisplay(subject);
		if (iterate == plName.getNumOfPlayers()) {
			iterate = 0;
		}
		drawPerson();
		guiDisplay(subject);
	}
	
	/**
	 * Draws the name of the player in red text over properties they own.
	 * It also adjusts the coordinate values in the board instance according to the size of screen.
	 */
	public void drawPerson() {
		try {
			PointPosition aCircle = new PointPosition(game.getPoint(person.getPosition()), 50, game.getPlayer(person.getPosition()).getName());
			aCircle.setSize(10);
			gui.shapes.add(aCircle);
			game.setPoint(-screenX, -screenY);
			Rectangle r = gui.getBounds();
			screenX = (r.width-1200)/2;
			game.setPoint(screenX, screenY);
			gui.repaint();
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * Draws the name of the player in blue text over their current position.
	 * There is a random variation of 10 pixels to reduce overlapping avatars.
	 */
	public void drawAvatar(){
		try {
			gui.avatars.clear();
			for (Player p : playerList){
				int rn = ThreadLocalRandom.current().nextInt(-10, 10 + 1);
				Point rp = new Point(game.getPoint(p.getPosition()));
				rp.moveDown(rn);
				rp.moveRight(rn);
				Avatar anAvatar = new Avatar(rp, 50, p.getName());
				gui.avatars.add(anAvatar);
				gui.repaint();
			}
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * Is run when the NEXT TURN button is pressed.
	 * It iterates to the next player and refreshes the board. 

	 */
	public void nextTurnCommand() {
		gui.setChanceLabel();
		drawAvatar();
		if (iterate < plName.getNumOfPlayers()) {
			nextPlayer(iterate);
			gui.setButtonVis(gui.getNextTurn(),false);
			gui.rollButton(this,true, valueRolled);
			//rollDie = false;
		}
		drawPerson();
		gameFinished();
	}
	
	/**
	 * is run when the chance button is pressed.
	 * calls communityCard to randomly pick an event in Board.java.
	 */
	public void chanceCommand() {
		gui.setChanceLabel(game.communityCard(person));
		guiDisplay(person);
		drawPerson();
		afterBuy();
		gui.setButtonVis(gui.getNextTurn(),true);
		gui.setButtonVis(gui.getCommunityCard(),false);
		drawPerson();
	}
	
	/**
	 * Initializes the players according to the number of players indicated from NameFrameGUI class.
	 */
	public void initilizePlayers() {
		for (int x =0; x < plName.getNumOfPlayers(); x++) {
			String a = plName.textFeild.get(x).getText();
			playerList.add(new Player());
			Player b = playerList.get(x);
			b.setName(a);
		}
	}
	
	/**
	 *makes the buy/pass buttons invisible after use.
	 */
	public void afterBuy(){
		gui.setButtonVis(gui.getBuy(),false);
		gui.setButtonVis(gui.getPass(),false);
	}
	
	/**
	 * makes the buy/pass buttons visible for use.
	 */
	public void beforeBuy(){
		gui.setButtonVis(gui.getBuy(),true);
		gui.setButtonVis(gui.getPass(),true);
	}
	
	/**
	 * listener for the JComboBox in NameFrameGUI
	 * gets the number selected by the user from the name JFrame and sets the number of players
	 */
	public void playerPanels(){
		String x = (plName.numOfPlayer.getSelectedItem().toString());
		int y = Integer.parseInt(x);
		plName.setNumOfPlayers(y);
		plName.visibleTextFeilds();
		plName.setNumbersVisibilityPanel(true);
		plName.setNamesPanelVisibilty(true);
	}
	
    /**
	 * to reduce privacy leaks it checks which player buys a property and do the needed transaction to the player balance 
	*/
	public void buyProperty() {
		game.buy(person.getPosition(), person);
		int playerMoney = person.getMoney();
		gui.setMoneyLabel(playerMoney);
		drawPerson();
	}
	
	
    /**
	 * it displays the player information i.e the balance,current position, the amount rolled, the amount the value the property cost, the rent of that property
	 * @param: subject of type player is the name of the current player playing their turn.  
	*/
	public void guiDisplay (Player subject){
		gui.setPropertyNameLabel(game.getPropertyName(subject.getPosition()));
		gui.setValueLabel(game.getValue(subject.getPosition()));
		gui.setRentLabel(game.getRent(subject.getPosition()));
		gui.setMoneyLabel(subject.getMoney());
		gui.setPlayerNameLabel(subject.getName());
				
		if (game.getPlayer(subject.getPosition()) == null){
			gui.setOwnerLabel("None");
		}else{
			gui.setOwnerLabel(game.getPlayer(subject.getPosition()).getName());
			
		}
	}


	/**
	 * Main method that starts the game.
	 */
	public static void main(String[] args) {
		ProjectMain p1 = new ProjectMain();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				p1.plName.setVisible(true);
				p1.gui.setVisible(false);
				 }
		 	});
		//p1.startGame();
	}

}

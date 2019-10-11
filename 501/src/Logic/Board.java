package Logic;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import gui.Point;
/**
 * Holds all the values for the board
 * Sets the values for the rent and property value
 * sets the names of the property based on user selected board
 * @author Sehajveer Bring
 *
 */

public class Board{

	//Inialize game board list
	private Player [] gameBoard = new Player [28];
	private ArrayList <Point> coord = new ArrayList<Point> (Arrays.asList( new Point(820,520), new Point(760,520), new Point(700,520), new Point(640,520), new Point(570,520),
			new Point(510,520), new Point(450,520), new Point(390,520), new Point(390,450), new Point(390,390), new Point(390,330), new Point(390,270), new Point(390,200), 
			new Point(390,140), new Point(390,70), new Point(450,70), new Point(510,70), new Point(570,70), new Point(640,70), new Point(700,70), new Point(760,70), 
			new Point(830,70), new Point(830,130), new Point(830,190), new Point(830,250), new Point(830,320), new Point(830,380), new Point(830,440)));
	//instance varibales
	private ArrayList <String> propertyNames = new ArrayList <String> ();
	private ArrayList <Integer> rent = new ArrayList <Integer> ();
	private ArrayList <Integer> value = new ArrayList <Integer> ();
	
	private List <Integer> pPostion = new ArrayList<Integer>(Arrays.asList(0, 7, 14, 11, 3, 18, 21, 25));

	private Random newEvent = new Random();
	
	private String canadianPropertyNames = "src/TextFiles/CanadianNames.txt";
	private String regularPropertyNames = "src/TextFiles/RegularPropertyNames.txt";
	private String proprtyNameTextFile;
	private String rentTexTFile = "src/TextFiles/Rent.txt";
	private String propertyValueTextFile = "src/TextFiles/PropertyValues.txt";
	

	public Board() {}

	/**
	 * It creates the gameboard as an array list and sets every element in the list to null.
	 * @throws FileNotFoundException 
	 */ 
	public void newGameBoard() {
		for (int x = 0; x < gameBoard.length; x++){
			gameBoard[x] = null;
		}
		addPropertyNames(getProprtyNameTextFile());
		addMoneyValues(getPropertyValueTextFile());
		addMoneyValues(getRentTexTFile());
	}

	/**
	 * getRent gets the value of rent of the property landed on.
	 * @param: property: Takes in the current property that the player has landed on.
	 * @return: returns the value of the rent.
	 */
	public int getRent(int property){
		int rentValue = rent.get(property);
		return rentValue;
	}

	/**
	 * getPropertyName gets the name of the property that has been landed on
	 * @param: position: Takes in the current position the player is on on the board.
	 * @return: returns the name of the property.
	 */
	public String getPropertyName (int position){
		String name = propertyNames.get(position);
		return name;
	}

	/**
	 * getValue gets the value of the property that has been landed on.
	 * @param: position: Takes in the current position the player is on on the board.
	 * @return: returns the value of the property as amount.
	 */
	public int getValue (int position){
		int amount = value.get(position);
		return amount;
	}

	/**
	 * Checks if a player owns a tile
	 * @param position: the current position of the player currently playing
	 * @return: returns the contents of the element of the array of type Player
	 */
	public Player getPlayer (int position){
		return gameBoard[position];
	}


	public Point getPoint(int position){
		Point p = coord.get(position);
		return p;
	}


	/**
	 * @return the canadianPropertyNames is the text file path on the drive
	 */
	public String getCanadianPropertyNames() {
		return canadianPropertyNames;
	}

	/**
	 * @return the regularPropertyNames is the text file path on the drive
	 */
	public String getRegularPropertyNames() {
		return regularPropertyNames;
	}

	/**
	 * @return the proprtyName text file is the text file that the user chooses to play
	 */
	public String getProprtyNameTextFile() {
		return proprtyNameTextFile;
	}

	/**
	 * @return the rentTexTFile is the text file path on the drive
	 */
	public String getRentTexTFile() {
		return rentTexTFile;
	}


	/**
	 * @return the propertyValueTextFile
	 */
	public String getPropertyValueTextFile() {
		return propertyValueTextFile;
	}
	
	/**
	 * 
	 * @return 
	 */
	public List<Integer> getpPostion() {
		return pPostion;
	}

	public void setPoint(int aX, int aY){
		for (int x = 0; x < coord.size(); x++){
			coord.get(x).moveRight(aX);
			coord.get(x).moveDown(aY);
		}
	}
	
	public List<Integer> returnIntSubList(List<Integer> list, int begining, int ending) {
		return list.subList(begining, ending);
	}
	
	
	/**
	 * @param proprtyName the proprtyName to set
	 */
	public void setProprtyName(String proprtyName) {
		this.proprtyNameTextFile = proprtyName;
	}

	/**
	 * newTile checks the property that has been landed on and shows if the property is eligible to be bought or not.
	 * It also gives players an option to buy and lets them know when a property is already owned by another player.
	 * @param: position : Takes in the current position of the player on the board
	 * @param: subject: Of type player. Passes on all the attributes from the player class. 
	 */ 
	public boolean newTile(int position, Player subject){ 
		boolean buyAble;
		
		if (getpPostion().contains(position)) {
			if((returnIntSubList(getpPostion(), 4, getpPostion().size()).contains(position))) {
				speicalTile(position, subject);	
			}
			buyAble = false;
		}
		
		else if (gameBoard[position] == null){
			buyAble = true;
		}
		else{
			subject.takeMoney(rent.get(position));
			gameBoard[position].addMoney(rent.get(position));
			buyAble = false;
		}
		
		return buyAble;
	}

	
	
	public boolean communityCardCheck(int position) {
		return (position == 11 || position == 25) ? true:false;
	}


	/**
	 * specialTile checks if a player lands on free parking and adds the value of rent ($100) to the players account.
	 * It also checks if the player landed on any of INCOME TAX, JAIL or LUXURY TAX and deducts their respective rent value.
	 * @param: position : Takes in the current position of the player on the board
	 * @param: subject: Of type player. Passes on all the attributes from the player class.
	 */ 
	public void speicalTile(int position, Player subject){
		if (position == 14){
			subject.addMoney(rent.get(position));
		}
		else{
			subject.takeMoney(rent.get(position));
		}
	}

	/**
	 * Gives the player a choice to either buy a property or forego it when they land on it.
	 * @param: position : Takes in the current position of the player on the board
	 * @param: subject: Of type player. Passes on all the attributes from the player class.
	 */	
	public void buy(int position, Player subject){
		gameBoard[position] = subject;
		subject.takeMoney(value.get(position));

	}

	/**
	 * Generates a random number and based on that number, a random event occurs
	 * @param subject is the current player
	 * @return the randomly generadted event
	 */

	public String communityCard(Player subject){
		int x = newEvent.nextInt(5) +1;
		String card = null;
		if(x== 1){
			card = "3 tiles backward";
			subject.move(-3);
			
		}

		else if(x == 2){
			card = "Go to jail";
			subject.setPosition(21);
			
		}

		else if (x ==3){
			card = "2 tile forward";
			subject.move(2);
			
		}

		else if (x == 4){
			card = "Move to GO";
			subject.setPosition(0);
			
		}

		else{
			card = "Bank gave you 200";
			subject.addMoney(200);
			
		}
		return card;
	}

	/**
	 * Reads the property name text files and sets the array list
	 * Reads the file for whichever board the user chooses
	 * @param the property name text file for which board the player chooses 
	 */
	public void addPropertyNames (String propertyNamesTextFile) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(propertyNamesTextFile));
			String line = reader.readLine();
			while (line != null) {
				if (line != null) {
					propertyNames.add(line);
					line = reader.readLine();
				}
			}
			reader.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();

		}
	}

	/**
	 * Reads the property value file and the rent file
	 * adds the values to the ArrayList above
	 * @param textFile
	 */
	public void addMoneyValues (String textFile) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(textFile));
			String line = reader.readLine();
			if (textFile.equals(rentTexTFile)) {
				line = reader.readLine();
				while (line != null) {
					if (line != null) {
						rent.add(Integer.parseInt(line));
						line = reader.readLine();
					}
				}
			}
			else if (textFile.equals(propertyValueTextFile)) {
				line = reader.readLine();
				while (line != null) {
					if (line != null) {
						value.add(Integer.parseInt(line));
						line = reader.readLine();
					}
				}
			}
			reader.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();

		}
	}

}

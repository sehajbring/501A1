package gui;
import java.awt.Graphics;

/**
 * Class defines a shape centered aroun a point, with getters and seters for the size and center point.
 * Constructors: a point, size of shape
 * @author Sehjaveer Bring
 */

public abstract class Shape {
	private Point topLeft = new Point(0,0);
	private int size = 50;

	public Shape(Point topLeft, int size){
		this.topLeft = topLeft;
		this.size = size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize(){
		return size;
	}

	public void setTopLeft (Point topLeft){
		this.topLeft = topLeft;
	}

	public Point getTopLeft (){
		return topLeft;
	}
	/**
	 * Methods to move the center point around
	 */
	public void moveDown(int amount) {
		topLeft.moveDown(amount);
	}

	public void moveUp(int amount) {
		topLeft.moveUp(amount);
	}

	public void moveLeft(int amount) {
		topLeft.moveLeft(amount);
	}

	public void moveRight(int amount) {
		topLeft.moveRight(amount);
	}
	/**
	 * Placeholder for the method to draw shape.
	 */
	abstract void draw(Graphics g);
}

package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/**
 * Class that draws name of player in red.
 * Constructors: a point, size of drawing, string that it draws.
 * @author Sehjaveer Bring
 */
public class PointPosition extends Shape {

	private String print;

	public PointPosition(Point topLeft, int size, String print){
		super(topLeft, size);
		this.print = print;
	}

	public void draw(Graphics g){
		//Dimension d = 50;
		int fontSize = 20;

		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

		g.setColor(Color.red);

		g.drawString(print, getTopLeft().getXCoord(), getTopLeft().getYCoord());

	}
}

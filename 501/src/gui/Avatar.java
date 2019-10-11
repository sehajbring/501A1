package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/**
 * Class that draws name of player in blue.
 * Constructors: a point, size of drawing, string that it draws.
 * @author Sehjaveer Bring
 */
public class Avatar extends Shape {

	private String print;

	public Avatar(Point topLeft, int size, String print){
		super(topLeft, size);
		this.print = print;
	}

	public void draw(Graphics g){
		int fontSize = 25;

		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

		g.setColor(Color.blue);

		g.drawString(print, getTopLeft().getXCoord(), getTopLeft().getYCoord());

	}
}

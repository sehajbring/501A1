package Logic;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gui.BoardGUI;

class TestCases {

	@Test
	void testbuyButtonPressed() {
		BoardGUI bg = new BoardGUI(null);
		assertEquals("Should not be pressed", false, bg.buyButtonPressed());
	}
	
	@Test
	void testNewTile() {
		Player p1 = new Player();
		p1.setName("p1");
		Player p2 = new Player();
		p2.setName("p2");
		Board b = new Board();
		assertEquals("Player 1 should not be able to buy", false,b.newTile(p1.getPosition(), p1));
		assertEquals("Player 2 should not be able to buy", false,b.newTile(p2.getPosition(), p2));
		
		p1.setPosition(2);
		assertEquals("Player 1 should be able to buy", true,b.newTile(p1.getPosition(), p1));
		
		p2.setPosition(11);
		assertEquals("Player 2 should not be able to buy", false,b.newTile(p2.getPosition(), p2));
		
		p1.setPosition(8);
		assertEquals("Player 1 should be able to buy", true,b.newTile(p1.getPosition(), p1));
		
		p2.setPosition(24);
		assertEquals("Player 2 should not be able to buy", true,b.newTile(p2.getPosition(), p2));
	}

}

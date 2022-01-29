package mainApp;

import java.awt.Color;

public class Player extends AnimateObject{

	private Color playerColor = Color.GREEN;
	
	public Player(int x, int y) {
		super(x, y);
		this.setColor(playerColor);
	}

}

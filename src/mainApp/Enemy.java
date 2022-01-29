package mainApp;

import java.awt.Color;

public class Enemy extends AnimateObject{

	private Color enemyColor = Color.cyan;
	
	public Enemy(int x, int y) {
		super(x, y);
		this.setColor(enemyColor);
	}

}

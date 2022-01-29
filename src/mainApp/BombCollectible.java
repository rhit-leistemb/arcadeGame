package mainApp;

import java.awt.Color;

public class BombCollectible extends GameObject{
	
	private Color bombColor = Color.RED;

	public BombCollectible(int x, int y) {
		super(x, y);
		this.setColor(bombColor);
	}

}

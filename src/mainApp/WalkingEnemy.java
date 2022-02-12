package mainApp;

import java.awt.Color;

public class WalkingEnemy extends Enemy{
	
	private final Color walkingEnemyColor = Color.magenta;

	public WalkingEnemy(int x, int y) {
		super(x, y);
		this.setColor(walkingEnemyColor);
	}
	
	public void moveRight() {
		if(this.getRSideHit() == false) {
			this.setX(this.getX() + 2);
		}
	}
	
	public void moveLeft() {
		if(this.getLSideHit() == false) {
			this.setX(this.getX() - 2);
		}
	}

}

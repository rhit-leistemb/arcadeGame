package mainApp;

import java.awt.Color;

public class WalkingEnemy extends Enemy{
	
	private final Color walkingEnemyColor = Color.magenta;
	private int speed = 2;
	
	public WalkingEnemy(int x, int y) {
		super(x, y);
		this.setColor(walkingEnemyColor);
	}
	
	public void moveRight() {
		if(this.getRSideHit() == false) {
			this.setX(this.getX() + speed);
		}
	}
	
	public void moveLeft() {
		if(this.getLSideHit() == false) {
			this.setX(this.getX() - speed);
		}
	}

}

package mainApp;

import java.awt.Color;

public class WalkingEnemy extends Enemy{
	
	private final Color walkingEnemyColor = Color.magenta;
	
	private boolean goLeft = false;
	private boolean goRight = false;

	public WalkingEnemy(int x, int y) {
		super(x, y);
		this.setColor(walkingEnemyColor);
	}
	
	public void moveRight() {
		if(this.goRight == true && this.getRSideHit() == false) {
			this.setX(this.getX() + 2);
		}
	}
	
	public void moveLeft() {
		if(this.goLeft == true && this.getLSideHit() == false) {
			this.setX(this.getX() - 2);
		}
	}

	public void goLeft(boolean condition) {
		this.goLeft = condition;
	}
	public void goRight(boolean condition) {
		this.goRight = condition;
	}

}

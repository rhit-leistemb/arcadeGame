package mainApp;

import java.awt.Color;

public class WalkingEnemy extends Enemy{
	
	private final Color walkingEnemyColor = Color.magenta;

	public WalkingEnemy(int x, int y) {
		super(x, y);
		this.setColor(walkingEnemyColor);
	}
	
	public void move(){
		this.setX(this.getX()+2);
	}
	
	public void moveRight() {
		this.setX(this.getX()+2);
	}
	
	public void moveLeft() {
		this.setX(this.getX()-2);
	}

}

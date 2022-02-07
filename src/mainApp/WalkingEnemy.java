package mainApp;

import java.awt.Color;

public class WalkingEnemy extends Enemy{
	
	private final Color walkingEnemyColor = Color.magenta;

	public WalkingEnemy(int x, int y) {
		super(x, y);
		this.setColor(walkingEnemyColor);
	}
	
	public void walk() {
		this.setX(this.getX()+20);
		try {
			this.wait(20);
		} catch (InterruptedException e) {
			System.out.println("WalkingEnemy wait does not work");
		}
		this.setX(this.getX()-20);
	}

}

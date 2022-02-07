package mainApp;

import java.awt.Color;

public class FlyingEnemy extends Enemy{
	
	private final Color flyingEnemyColor = Color.blue;

	public FlyingEnemy(int x, int y) {
		super(x, y);
		this.setColor(flyingEnemyColor);
	}
	
	public void fly() {
		this.setY(this.getY()-50);
		try {
			this.wait(20);
		} catch (InterruptedException e) {
			System.out.println("FlyingEnemy wait does not work");
		}
		this.setY(this.getY()+50);
	}

}

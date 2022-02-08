package mainApp;

import java.awt.Color;
import java.util.Random;

public class FlyingEnemy extends Enemy{
	
	private final Color flyingEnemyColor = Color.blue;

	public FlyingEnemy(int x, int y) {
		super(x, y);
		this.setColor(flyingEnemyColor);
	}
	
	public void move() {
		Random random = new Random();
		int rand = random.nextInt(4);
		if(rand == 0) {
			this.setY(this.getY()-4);
		}else if(rand == 1) {
			this.setY(this.getY()+4);
		}else if(rand == 2) {
			this.setX(this.getX()-4);
		}else if(rand == 3) {
			this.setX(this.getX()+4);
		}
	}
	
	
	public void gravity() {
		return;
	}
}

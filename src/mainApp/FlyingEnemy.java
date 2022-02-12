package mainApp;

import java.awt.Color;
import java.util.Random;

public class FlyingEnemy extends Enemy{
	
	private final Color flyingEnemyColor = Color.blue;

	public FlyingEnemy(int x, int y) {
		super(x, y);
		this.setColor(flyingEnemyColor);
	}
	
	public void flyUp() {
		if(this.getTopHit() == false) {
			this.setY(this.getY()-2);
		}
	}
	public void flyDown() {
		if(this.getButtHit() == false) {
			this.setY(this.getY()+2);
		}
	}
	
	public void moveRight() {
		if(this.getRSideHit() == false) {
			this.setX(this.getX()+2);
		}
	}
	
	public void moveLeft() {
		if(this.getLSideHit() == false) {
			this.setX(this.getX()-2);
		}
	}
	
	public void gravity() {
		return;
	}
}

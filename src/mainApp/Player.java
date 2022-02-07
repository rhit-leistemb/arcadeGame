package mainApp;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player extends AnimateObject {

	private Color playerColor = Color.GREEN;
	private int speed = 3;
	
	private boolean goUp = false;
	private boolean goDown = false;
	private boolean goLeft = false;
	private boolean goRight = false;
	
	public Player(int x, int y) {
		super(x, y);
		this.setColor(playerColor);
	}

	

	public void move() {
		//this one works
		if(this.goUp == true) {
			this.setY(this.getY() - speed);
			isMoving = true;
		}
		if (this.goDown == true) {
			this.setY(this.getY() + speed);
			isMoving = true;
		}
		if(this.goLeft == true) {
			this.setX(this.getX() - speed);
			//isMoving = true;
		}
		if(this.goRight == true) {
			this.setX(this.getX() + speed);
			//isMoving = true;
		}
	}
	
	public void goUp(boolean condition) {
		this.goUp = condition;
	}
	public void goDown(boolean condition) {
		this.goDown = condition;
	}
	public void goLeft(boolean condition) {
		this.goLeft = condition;
	}
	public void goRight(boolean condition) {
		this.goRight = condition;
	}
	


}



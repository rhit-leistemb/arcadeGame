package mainApp;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player extends AnimateObject {

	private Color playerColor = Color.GREEN;
	private int speed = 3;
	private boolean upStop = true;
	private boolean downStop = true;
	private boolean leftStop = true;
	private boolean rightStop = true;
	
	public Player(int x, int y) {
		super(x, y);
		this.setColor(playerColor);
	}

	public void move1() {
		//this one works
		if(this.upStop == false) {
			System.out.println("Move up");
			this.setY(this.getY() - speed);
			isMoving = true;
		}
		if (this.downStop == false) {
			System.out.println("Move down");
			this.setY(this.getY() + speed);
			isMoving = true;
		}
		if(this.leftStop == false) {
			System.out.println("Move left");
			this.setX(this.getX() - speed);
			//isMoving = true;
		}
		System.out.println("This is rightstop: " + this.rightStop);
		if(this.rightStop == false) {
			System.out.println("Move right");
			this.setX(this.getX() + speed);
			//isMoving = true;
		}
	}
	
	public void setUpStop(boolean condition) {
		this.upStop = condition;
	}
	public void setDownStop(boolean condition) {
		this.downStop = condition;
	}
	public void setLeftStop(boolean condition) {
		this.leftStop = condition;
	}
	public void setRightStop(boolean condition) {
		this.rightStop = condition;
	}
	


}



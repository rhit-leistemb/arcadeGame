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

	//allows the player to control movement
	public void move2() {
//		int code = press.getKeyCode();
//		if(code == KeyEvent.VK_UP) {
//			this.setY(this.getY() - speed);	
//		}
//		if (code == KeyEvent.VK_DOWN) {
//			this.setY(this.getY() + speed);
//		}
//		if(code == KeyEvent.VK_LEFT) {
//			this.setX(this.getX() - speed);
//		}
//		if(code == KeyEvent.VK_RIGHT ) {
//			this.setX(this.getX() + speed);
	}	

	public void move1() {
//		int code = press.getKeyCode();
//		if(code == KeyEvent.VK_UP) {
//			this.setY(this.getY() - speed);	
//		}
//		if (code == KeyEvent.VK_DOWN) {
//			this.setY(this.getY() + speed);
//		}
//		if(code == KeyEvent.VK_LEFT) {
//			this.setX(this.getX() - speed);
//		}
//		if(code == KeyEvent.VK_RIGHT ) {
//			this.setX(this.getX() + speed);
		
		if(this.upStop == false) {
			this.setY(this.getY() - speed);	
		}
		if (this.downStop == false) {
			this.setY(this.getY() + speed);
		}
		if(this.leftStop == false) {
			this.setX(this.getX() - speed);
		}
		if(this.rightStop == false) {
			this.setX(this.getX() + speed);
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



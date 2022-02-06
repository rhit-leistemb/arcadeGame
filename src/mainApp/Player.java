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
//	public void move2(KeyEvent press, boolean up, boolean right, boolean left) {
//		double angleHZ = 0;
//		double angleV = 1;
//		int code = press.getKeyCode();
//		
//		if(up) {
//			if (right) {
//				angleV = Math.sin(  45 + (Math.PI/2)  );
//				angleHZ = -1 * Math.cos(  45 + (Math.PI/2)  );
//			}
//			if (left) {
//				angleV = Math.sin(  45 + (Math.PI/2)  );
//				angleHZ = Math.cos(  45 + (Math.PI/2)  );
//			}
//			this.setY(this.getY() - (int)(speed * angleV));	
//			this.setX(this.getX() + (int)(speed * angleHZ));
//		}
//		if (code == KeyEvent.VK_DOWN) {
//			this.setY(this.getY() + speed);
//		}
//		if(code == KeyEvent.VK_LEFT) {
//			this.setX(this.getX() - speed);
//		}
//		if(code == KeyEvent.VK_RIGHT ) {
//			this.setX(this.getX() + speed);
//		}
//	}	

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
//		}
		
		//this one works
		if(this.upStop == false) {
			System.out.println("Move up");
			this.setY(this.getY() - speed);
		}
		if (this.downStop == false) {
			System.out.println("Move down");
			this.setY(this.getY() + speed);
		}
		if(this.leftStop == false) {
			System.out.println("Move left");
			this.setX(this.getX() - speed);
		}
		if(this.rightStop == false) {
			System.out.println("Move right");
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



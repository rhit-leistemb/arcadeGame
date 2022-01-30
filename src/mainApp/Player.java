package mainApp;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player extends AnimateObject {

	private Color playerColor = Color.GREEN;
	private int speed = 3;
	
	public Player(int x, int y) {
		super(x, y);
		this.setColor(playerColor);
	}

	public void move(KeyEvent press) {
		int code = press.getKeyCode();
		if(code == KeyEvent.VK_UP) {
			System.out.println("going upppp");
			this.setY(this.getY() - speed);	
		}
//		if (code == KeyEvent.VK_DOWN) {
//			this.setY(this.getY() + speed);
//		}
		if(code == KeyEvent.VK_LEFT) {
			this.setX(this.getX() - speed);
		}
		if(code == KeyEvent.VK_RIGHT ) {
			this.setX(this.getX() + speed);
		}	
	}
}



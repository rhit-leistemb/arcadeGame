package mainApp;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player extends AnimateObject {

	private Color playerColor = Color.GREEN;
	private int speed = 2;
	private int score = 0;
	
	public Player(int x, int y) {
		super(x, y);
		this.setColor(playerColor);
	}

	public void move() {
		if(this.isGoUp() == true && this.getTopHit() == false) {
			this.setY(this.getY() - speed);
			isMoving = true;
		} else if(this.isGoUp() == false) {
			isMoving = false;
		}
		if(this.isGoLeft() == true && this.getLSideHit() == false) {
			this.setX(this.getX() - speed);
		}
		if(this.isGoRight() == true && this.getRSideHit() == false) {
			this.setX(this.getX() + speed);
		}
	}
	
//	public void updateVal(GameObject other) {
//		if(other.getClass().getSimpleName().equals("BombCollectible")) {
//			score++;
//		}
//	}

}



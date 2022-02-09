package mainApp;

public class AnimateObject extends GameObject{
	
	boolean isMoving = false;

	
	public AnimateObject(int x, int y) {
		super(x, y);
	}

	public void gravity() {
		if(isMoving == false && this.getButtHit() == false) {
			this.setY(this.getY()+ 2);
		}
	}
	
	public void updateHitLines() {
		buttLine.setLine(this.getX(), this.getY() + this.getHeight(), this.getX() + this.getWidth(), this.getY() + this.getHeight());
		topLine.setLine(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY());
		rightLine.setLine(this.getX() + this.getWidth(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight());
		leftLine.setLine(this.getX(), this.getY(), this.getX(), this.getY() + this.getHeight());
	}
	

	public void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public void move() {
		return;
	}
	
	public void moveRight() {
		return;
	}
	
	public void moveLeft() {
		return;
	}

}

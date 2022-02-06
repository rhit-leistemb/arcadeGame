package mainApp;

public class AnimateObject extends GameObject{
	
	boolean isMoving = false;

	public AnimateObject(int x, int y) {
		super(x, y);
	}

	public void gravity() {
		if(isMoving == false) {
			this.setY(this.getY()+3);
		}
	}
	
	public void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

}

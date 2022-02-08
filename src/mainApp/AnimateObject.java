package mainApp;

public class AnimateObject extends GameObject{
	
	boolean isMoving = false;
	boolean isColliding = false;
	
	public AnimateObject(int x, int y) {
		super(x, y);
	}

	public void gravity() {
		if(isMoving == false&&isColliding == false) {
			this.setY(this.getY()+3);
		}
	}
	
	public void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public void setIsColliding(boolean isColliding) {
		this.isColliding = isColliding;
		//System.out.println(isColliding);
	}

}

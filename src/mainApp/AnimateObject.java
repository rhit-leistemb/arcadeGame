package mainApp;

/**
 * Class: AnimateObject
 * @author Bhargav Nagalamadaka and Mathew Leister
 * <br>Purpose: Contains behavior and attributes specific to moving objects in the game
 * <br>Restrictions: Cannot be used for stationary objects
 * <br>For example: 
 * <pre>
 *    AnimateObject exAnim = new AnimateObject(5, 5);
 * </pre>
 */
public class AnimateObject extends GameObject{
	
	boolean isMoving = false;

	private boolean goUp = false;
	private boolean goLeft = false;
	private boolean goRight = false;
	private boolean goDown = false;
	private int playerX;
	private int playerY;
	
	public AnimateObject(int x, int y) {
		super(x, y);
	}

	public void gravity() {
		if(isMoving == false && this.getButtHit() == false) {
			this.setY(this.getY()+ 2);
		}
	}
	
	public void updateHitLines() {
		buttLine.setLine(this.getX(), this.getY() + this.getHeight(), this.getX() + this.getWidth(), this.getY() + this.getHeight() );
		topLine.setLine(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY());
		rightLine.setLine(this.getX() + this.getWidth() , this.getY() + 1, this.getX() + this.getWidth(), this.getY() + this.getHeight() - 1);
		leftLine.setLine(this.getX(), this.getY() + 1, this.getX(), this.getY() + this.getHeight() - 1);
	
		l2.setLine(this.getX() - 1 , this.getY() + this.getHeight() / 2, this.getX() + 4, this.getY() + this.getHeight() / 2);
		r2.setLine(this.getX() + this.getWidth() - 4, this.getY() + this.getHeight() / 2, this.getX() + this.getWidth() + 1, this.getY() + this.getHeight() / 2);
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
	
	public void flyUp() {
		return;
	}
	
	public void flyDown() {
		return;
	}
	
	public void goLeft(boolean condition) {
		this.setGoLeft(condition);
	}
	public void goRight(boolean condition) {
		this.setGoRight(condition);
	}

	public boolean isGoUp() {
		return goUp;
	}

	public void setGoUp(boolean goUp) {
		this.goUp = goUp;
	}

	public boolean isGoLeft() {
		return goLeft;
	}

	public void setGoLeft(boolean goLeft) {
		this.goLeft = goLeft;
	}

	public boolean isGoRight() {
		return goRight;
	}

	public void setGoRight(boolean goRight) {
		this.goRight = goRight;
	}

	public boolean isGoDown() {
		return goDown;
	}

	public void setGoDown(boolean goDown) {
		this.goDown = goDown;
	}
	
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

}

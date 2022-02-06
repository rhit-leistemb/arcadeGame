package mainApp;

public class AnimateObject extends GameObject{

	public AnimateObject(int x, int y) {
		super(x, y);
	}

	public void gravity() {
		this.setY(this.getY()+3);

	}

}

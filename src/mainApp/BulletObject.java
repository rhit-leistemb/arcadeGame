package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;

public class BulletObject extends AnimateObject{
	
	private final Color bulletColor = Color.red;
	private final int bulletWidth = 20;
	private final int bulletHeight = 10;

	public BulletObject(int x, int y) {
		super(x, y);
		this.setColor(bulletColor);
		this.setHeight(bulletHeight);
		this.setWidth(bulletWidth);
	}
	
	public void drawOn(Graphics2D g) {
		g = (Graphics2D)g.create();
		g.setColor(this.getColor());
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		if(this.isGoUp()) {
			g.rotate(Math.PI/2);
		}else if(this.isGoDown()) {
			g.rotate(Math.PI/2);
		}else if(this.isGoRight()) {
			g.rotate(0);
		}else if(this.isGoLeft()) {
			g.rotate(0);
		}
	}
	
	public void move() {
		if(this.isGoUp()) {
			this.setY(this.getY()-2);
		}else if(this.isGoDown()) {
			this.setY(this.getY()+2);
		}else if(this.isGoLeft()) {
			this.setX(this.getX() - 2);
		}else if(this.isGoRight()) {
			this.setX(this.getX() - 2);
		}
	}
	
	public void gravity() {
		return;
	}
}

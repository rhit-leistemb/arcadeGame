package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GameObject {
	
	public int x;
	public int y;
	public int width = 20;
	public int height = 20;
	public Color color = Color.black;
	
	Rectangle2D.Double hitbox = new Rectangle2D.Double(this.x, this.y, this.width, this.height);
	
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void drawOn(Graphics2D g) {
		g = (Graphics2D)g.create();
		g.setColor(this.color);
		g.fillRect(x, y, width, height);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}

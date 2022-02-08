package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class GameObject {
	
	public int x;
	public int y;
	public int width = 20;
	public int height = 20;
	public Color color = Color.black;
	
	Rectangle2D.Double hitbox;
	
	//
	Line2D.Double buttLine;
	Line2D.Double topLine;
	Line2D.Double leftLine;
	Line2D.Double rightLine;
	
	
	private boolean buttHit = false;
	private boolean rSideHit = false;
	private boolean lSideHit = false;
	private boolean topHit = false;
	//
	
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
		hitbox = new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		buttLine = new Line2D.Double(this.getX(), this.getY() + this.getHeight(), this.getX() + this.getWidth(), this.getY() + this.getHeight());
		topLine = new Line2D.Double(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY());
		rightLine = new Line2D.Double(this.getX() + this.getWidth(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight());
		leftLine = new Line2D.Double(this.getX() , this.getY(), this.getX(), this.getY() + this.getHeight());
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
	
	public Rectangle2D.Double getHitbox() {
		return hitbox;
	}
	
	public void updateHitLines() {
		buttLine.setLine(this.getX()-1, this.getY() + this.getHeight()+1, this.getX() + this.getWidth()+1, this.getY() + this.getHeight()+1);
		topLine.setLine(this.getX()-1, this.getY()-1, this.getX() + this.getWidth()+1, this.getY()+1);
		rightLine.setLine(this.getX() + this.getWidth()+1, this.getY()-1, this.getX() + this.getWidth()+1, this.getY() + this.getHeight()+1);
		leftLine.setLine(this.getX()-1 , this.getY()-1, this.getX()-1, this.getY() + this.getHeight()+1);
	}
	
	//
	public Line2D.Double getButtLine(){
		return this.buttLine;
	}
	
	public Line2D.Double getTopLine(){
		return this.topLine;
	}
	public Line2D.Double getRightLine(){
		return this.rightLine;
	}
	public Line2D.Double getLeftLine(){
		return this.leftLine;
	}
	
	
	public boolean getButtHit() {
		return this.buttHit;
	}
	public boolean getTopHit() {
		return this.topHit;
	}
	public boolean getRSideHit() {
		return this.rSideHit;
	}
	public boolean getLSideHit() {
		return this.lSideHit;
	}
	
	
	public void setButtHit(boolean condition) {
		this.buttHit = condition;
	}
	public void setTopHit(boolean condition) {
		this.topHit = condition;
	}
	public void setLSideHit(boolean condition) {
		this.lSideHit = condition;
	}
	public void setRSideHit(boolean condition) {
		this.rSideHit = condition;
	}
	//
}

package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * 
 */
public class GameComponent extends JComponent{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ArrayList<AnimateObject> animateObjects = new ArrayList<AnimateObject>();
	//
	private ArrayList<BoundaryObject> bounds = new ArrayList<BoundaryObject>();
	//
	private String fileName;
	private Player hero;
//	private boolean isColliding = false;
	
	public GameComponent(String fileName) {
		this.fileName = fileName;
		createGameObjectList();
	}
	
	public void createGameObjectList() {
		LevelReader reader = new LevelReader(fileName);
		reader.resetLevel();
		reader.readFile();
		objects = reader.getObjects();
		animateObjects = reader.getAnimateObjects();
		hero = reader.getHero();
		//
		bounds = reader.getBoundaries();
		//
	}

	public void checkCollision() {
		Line2D.Double butt1 = hero.getButtLine();
		//butt1.setLine(hero.getButtLine().getX1(), hero.getButtLine().getY1(), hero.getButtLine().getX2(), hero.getButtLine().getY2());
		Line2D.Double top1 = hero.getTopLine();
		Line2D.Double right1 = hero.getRightLine();
		Line2D.Double left1 = hero.getLeftLine();
		for (int j = 0; j < bounds.size(); j++) {
			Line2D.Double top2 = bounds.get(j).getTopLine();
			Line2D.Double butt2 = bounds.get(j).getButtLine();
			Line2D.Double right2 = bounds.get(j).getRightLine();
			Line2D.Double left2 = bounds.get(j).getLeftLine();
			
//			Rectangle2D.Double hitbox = bounds.get(j).getHitbox();
//			if (butt1.intersects(hitbox)) {
//				hero.setButtHit(true); 
//				System.out.println("Butt hit");
//			}
//			if (top1.intersects(hitbox)) {
//				hero.setTopHit(true); 
//			}
//			if (right1.intersects(hitbox)) {
//				hero.setRSideHit(true); 
//			}
//			if (left1.intersects(hitbox)) {
//				hero.setLSideHit(true); 
//			}
			if (butt1.intersectsLine(top2)) {
				hero.setButtHit(true); 
				//System.out.println("Butt hit");
			}
			if (top1.intersectsLine(butt2)) {
				hero.setTopHit(true); 
			}
			if (right1.intersectsLine(left2)) {
				hero.setRSideHit(true); 
			}
			if (left1.intersectsLine(right2)) {
				hero.setLSideHit(true); 
			}
		}
	}
	
	public void checkFreedom() {
		Line2D.Double butt1 = hero.getButtLine();
		Line2D.Double top1 = hero.getTopLine();
		Line2D.Double right1 = hero.getRightLine();
		Line2D.Double left1 = hero.getLeftLine();
		for (int j = 0; j < bounds.size(); j++) {
			Line2D.Double top2 = bounds.get(j).getTopLine();
			Line2D.Double butt2 = bounds.get(j).getButtLine();
			Line2D.Double right2 = bounds.get(j).getRightLine();
			Line2D.Double left2 = bounds.get(j).getLeftLine();
			
//			Rectangle2D.Double hitbox = bounds.get(j).getHitbox();
//			if (!butt1.intersects(hitbox)) {
//				hero.setButtHit(false); 
//				System.out.println("Butt free");
//			}
//			if (!top1.intersects(hitbox)) {
//				hero.setTopHit(false); 
//			}
//			if (!right1.intersects(hitbox)) {
//				hero.setRSideHit(false); 
//			}
//			if (!left1.intersects(hitbox)) {
//				hero.setLSideHit(false); 
//			}
//			
			if (!butt1.intersectsLine(top2)) {
				hero.setButtHit(false); 
				System.out.println("Butt free");
			}
			if (!top1.intersectsLine(butt2)) {
				hero.setTopHit(false); 
			}
			if (!right1.intersectsLine(left2)) {
				hero.setRSideHit(false); 
			}
			if (!left1.intersectsLine(right2)) {
				hero.setLSideHit(false); 
			}
		}
	}
		
	
		/*for (int i = 0; i < animateObjects.size(); i++) {
			Rectangle2D.Double hb1 = animateObjects.get(i).getHitbox();
			for (int j = 0; j < objects.size(); j++) {
				Rectangle2D.Double hb2 = objects.get(j).getHitbox();
				if (hb1.intersects(hb2)&& animateObjects.get(i).getClass().getSimpleName() != objects.get(j).getClass().getSimpleName()) {
					isColliding = true;
				}else {
					isColliding = false;
				}
				System.out.println(animateObjects.get(i).getClass().getSimpleName());
				System.out.println(objects.get(j).getClass().getSimpleName());
				System.out.println(isColliding);
			}
			if(isColliding) {
				animateObjects.get(i).setIsColliding(true);
			}else {
				animateObjects.get(i).setIsColliding(false);
			}
		}*/
		/*Rectangle2D.Double hb1 = hero.getHitbox();
		for (int j = 0; j < objects.size(); j++) {
			Rectangle2D.Double hb2 = objects.get(j).getHitbox();
			if (hb1.intersectsLine(hb2.getX(), hb2.getY(), hb2.getWidth(), hb2.getHeight())&& hero.getClass().getSimpleName() != objects.get(j).getClass().getSimpleName()) {
				isColliding = true;
				System.out.println(hero.getClass().getSimpleName());
				System.out.println(objects.get(j).getClass().getSimpleName());
			}else {
				isColliding = false;
			}
			//System.out.println(isColliding);
		}
		if(isColliding) {
			hero.setIsColliding(true);
		}else {
			hero.setIsColliding(false);
		}*/
//			int count = 1;
//			for(int j = 0; j< objects.size(); j++) {
//				if(objects.get(j).getClass().getSimpleName()!= "Player") {
//					//System.out.println(hero.getX()+hero.getWidth());
//					//System.out.println(objects.get(j).getX());
//					if(hero.getX()+hero.getWidth()>objects.get(j).getX()&&hero.getX()+hero.getWidth()<objects.get(j).getX()+objects.get(j).getWidth()&&hero.getY()+hero.getHeight()>objects.get(j).getY()&&hero.getY()+hero.getHeight()<objects.get(j).getY()+objects.get(j).getHeight()) {
//						count = 0;
//					}else {
//						count++;
//					}
//				}
//			}
//			if(count!=objects.size()-1) {
//				hero.setIsColliding(true);
//				count = 0;
//			}else {
//				count = 0;
//				hero.setIsColliding(false);
//			}
//	}
	

	public void traverse() {	
		hero.move();
	}
	
	public void update() {
		for(int i = 0; i< objects.size(); i++) {
			objects.get(i).updateHitLines();
		}
	}
	
	public void setDirection(int code) {
		if(code == KeyEvent.VK_UP) {
			hero.goUp(true);
		}
		if(code == KeyEvent.VK_LEFT) {
			hero.goLeft(true);
		}
		if(code == KeyEvent.VK_RIGHT ) {
			hero.goRight(true);
		}
		checkFreedom();
	}
	
	public void stopDirection(int code) {
		if(code == KeyEvent.VK_UP) {
			hero.goUp(false);
		}
		if(code == KeyEvent.VK_LEFT) {
			hero.goLeft(false);
		}
		if(code == KeyEvent.VK_RIGHT ) {
			hero.goRight(false);
		}
		checkFreedom();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		for (GameObject o: this.objects) {
			o.drawOn(g2);
		}		
	}	

	public void setFileName(String fileName) {
		this.fileName = fileName;
		recreate();
	}
	
	public void gravity() {
		for(int i = 0; i< animateObjects.size(); i++) {
			animateObjects.get(i).gravity();
		}
	}
	
	public void recreate() {
		objects.clear();
		animateObjects.clear();
		hero = null;
		createGameObjectList();	
	}
	
	public Player getHero() {
		return hero;
	}
	

}

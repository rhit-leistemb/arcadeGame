package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
	private String fileName;
	private Player hero;
	private boolean isColliding = false;
	
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
	}

	public void checkCollision() {
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
		Rectangle2D.Double hb1 = hero.getHitbox();
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
		}
	}
	

	public void traverse() {	
		hero.move();
	}
	
	public void update() {
		for(int i = 0; i< objects.size(); i++) {
			objects.get(i).updateHitbox();
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

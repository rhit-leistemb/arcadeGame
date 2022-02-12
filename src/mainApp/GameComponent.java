package mainApp;

import java.awt.Color;
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
	private String fileName;
	private Player hero;
	private int num = 0;
	
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
		for (int i = 0; i < animateObjects.size(); i++) {
			Line2D.Double butt1 = animateObjects.get(i).getButtLine();
			Line2D.Double top1 = animateObjects.get(i).getTopLine();
			Line2D.Double right1 = animateObjects.get(i).getRightLine();
			Line2D.Double left1 = animateObjects.get(i).getLeftLine();
			
			for (int j = 0; j < objects.size(); j++) {
				Line2D.Double top2 = objects.get(j).getTopLine();
				Line2D.Double butt2 = objects.get(j).getButtLine();
				Line2D.Double right2 = objects.get(j).getRightLine();
				Line2D.Double left2 = objects.get(j).getLeftLine();
						
				if (butt1.intersectsLine(top2)) {
					animateObjects.get(i).setButtHit(true);
				}
				if (top1.intersectsLine(butt2)) {
					animateObjects.get(i).setTopHit(true); 
				}
				if (right1.intersectsLine(left2)) {
					animateObjects.get(i).setRSideHit(true); 
				}
				if (left1.intersectsLine(right2)) {
					animateObjects.get(i).setLSideHit(true); 
				}
			}
		}
	}
	
	public void checkFreedom() { 
		for (int i = 0; i < animateObjects.size(); i++) {
			Line2D.Double butt1 = animateObjects.get(i).getButtLine();
			Line2D.Double top1 = animateObjects.get(i).getTopLine();
			Line2D.Double right1 = animateObjects.get(i).getRightLine();
			Line2D.Double left1 = animateObjects.get(i).getLeftLine();
		
			for (int j = 0; j < objects.size(); j++) {
				Line2D.Double top2 = objects.get(j).getTopLine();
				Line2D.Double butt2 = objects.get(j).getButtLine();
				Line2D.Double right2 = objects.get(j).getRightLine();
				Line2D.Double left2 = objects.get(j).getLeftLine();	
		
				if (!butt1.intersectsLine(top2)) {
					animateObjects.get(i).setButtHit(false); 
				}
				if (!top1.intersectsLine(butt2)) {
					animateObjects.get(i).setTopHit(false); 
				}
				if (!right1.intersectsLine(left2)) {
					animateObjects.get(i).setRSideHit(false); 
				}
				if (!left1.intersectsLine(right2)) {
					animateObjects.get(i).setLSideHit(false); 
				}
			}
		}
	}

	public void traverse() {	
		hero.move();
	}
	
	public void moveEnemyRight() {
		for(int i = 0; i< animateObjects.size(); i++) {
			if(animateObjects.get(i).getClass().getSimpleName().equals("WalkingEnemy")) {
				animateObjects.get(i).moveRight();
			}
		}
	}
	
	public void moveEnemyLeft() {
		for(int i = 0; i< animateObjects.size(); i++) {
			if(animateObjects.get(i).getClass().getSimpleName().equals("WalkingEnemy")) {
				animateObjects.get(i).moveLeft();
			}
		}
	}
	
	public void update() {
		for(int i = 0; i< animateObjects.size(); i++) {
			animateObjects.get(i).updateHitLines();
		}
		checkFreedom();
	}
	
	public void moveEnemy() {
		if(num < 50) {
			this.moveEnemyRight();
			num++;
		}else if(num < 100) {
			this.moveEnemyLeft();
			num++;
		}else {
			for(int i = 0; i< animateObjects.size(); i++) {
				if(animateObjects.get(i).getClass().getSimpleName().equals("FlyingEnemy")) {
					animateObjects.get(i).move();
				}
			}
			num = 0;
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
		//checkFreedom();
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
		//checkFreedom();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		for (GameObject o: this.objects) {
			o.drawOn(g2);
		}
			
	//draws hitboxes
		g2.setColor(Color.WHITE);
		for (int j = 0; j < objects.size(); j++) {
			Line2D.Double top2 = objects.get(j).getTopLine();
			Line2D.Double butt2 = objects.get(j).getButtLine();
			Line2D.Double right2 = objects.get(j).getRightLine();
			Line2D.Double left2 = objects.get(j).getLeftLine();
			
			g2.draw(top2);
			g2.draw(butt2);
			g2.draw(right2);
			g2.draw(left2);
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
}

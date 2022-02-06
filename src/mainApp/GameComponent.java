package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * 
 */
public class GameComponent extends JComponent{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private String fileName;
	private Player hero;
	
	public GameComponent(String fileName) {
		this.fileName = fileName;
		createGameObjectList();
	}
	
	public void createGameObjectList() {
		LevelReader reader = new LevelReader(fileName);
		reader.resetLevel();
		reader.readFile();
		objects = reader.getObjects();
		hero = reader.getHero();
	}
	
	
//	public void traverse(KeyEvent press, boolean up,boolean right, boolean left) {
//		hero.move2(press, up, right, left);
//	}
	
	public void traverse(KeyEvent press) {	
		int code = press.getKeyCode();
		if(code == KeyEvent.VK_UP) {
			hero.setUpStop(false);
			hero.move1();
		}
		if (code == KeyEvent.VK_DOWN) {
			hero.setDownStop(false);
			hero.move1();
		}
		if(code == KeyEvent.VK_LEFT) {
			hero.setLeftStop(false);
			hero.move1();
		}
		if(code == KeyEvent.VK_RIGHT ) {
			hero.setRightStop(false);
			hero.move1();
		}
	}
	
	public void hault(KeyEvent press) {
		int code = press.getKeyCode();
		if(code == KeyEvent.VK_UP) {
			hero.setUpStop(true);	
			//System.out.println("stopped moving");
		}
		if (code == KeyEvent.VK_DOWN) {
			hero.setDownStop(true);
		}
		if(code == KeyEvent.VK_LEFT) {
			hero.setLeftStop(true);
		}
		if(code == KeyEvent.VK_RIGHT ) {
			hero.setRightStop(true);
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
	
	public void recreate() {
		objects.clear();
		hero = null;
		createGameObjectList();	
	}
}

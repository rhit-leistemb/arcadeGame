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
	private ArrayList<String> lines = new ArrayList<String>();
	private String fileName;
	private int space = 20;
	private Player hero;
	
	public GameComponent(String fileName) {
		this.fileName = fileName;
		createGameObjectList();
	}
	
	public void createGameObjectList() {
		LevelReader reader = new LevelReader(fileName);
		reader.readFile();
		lines = reader.getLines();
		int x = 0;
		int y = 0;
		for(int i = 0; i< lines.size(); i++){
			String currentLine = lines.get(i);
			for(int j = 0; j< currentLine.length(); j++) {
				char currentChar = currentLine.charAt(j);
				if(currentChar == '|') {
					GameObject o = new GameObject(x, y);
					x+=space;
					objects.add(o);
				}else if(currentChar == '-') {
					GameObject o = new GameObject(x, y);
					x+=space;
					objects.add(o);
				}else if(currentChar == '.') {
					x+=space;
				}else if(currentChar == 'B') {
					BombCollectible o = new BombCollectible(x, y);
					x+=space;
					objects.add(o);
				}else if(currentChar == 'A') {
					Enemy o = new Enemy(x, y);
					x+=space;
					objects.add(o);
				}else if(currentChar == 'H') {
					hero = new Player(x, y);
					x+=space;
					objects.add(hero);
				}else {
					x+=space;
				}
			}
			y+=space;
			x= 0;
		}
	}
	
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
			System.out.println("stopped moving");
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
		lines.clear();	
		createGameObjectList();	
	}
}

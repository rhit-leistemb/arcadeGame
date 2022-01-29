package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class GameComponent extends JComponent{
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ArrayList<String> lines = new ArrayList<String>();
	private LevelReader reader = new LevelReader("Levels/Level-1");
	private int space = 30;
	
	public GameComponent() {
		createGameObjectList();
	}
	
	public void createGameObjectList() {
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
					Player o = new Player(x, y);
					x+=space;
					objects.add(o);
				}else {
					x+=space;
				}
			}
			y+=space;
			x= 0;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		for (GameObject o: this.objects) {
			o.drawOn(g2);
		}		
	}	


}

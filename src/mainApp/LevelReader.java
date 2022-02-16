package mainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelReader {
	
	String fileName;
	ArrayList<String> lines = new ArrayList<String>();
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	ArrayList<AnimateObject> animateObjects = new ArrayList<AnimateObject>();
	private final int space = 20;
	private Player hero;
	
	private int bombNum = 0;
		
	public LevelReader(String fileName) {
		this.fileName = fileName;
	}
	
	public void readFile() {
		Scanner scanner = null;
		File file = null;
		

		try {
			file = new File(fileName);
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Bad File Name Try Again");
			System.exit(1);
		}
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lines.add(line);
			System.out.println(line);
		}
		this.createObjects();
		scanner.close();
	}
	
	public void createObjects() {
		int x = 0;
		int y = 0;
		for(int i = 0; i< lines.size(); i++){
			String currentLine = lines.get(i);
			for(int j = 0; j< currentLine.length(); j++) {
				int frameWidth = space * currentLine.length() - space;
				char currentChar = currentLine.charAt(j);
				if(currentChar == '|') {
					BoundaryObject o = new BoundaryObject(x, y, frameWidth);
					x+=space;
					objects.add(o);
				}else if(currentChar == '-') {
					BoundaryObject o = new BoundaryObject(x, y, frameWidth);
					x+=space;
					objects.add(o);
				}else if(currentChar == '.') {
					x+=space;
				}else if(currentChar == 'B') {
					BombCollectible o = new BombCollectible(x, y);
					x+=space;
					objects.add(o);
					bombNum++;
				}else if(currentChar == 'W') {
					WalkingEnemy o = new WalkingEnemy(x, y);
					x+=space;
					animateObjects.add(o);
					objects.add(o);
				}else if(currentChar == 'F') {
					FlyingEnemy o = new FlyingEnemy(x, y);
					x+=space;
					animateObjects.add(o);
					objects.add(o);
				}else if(currentChar == 'H') {
					hero = new Player(x, y);
					x+=space;
					animateObjects.add(hero);
					objects.add(hero);
				}else {
					x+=space;
				}
			}
			y+=space;
			x= 0;
		}
	}
	
	public ArrayList<GameObject> getObjects(){
		return objects;
	}
	
	public ArrayList<AnimateObject> getAnimateObjects(){
		return animateObjects;
	}
	
	public void resetLevel() {
		objects.clear();
		animateObjects.clear();
		lines.clear();
		hero = null;
	}
	
	public int getBombNum() {
		return bombNum;
	}
	
	public Player getHero() {
		return hero;
	}
	
	public ArrayList<String> getLines() {
		return lines;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}

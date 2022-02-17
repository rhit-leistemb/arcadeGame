package mainApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;


/**
 * 
 */
public class GameComponent extends JComponent{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ArrayList<AnimateObject> animateObjects = new ArrayList<AnimateObject>();
	private ArrayList<Integer> scoreList = new ArrayList<Integer>();
	private String fileName;
	private Boolean ready;
	private Player hero;
	private int num = 0;
	private int numTwo = 0;
	private int delayStamina = 0;
	private boolean playerGotHit = false;
	private int delayHit = 0;
	
	private int lives;
	private int bombs = 0;
	private int winScore = 0;
	private boolean won = false;
	private boolean lost = false;
	private boolean paused = false;
	
	private String currentLevel;
	
	private File titleImgFile = new File("Sprites/Title.PNG");
	private Image titleImg;
	private File winImgFile = new File("Sprites/EndScreen.PNG");
	private Image winImg;
	private File trophyImgFile = new File("Sprites/Win.PNG");
	private Image trophyImg;
	private File nextImgFile = new File("Sprites/NextLevel.PNG");
	private Image nextImg;
	private File loseImgFile = new File("Sprites/GameOver.PNG");
	private Image loseImg;
	private File congratsImgFile = new File("Sprites/Congrats.PNG");
	private Image congratsImg;
	
	private File backgroundImageFile = new File("Sprites/BackgroundLevel1.png");
	private Image backgroundImage;
	
	
	
	public GameComponent(String fileName, Boolean ready) {
		this.fileName = fileName;
		this.ready = ready;
		
		try {
			Image bufferedBackgroundImg = ImageIO.read(backgroundImageFile);
			backgroundImage = bufferedBackgroundImg.getScaledInstance(450, 450, Image.SCALE_DEFAULT);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("Cannot find background image file");
		}
		createGameObjectList();
		
		//loads in images
		try {
			BufferedImage bufferedImg = ImageIO.read(titleImgFile);
			this.titleImg = bufferedImg.getScaledInstance(300, 125, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.out.println("Cannot find title screen image file.");
		}
		try {
			this.winImg = ImageIO.read(winImgFile);
		} catch (IOException e) {
			System.out.println("Cannot find win screen image file.");
		}
		try {
			BufferedImage bufferedImg = ImageIO.read(trophyImgFile);
			this.trophyImg = bufferedImg.getScaledInstance(110, 50, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.out.println("Cannot find trophy image file.");
		}
		try {
			BufferedImage bufferedImg = ImageIO.read(congratsImgFile);
			this.congratsImg = bufferedImg.getScaledInstance(2000, 30, Image.SCALE_DEFAULT);
//			this.congratsImg = ImageIO.read(congratsImgFile);
		} catch (IOException e) {
			System.out.println("Cannot find trophy image file.");
		}
		try {
			BufferedImage bufferedImg = ImageIO.read(nextImgFile);
			this.nextImg = bufferedImg.getScaledInstance(280, 240, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.out.println("Cannot find Next Level image file.");
		}
		try {
			BufferedImage bufferedImg = ImageIO.read(loseImgFile);
			this.loseImg = bufferedImg.getScaledInstance(270, 200, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.out.println("Cannot find Game Over image file.");
		}
	}
	
	public void createGameObjectList() {
		LevelReader reader = new LevelReader(fileName);
		reader.resetLevel();
		reader.readFile();
		
		objects = reader.getObjects();
		animateObjects = reader.getAnimateObjects();
		hero = reader.getHero();	
		lives = hero.getLives();
		winScore = reader.getBombNum();
		currentLevel = reader.getLevel();
	}

	//checks if animatedObject is colliding with something
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
					react(animateObjects.get(i), objects.get(j), i, j, true);					
				}
				if (top1.intersectsLine(butt2)) {
					animateObjects.get(i).setTopHit(true); 
					react(animateObjects.get(i), objects.get(j), i, j, false);				
				}
				if (right1.intersectsLine(left2)) {
					animateObjects.get(i).setRSideHit(true); 
					react(animateObjects.get(i), objects.get(j), i, j, false);				
				}
				if (left1.intersectsLine(right2)) {
					animateObjects.get(i).setLSideHit(true); 
					react(animateObjects.get(i), objects.get(j), i, j, false);				
				}
			}
		}
	}
	
	//checks is animatedObject is no long colliding against something
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
	public void react(AnimateObject movingObj, GameObject generalObj, int aniIndex, int objIndex, boolean attack) {
		if(movingObj.getClass().getSimpleName().equals("Player")  && attack == false) {
			if(generalObj.getClass().getSimpleName().equals("BombCollectible")) {
				objects.remove(objIndex);
				bombs++;
				checkWin();
			} else if(generalObj.getClass().getSimpleName().equals("WalkingEnemy") || generalObj.getClass().getSimpleName().equals("FlyingEnemy")) {
				if(playerGotHit == false) {
					playerGotHit = true;
					lives--;
					if(lives == 0) {
						lose();
					}
				}
			}
		}
		if(movingObj.getClass().getSimpleName().equals("Player")  && attack == true) {
			if(generalObj.getClass().getSimpleName().equals("WalkingEnemy")) {
				objects.remove(objIndex);	
			}
		}
	}
	
	public void checkWin() {
		if (bombs == winScore) {
			this.won = true;
		}
	}
	
	public void lose() {
		for(int i = 0; i< animateObjects.size(); i++) {
			if(animateObjects.get(i).getClass().getSimpleName().equals("Player")) {
				animateObjects.remove(i);
			}
		}
		for(int j = 0; j< objects.size(); j++) {
			if(objects.get(j).getClass().getSimpleName().equals("Player")) {
				objects.remove(j);
			}
		}
		this.lost = true;
	}
	
	//tells player to start moving
	public void traverse() {
		checkCollision();
		hero.move();
	}
	
	public void moveEnemyRight() {
		checkCollision();
		for(int i = 0; i< animateObjects.size(); i++) {
			if(animateObjects.get(i).getClass().getSimpleName().equals("WalkingEnemy")) {
				animateObjects.get(i).moveRight();
			}
		}
	}
	
	public void moveEnemyLeft() {
		checkCollision();
		for(int i = 0; i< animateObjects.size(); i++) {
			if(animateObjects.get(i).getClass().getSimpleName().equals("WalkingEnemy")) {
				animateObjects.get(i).moveLeft();
			}
		}
	}
	
	public void enemyFlyUp() {
		for(int i = 0; i< animateObjects.size(); i++) {
			if(animateObjects.get(i).getClass().getSimpleName().equals("FlyingEnemy")) {
				animateObjects.get(i).flyUp();
			}
		}
	}
	
	public void enemyFlyDown() {
		for(int i = 0; i< animateObjects.size(); i++) {
			if(animateObjects.get(i).getClass().getSimpleName().equals("FlyingEnemy")) {
				animateObjects.get(i).flyDown();
			}
		}
	}
	
	public void update() {
		for(int i = 0; i< animateObjects.size(); i++) {
			animateObjects.get(i).updateHitLines();
		}
		this.checkFreedom();
		if(playerGotHit) {
			delayHit++;
			if(delayHit >100) {
				playerGotHit = false;
				delayHit = 0;
			}
		}
		delayStamina++;
		if(delayStamina >= 10) {
			hero.updateStamina();
			delayStamina = 0;
		}
	}
	
	public void moveEnemy() {
		for(int i = 0; i< animateObjects.size(); i++) {
			animateObjects.get(i).setPlayerX(hero.getX());
			animateObjects.get(i).setPlayerY(hero.getY());
			if(animateObjects.get(i).getClass().getSimpleName().equals("WalkingEnemy")){
				if(num < 50) {
					this.moveEnemyRight();
					num++;
				}else if(num < 100) {
					this.moveEnemyLeft();
					num++;
				}else {
					num = 0;
				}
			}else if(animateObjects.get(i).getClass().getSimpleName().equals("FlyingEnemy")){
				if(numTwo < 10) {
					this.enemyFly();
					numTwo++;
				}else {
					numTwo= 0;
				}
			}
		}
	}
	
	public void enemyFly() {
		for(int i = 0; i< animateObjects.size(); i++) {
			if(animateObjects.get(i).getClass().getSimpleName().equals("FlyingEnemy")) {
				animateObjects.get(i).move();
			}
		}
	}
	
	//sets constant velocity in a direction
	public void setDirection(int code) {
		if(code == KeyEvent.VK_UP) {
			hero.setGoUp(true);
		}
		if(code == KeyEvent.VK_LEFT) {
			hero.goLeft(true);
		}
		if(code == KeyEvent.VK_RIGHT ) {
			hero.goRight(true);
		}
		
		//checkFreedom();
	}
	//stops constant velocity in a direction
	public void stopDirection(int code) {
		if(code == KeyEvent.VK_UP) {
			hero.setGoUp(false);
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
		
		g2.drawImage(backgroundImage, 0, 0, null);

		for (GameObject o: this.objects) {
			o.drawOn(g2);
		}
		//draws appropriate screens for win
		g2.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		if(!ready) {
			int xPos = (450/2) - this.titleImg.getWidth(null)/2;
			int yPos = (450/2) - this.titleImg.getHeight(null)/2 - 70;
			g2.drawImage(this.titleImg, xPos, yPos, this.titleImg.getWidth(null), this.titleImg.getHeight(null), null);
			
			g2.setFont(new Font("MS Gothic", Font.BOLD, 17));
			g2.setPaint(Color.RED);
			g2.drawString("Press 'Enter' to Start", 120, 395);
		}
		if(won) {
			g2.setPaint(Color.BLACK);
			g2.fillRect(0, 0, 450, 450);
			if(currentLevel.equals("Levels/Level-2")) {
				int xPos = (450/2) - this.winImg.getWidth(null)/2 - 5;
				int yPos = (450/2) - this.winImg.getHeight(null)/2 - 70;
				g2.drawImage(this.winImg, xPos, yPos, this.winImg.getWidth(null), this.winImg.getHeight(null), null);
				
				int xPos2 = (450 - this.trophyImg.getWidth(null) - 15);
				int yPos2 = 120;
				g2.drawImage(this.trophyImg, xPos2, yPos2, this.trophyImg.getWidth(null), this.trophyImg.getHeight(null), null);
			
				int xPos3 = 450/2 - 70;
				int yPos3 = 450 - 150;
				g2.drawImage(this.congratsImg, xPos3, yPos3, this.trophyImg.getWidth(null), this.congratsImg.getHeight(null), null);
				
				g2.setPaint(Color.YELLOW);
				g2.drawString("Press 'R' to restart", 320, 13);
				
				g2.setFont(new Font("MS Gothic", Font.BOLD, 30));
				int points1 = bombs * 10;
				int points2 = lives * 20;
				int pointsTot = points1 + points2;
				
				for(int i = 0; i < scoreList.size(); i++) {
					pointsTot+=scoreList.get(i);
				}
				g2.drawString("Total Score: " + pointsTot, 100, 380);
			} else {
				int xPos = (450/2) - this.nextImg.getWidth(null)/2 - 5;
				int yPos = (450/2) - this.nextImg.getHeight(null)/2 - 70;
				g2.drawImage(this.nextImg, xPos, yPos, this.nextImg.getWidth(null), this.nextImg.getHeight(null), null);
				
				g2.setPaint(Color.YELLOW);
				g2.setFont(new Font("MS Gothic", Font.PLAIN, 15));
				g2.drawString("Congratulations! Press 'U' for the next level.", 33, 350);
				
			
				g2.drawString("Bombs:" + bombs, 160, 135);
				g2.drawString("Lives: " + lives, 160, 155);
//				g2.drawString("Time", 138, 175);
//				g2.drawString("Stamina", 138, 195);
		
				int points1 = bombs * 10;
				g2.drawString(" " + points1, 250, 135);
				int points2 = lives * 20;
				g2.drawString(" " + points2, 250, 155);
//				g2.drawString("", 200, 175);
//				g2.drawString("", 200, 195);
				
				g2.setFont(new Font("MS Gothic", Font.BOLD, 15));
				g2.drawString("Items", 163, 105);
				g2.drawString("Points", 240, 105);
				int pointsTot = points1 + points2;
				scoreList.add(pointsTot);
				g2.drawString("Total Score: " + pointsTot, 157, 220);
			}
		}
		//draws appropriate screens for loss
		if(lost) {
			g2.setPaint(new Color(0, 0, 0, 150));
			g2.fillRect(0, 0, 450, 450);
			
			g2.setPaint(Color.YELLOW);
			g2.drawString("Press 'R' to restart", 275, 13);
			
			int xPos2 = (450/2) - this.loseImg.getWidth(null)/2 - 5;
			int yPos2 = (450/2) - this.loseImg.getHeight(null)/2 - 70;
			g2.drawImage(this.loseImg, xPos2, yPos2, this.loseImg.getWidth(null), this.loseImg.getHeight(null), null);
		}
		
		if(paused) {
			int xPos = (450/2) - this.nextImg.getWidth(null)/2 - 5;
			int yPos = (450/2) - this.nextImg.getHeight(null)/2 - 70;
			g2.drawImage(this.nextImg, xPos, yPos, this.nextImg.getWidth(null), this.nextImg.getHeight(null), null);

			g2.setPaint(new Color(0, 0, 0, 170));
			g2.fillRect(0, 0, 450, 450);
			
			g2.setPaint(Color.YELLOW);
			g2.setFont(new Font("MS Gothic", Font.BOLD, 15));
			g2.drawString("Key", 130, 100);
			g2.setFont(new Font("MS Gothic", Font.PLAIN, 15));
			g2.drawString("P", 135, 135);
			g2.drawString("U", 135, 155);
			g2.drawString("D", 135, 175);
			g2.drawString("R", 135, 195);


			
			g2.setFont(new Font("MS Gothic", Font.BOLD, 15));
			g2.drawString("Action", 200, 100);
			g2.setFont(new Font("MS Gothic", Font.PLAIN, 15));
			g2.drawString("Pauses the game", 200, 135);
			g2.drawString("Next Level", 200, 155);
			g2.drawString("Previous Level", 200, 175);
			g2.drawString("Restarts Game", 200, 195);


			
		}
	
////	draws hitboxes
//		g2.setColor(Color.WHITE);
//		for (int j = 0; j < objects.size(); j++) {
//			Line2D.Double top2 = objects.get(j).getTopLine();
//			Line2D.Double butt2 = objects.get(j).getButtLine();
//			Line2D.Double right2 = objects.get(j).getRightLine();
//			Line2D.Double left2 = objects.get(j).getLeftLine();
//			
//			g2.draw(top2);
//			g2.draw(butt2);
//			g2.draw(right2);
//			g2.draw(left2);
//		}
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
		scoreList.clear();
		hero = null;
		won = false;
		lost = false;
		bombs = 0;
		lives = 3;
		createGameObjectList();	
	}
	
//getters and setters	
	public boolean getWon() {
		if(won == true) {
			return true;
		}
		return false;
	}
	public boolean getLost() {
		if(lost == true) {
			return true;
		}
		return false;
	}
	public int getLives() {
		return this.lives;
	}
	public int getScore() {
		return this.bombs;
	}
	
	public int getStamina() {
		return hero.getStamina();
	}

	public void setPause() {
		this.paused = !this.paused;
	}
	
}

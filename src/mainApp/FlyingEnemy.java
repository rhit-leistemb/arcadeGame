package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * Class: FlyingEnemy
 * @author Bhargav Nagalamadaka and Mathew Leister
 * <br>Purpose: Draws and allows for movement of the flying type of enemy
 * <br>Restrictions: Doesn't apply to other enemy types
 * <br>For example: 
 * <pre>
 *    FlyingEnemy exFlyingEnemy = new FlyingEnemy(5, 5);
 * </pre>
 */
public class FlyingEnemy extends AnimateObject{
	
	private final String enemyStanding = "FlyingEnemySprites/FlyingEnemyStandingNoBG.png";
	private final String enemyDown = "FlyingEnemySprites/FlyingEnemyDownNoBG.png";
	private final String enemyRight = "FlyingEnemySprites/FlyingEnemyRightNoBG.png";
	private final String enemyLeft = "FlyingEnemySprites/FlyingEnemyLeftNoBG.png";
	
	private File enemyImageFile = new File(enemyStanding);
	private Image enemyImage;
	private int playerX;
	private int playerY;
	private int speed = 2;

	public FlyingEnemy(int x, int y) {
		super(x, y);
		
	}
	
	public void drawOn(Graphics2D g) {
		g.drawImage(enemyImage, getX(), getY(), getWidth(), getHeight(), null);
	}
	
	public void setEnemyImageFile(String fileName) {
		enemyImageFile = new File(fileName);
		this.updateImage();
	}
	
	public void updateImage() {
		try {
			BufferedImage bufferedImg = ImageIO.read(enemyImageFile);
			enemyImage = bufferedImg.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.out.println("Cannot find flying enemy image file.");
		}
	}
	
	/**
	 * ensures: FlyingEnemy tracking of the player based on it's coordinates
	 */
	public void move() {
		int diffX = this.getX()-playerX;
		int diffY = this.getY()-playerY;
		if(Math.abs(diffX)>Math.abs(diffY)) {
			if(diffX<0) {
				this.moveRight();
			}else if(diffX>0) {
				this.moveLeft();
			}
		}else{
			if(diffY<0) {
				this.flyDown();
			}else if(diffY>0){
				this.flyUp();
			}
		}
	}
	
	public void flyUp() {
		if(this.getTopHit() == false) {
			this.setEnemyImageFile(enemyStanding);
			this.setY(this.getY()-speed);
		}else if(this.getLSideHit()==false){
			this.setEnemyImageFile(enemyLeft);
			this.setX(this.getX()-speed);
		}else if(this.getRSideHit()==false) {
			this.setEnemyImageFile(enemyRight);
			this.setX(this.getX()+speed);
		}else if(this.getTopHit()==false){
			this.setEnemyImageFile(enemyStanding);
			this.setY(this.getY()-speed);
		}
	}
	public void flyDown() {
		if(this.getButtHit() == false) {
			this.setEnemyImageFile(enemyDown);
			this.setY(this.getY()+speed);
		}else if(this.getLSideHit()==false){
			this.setEnemyImageFile(enemyLeft);
			this.setX(this.getX()-speed);
		}else if(this.getRSideHit()==false) {
			this.setEnemyImageFile(enemyRight);
			this.setX(this.getX()+speed);
		}else if(this.getTopHit()==false){
			this.setEnemyImageFile(enemyStanding);
			this.setY(this.getY()-speed);
		}
		
	}
	
	public void moveRight() {
		if(this.getRSideHit() == false) {
			this.setEnemyImageFile(enemyRight);
			this.setX(this.getX()+speed);
		}else if(this.getTopHit()==false){
			this.setEnemyImageFile(enemyStanding);
			this.setY(this.getY()-speed);
		}else if(this.getButtHit()==false) {
			this.setEnemyImageFile(enemyDown);
			this.setY(this.getY()+speed);
		}else if(this.getLSideHit()==false){
			this.setEnemyImageFile(enemyLeft);
			this.setX(this.getX()-speed);
		}
	}
	
	public void moveLeft() {
		if(this.getLSideHit() == false) {
			this.setEnemyImageFile(enemyLeft);
			this.setX(this.getX()-speed);
		}else if(this.getTopHit()==false){
			this.setEnemyImageFile(enemyStanding);
			this.setY(this.getY()-speed);
		}else if(this.getButtHit()==false) {
			this.setEnemyImageFile(enemyDown);
			this.setY(this.getY()+speed);
		}else if(this.getRSideHit()==false) {
			this.setEnemyImageFile(enemyRight);
			this.setX(this.getX()+speed);
		}
	}
	
	/**
	 * ensures: modifies gravity to be work for the enemy's flying capability 
	 */
	public void gravity() {
		return;
	}
	
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}
}

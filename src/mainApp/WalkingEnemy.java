package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class: WalkingEnemy
 * @author Bhargav Nagalamadaka and Mathew Leister
 * <br>Purpose: Draws and houses attributes as well as behaviors of the walking enemy type
 * <br>Restrictions: Doesn't apply to other enemy types
 * <br>For example: 
 * <pre>
 *    WalkingEnemy exWalkingEnemy = new WalkingEnemy(5, 5);
 * </pre>
 */
public class WalkingEnemy extends Enemy{
	
	private final String enemyFalling = "WalkingEnemySprites/WalkingEnemyFallingNoBG.png";
	private final String enemyStanding = "WalkingEnemySprites/WalkingEnemyStandingNoBG.png";
	private final String enemyLeft1 = "WalkingEnemySprites/WalkingEnemyLeft1NoBG.png";
	private final String enemyLeft2 = "WalkingEnemySprites/WalkingEnemyLeft2NoBG.png";
	private final String enemyLeft3 = "WalkingEnemySprites/WalkingEnemyLeft3NoBG.png";
	private final String enemyRight1 = "WalkingEnemySprites/WalkingEnemyRight1NoBG.png";
	private final String enemyRight2 = "WalkingEnemySprites/WalkingEnemyRight2NoBG.png";
	private final String enemyRight3 = "WalkingEnemySprites/WalkingEnemyRight3NoBG.png";
	
	private int speed = 2;
	private File enemyImageFile = new File(enemyStanding);
	private Image enemyImage;
	
	public WalkingEnemy(int x, int y) {
		super(x, y);
		this.updateImage();
	}
	
	public void drawOn(Graphics2D g) {
		g.drawImage(enemyImage, getX(), getY(), getWidth(), getHeight(), null);
	}
	
	
	public void moveRight() {
		if(this.getRSideHit() == false&&this.getButtHit()==true) {
			this.setEnemyImageFile(enemyRight1);
			this.setX(this.getX() + speed);
		}else if(this.getButtHit()){
			this.setEnemyImageFile(enemyStanding);
		}else {
			this.setEnemyImageFile(enemyFalling);
		}
	}
	
	public void moveLeft() {
		if(this.getLSideHit() == false&&this.getButtHit()==true) {
			this.setEnemyImageFile(enemyLeft1);
			this.setX(this.getX() - speed);
		}else if(this.getButtHit()){
			this.setEnemyImageFile(enemyStanding);
		}else {
			this.setEnemyImageFile(enemyFalling);
		}
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
			System.out.println("Cannot find walking enemy image file.");
		}
	}
	

}

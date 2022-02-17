package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class FlyingEnemy extends Enemy{
	
	private final String enemyStanding = "FlyingEnemySprites/FlyingEnemyStandingNoBG.png";
	private final String enemyDown = "FlyingEnemySprites/FlyingEnemyDownNoBG.png";
	private final String enemyRight = "FlyingEnemySprites/FlyingEnemyRightNoBG.png";
	private final String enemyLeft = "FlyingEnemySprites/FlyingEnemyLeftNoBG.png";
	
	private File enemyImageFile = new File(enemyStanding);
	private Image enemyImage;

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
	
	public void flyUp() {
		if(this.getTopHit() == false) {
			this.setEnemyImageFile(enemyStanding);
			this.setY(this.getY()-2);
		}
	}
	public void flyDown() {
		if(this.getButtHit() == false) {
			this.setEnemyImageFile(enemyDown);
			this.setY(this.getY()+2);
		}
	}
	
	public void moveRight() {
		if(this.getRSideHit() == false) {
			this.setEnemyImageFile(enemyRight);
			this.setX(this.getX()+2);
		}
	}
	
	public void moveLeft() {
		if(this.getLSideHit() == false) {
			this.setEnemyImageFile(enemyLeft);
			this.setX(this.getX()-2);
		}
	}
	
	public void gravity() {
		return;
	}
}

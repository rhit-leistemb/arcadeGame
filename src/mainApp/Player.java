package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends AnimateObject {

	//private Color playerColor = Color.GREEN;
	private int speed = 2;
	private int score = 0;
	private int lives = 467;
	private File playerImageFile = new File("Sprites/BombJackPlayerSprite.png");
	private Image playerImage;
	
	public Player(int x, int y) {
		super(x, y);
		//this.setColor(playerColor);
		try {
			BufferedImage bufferedImg = ImageIO.read(playerImageFile);
			playerImage = bufferedImg.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.out.println("Cannot find player image file.");
		}
	}

	public void move(GameComponent comp) {
		comp.checkCollision();
		if(this.isGoUp() == true && this.getTopHit() == false) {
			this.setY(this.getY() - speed);
			isMoving = true;
		} else if(this.isGoUp() == false) {
			isMoving = false;
		}
		if(this.isGoLeft() == true && this.getLSideHit() == false) {
			this.setX(this.getX() - speed);
		}
		if(this.isGoRight() == true && this.getRSideHit() == false) {
			this.setX(this.getX() + speed);
		}
	}
	
	public void drawOn(Graphics2D g) {
		g.drawImage(playerImage, getX(), getY(), getWidth(), getHeight(), null);
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	

}



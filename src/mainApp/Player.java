package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends AnimateObject {

	private final String playerFallingDown = "PlayerSprites/PlayerFallingDownNoBG.png";
	private final String playerFallingDownLeft = "PlayerSprites/PlayerFallingDownToLeftNoBG.png";
	private final String playerFallingDownRight = "PlayerSprites/PlayerFallingDownToRightNoBG.png";
	private final String playerFlyingUp = "PlayerSprites/PlayerFlyingUpNoBG.png";
	private final String playerFlyingUpLeft = "PlayerSprites/PlayerFlyingUpToLeftNoBG.png";
	private final String playerFlyingUpRight = "PlayerSprites/PlayerFlyingUpToRightNoBG.png";
	private final String playerStanding = "PlayerSprites/PlayerStandingSpriteNoBG.png";
	private final String playerWalkingLeft1 = "PlayerSprites/PlayerWalkingLeft1NoBG.png";
	private final String playerWalkingLeft2 = "PlayerSprites/PlayerWalkingLeft2NoBG.png";
	private final String playerWalkingLeft3 = "PlayerSprites/PlayerWalkingLeft3NoBG.png";
	private final String playerWalkingLeft4 = "PlayerSprites/PlayerWalkingLeft4NoBG.png";
	private final String playerWalkingRight1 = "PlayerSprites/PlayerWalkingRight1NoBG.png";
	private final String playerWalkingRight2 = "PlayerSprites/PlayerWalkingRight2NoBG.png";
	private final String playerWalkingRight3 = "PlayerSprites/PlayerWalkingRight3NoBG.png";
	private final String playerWalkingRight4 = "PlayerSprites/PlayerWalkingRight4NoBG.png";
	
	private int stamina = 100;
	private int speed = 2;
	private int lives = 1;
	private File playerImageFile = new File(playerStanding);
	private Image playerImage;
	private ArrayList<BulletObject> bullets = new ArrayList<BulletObject>();
	
	public Player(int x, int y) {
		super(x, y);
		this.updateImage();
	}

	public void move() {
		if(this.isGoUp() == true && this.getTopHit() == false&&stamina>0) {
			this.setPlayerImageFile(playerFlyingUp);
			this.setY(this.getY() - speed);
			isMoving = true;
		}else if(this.isGoUp() == true && this.getButtHit() && stamina<=0) {
			this.setPlayerImageFile(playerStanding);
			isMoving = false;
		}else if(this.isGoUp() == true && this.getButtHit()== false && stamina<=0) {
			this.setPlayerImageFile(playerFallingDown);
			isMoving = false;
		} else if(this.isGoUp() == false && this.getButtHit()) {
			this.setPlayerImageFile(playerStanding);
			isMoving = false;
		}else if(this.isGoUp() == false && this.getButtHit()== false) {
			this.setPlayerImageFile(playerFallingDown);
			isMoving = false;
		}
		if(this.isGoLeft() == true && this.getLSideHit() == false) {
			this.setPlayerImageFile(playerWalkingLeft1);
			this.setX(this.getX() - speed);
		}
		if(this.isGoRight() == true && this.getRSideHit() == false) {
			this.setPlayerImageFile(playerWalkingRight1);
			this.setX(this.getX() + speed);
		}
		
		if(this.isGoRight() && this.isGoUp()&&stamina>0) {
			this.setPlayerImageFile(playerFlyingUpRight);
		}
		
		if(this.isGoLeft() && this.isGoUp()&&stamina>0) {
			this.setPlayerImageFile(playerFlyingUpLeft);
		}
		if(this.isGoLeft() && this.isGoUp() && this.getButtHit()==false&&stamina<=0) {
			this.setPlayerImageFile(playerFallingDownLeft);
			isMoving = false;
		}
		if(this.isGoLeft() && this.isGoUp()==false && this.getButtHit()==false) {
			this.setPlayerImageFile(playerFallingDownLeft);
			isMoving = false;
		}
		if(this.isGoRight() && this.isGoUp()==false && this.getButtHit()==false&&stamina<=0) {
			this.setPlayerImageFile(playerFallingDownRight);
			isMoving = false;
		}
		if(this.isGoRight() && this.isGoUp()==false && this.getButtHit()==false) {
			this.setPlayerImageFile(playerFallingDownRight);
			isMoving = false;
		}
	}
	
	public void updateStamina() {
		if(this.isGoUp()) {
			stamina-=10;
			this.setStamina(stamina);
		}else {
			stamina+=2;
			this.setStamina(stamina);
		}
	}
	
	public void setPlayerImageFile(String fileName) {
		playerImageFile = new File(fileName);
		this.updateImage();
	}
	
	public void updateImage() {
		try {
			BufferedImage bufferedImg = ImageIO.read(playerImageFile);
			playerImage = bufferedImg.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.out.println("Cannot find player image file.");
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

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		if(stamina<=0) {
			this.stamina = 0;
		}else if(stamina>=100) {
			this.stamina = 100;
		}else{
			this.stamina = stamina;
		}
	}
	

}



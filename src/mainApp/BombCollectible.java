package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BombCollectible extends GameObject{
	
	//private Color bombColor = Color.RED;
	private File bombImageFile = new File("Sprites/BombCollectibleNoBG.png");
	private Image bombImage;

	public BombCollectible(int x, int y) {
		super(x, y);
		//this.setColor(bombColor);
		try {
			BufferedImage bufferedImg = ImageIO.read(bombImageFile);
			bombImage = bufferedImg.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.out.println("Cannot find bomb image file.");
		}
	}
	
	public void drawOn(Graphics2D g) {
		g.drawImage(bombImage, getX(), getY(), getWidth()-getWidth()/10, getHeight(), null);
	}

}

package mainApp;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class: PowerUpCollectible
 * @author Bhargav Nagalamadaka and Mathew Leister
 * <br>Purpose: Draws and houses attributes of the power up
 * <br>Restrictions: None
 * <br>For example: 
 * <pre>
 *    PowerUpCollectible exPowerUpCollectible = new PowerUpCollectible(5, 5);
 * </pre>
 */
public class PowerUpCollectible extends GameObject{
	
	private File powerUpImageFile = new File("Sprites/PowerUpCollectibleNoBG.png");
	private Image powerUpImage;
	
	public PowerUpCollectible(int x, int y) {
		super(x,y);
		try {
			BufferedImage bufferedImg = ImageIO.read(powerUpImageFile);
			powerUpImage = bufferedImg.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.out.println("Cannot find power up image file.");
		}
	}
	
	public void drawOn(Graphics2D g) {
		g.drawImage(powerUpImage, getX(), getY(), getWidth()-getWidth()/10, getHeight()-getHeight()/10, null);
	}
	
}

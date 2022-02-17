package mainApp;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BoundaryObject extends GameObject{
	
	private File platformImageFile = new File("Sprites/PlatformImage.png");
	private Image platformImage;
	
	public BoundaryObject(int x, int y, int maxY) {
		super(x, y);
		try {
			BufferedImage bufferedImg = ImageIO.read(platformImageFile);
			platformImage = bufferedImg.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.out.println("Cannot find platform image file.");
		}
		
		if(x == 0 || x == maxY) {
			super.buttLine = new Line2D.Double(super.getX() + 1, super.getY() + super.getHeight(), super.getX() + super.getWidth() - 1, super.getY() + super.getHeight() );
			super.topLine = new Line2D.Double(super.getX() + 1, super.getY(), super.getX() + super.getWidth() - 1, super.getY());
			super.rightLine = new Line2D.Double(super.getX() + super.getWidth() , super.getY(), super.getX() + super.getWidth(), super.getY() + super.getHeight());
			super.leftLine = new Line2D.Double(super.getX(), super.getY(), super.getX(), super.getY() + super.getHeight());
		}
		
	}
	
	public void drawOn(Graphics2D g) {
		g.drawImage(platformImage, getX(), getY(), getWidth(), getHeight(), null);
	}


}

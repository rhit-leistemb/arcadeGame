package mainApp;

import java.awt.geom.Line2D;

public class BoundaryObject extends GameObject{
	
	public BoundaryObject(int x, int y, int maxY) {
		super(x, y);
		if(x == 0 || x == maxY) {
			super.buttLine = new Line2D.Double(super.getX() + 1, super.getY() + super.getHeight(), super.getX() + super.getWidth() - 1, super.getY() + super.getHeight() );
			super.topLine = new Line2D.Double(super.getX() + 1, super.getY(), super.getX() + super.getWidth() - 1, super.getY());
			super.rightLine = new Line2D.Double(super.getX() + super.getWidth() , super.getY(), super.getX() + super.getWidth(), super.getY() + super.getHeight());
			super.leftLine = new Line2D.Double(super.getX(), super.getY(), super.getX(), super.getY() + super.getHeight());
		}
		
	}
	


}

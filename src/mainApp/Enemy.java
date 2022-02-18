package mainApp;

import java.awt.Color;

/**
 * Class: Enemy
 * @author Bhargav Nagalamadaka and Mathew Leister
 * <br>Purpose: Creates enemies in the game
 * <br>Restrictions: None
 * <br>For example: 
 * <pre>
 *    Enemy exEnemy = new Enemy(5, 5);
 * </pre>
 */
public class Enemy extends AnimateObject{

	private Color enemyColor = Color.cyan;
	
	public Enemy(int x, int y) {
		super(x, y);
		this.setColor(enemyColor);
	}

}

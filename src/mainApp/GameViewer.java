package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 * 
 */
public class GameViewer {
	private ArrayList<String> fileNames = new ArrayList<String>();
	public static final int FRAME_WIDTH = 450;
	public static final int FRAME_HEIGHT = 450;
	public static final Color LIGHT_GRAY = new Color(200,200,200);
	protected static Graphics g;
	private int num = 1;
	
	
public GameViewer() {
	}

	public void createGame() {
		fileNames.add("Levels/Level-1");
		fileNames.add("Levels/Level-2");
		
		JFrame frame = new JFrame();
		frame.setTitle("Arcade Game-Level 1");
		frame.setPreferredSize( new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.gray);
		
		JPanel informationPanel = new JPanel();
		//informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.X_AXIS));
		JLabel score = new JLabel("Score: 0", JLabel.LEFT);
		score.setFont(new Font("Verdana", Font.PLAIN, 18));
		JLabel lives = new JLabel("Lives: 0", JLabel.RIGHT);
		lives.setFont(new Font("Verdana", Font.PLAIN, 18));
		informationPanel.add(score);
		informationPanel.add(lives);
		informationPanel.setBackground(Color.LIGHT_GRAY);
		
		GameComponent component = new GameComponent(fileNames.get(0));
		
		frame.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {	
			
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//consider changing to getKeyCode
				char key = e.getKeyChar();
				int code = e.getKeyCode();
				if(key == 'u') {
					component.setFileName(fileNames.get(1));
					frame.setTitle("Arcade Game-Level 2");
				} else if(key == 'd') {
					component.setFileName(fileNames.get(0));
					frame.setTitle("Arcade Game-Level 1");
				} 
				component.setDirection(code);
				component.repaint();
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int code = e.getKeyCode();
				component.stopDirection(code);
				component.repaint();
			
			}	
		});
		
		Timer timer = new Timer(20, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				component.update();
				component.checkCollision();
//				hasWon(component.getCompleted());
				component.traverse();
				component.moveEnemy();
				component.gravity();
				component.repaint();
				frame.repaint();
			}	
		});
		timer.start();
		
//		public void hasWon(boolean won) {
//			if(won) {
//				
//			} else {
//				return;
//			}
//		}
		frame.add(informationPanel, BorderLayout.SOUTH);
		frame.add(component, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);	
	}
	
	

	
}

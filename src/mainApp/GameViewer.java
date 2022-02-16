package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
//	private File winImgFile = new File("Sprites/EndScreen.PNG");
//	private Image winImg;
	
	private boolean paused = false;
	private boolean won = false;
	
	private int scoreNum = 0;
	private int livesNum = 3;
	JLabel score;
	JLabel lives;
	
	JFrame frame;
	JPanel informationPanel;
	
	public GameViewer() {
//		try {
//			this.winImg = ImageIO.read(winImgFile);
//		} catch (IOException e) {
//			System.out.println("Cannot find win screen image file.");
//		}
	}

	public void createGame() {
		fileNames.add("Levels/Level-1");
		fileNames.add("Levels/Level-2");
		
		frame = new JFrame();
		frame.setTitle("Arcade Game-Level 1");
		frame.setPreferredSize( new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.gray);
		
		informationPanel = new JPanel();
		//informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.X_AXIS));
		score = new JLabel("Score: " + scoreNum, JLabel.LEFT);
		score.setFont(new Font("Verdana", Font.PLAIN, 18));
		
		lives = new JLabel("Lives: " + livesNum, JLabel.RIGHT);
		lives.setFont(new Font("Verdana", Font.PLAIN, 18));
		
		informationPanel.add(score);
		informationPanel.add(lives);
		informationPanel.setBackground(Color.LIGHT_GRAY);
		
		GameComponent component = new GameComponent(fileNames.get(0));
		
//		JPanel startPanel = new JPanel();
//		JButton pauseButt = new JButton("P");
//		informationPanel.add(pauseButt);
//		pauseButt.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				System.out.println("Rn game is paused: " + paused);
//			}
//			
//			
//		});
		
		frame.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {	
			
			}

			@Override
			public void keyPressed(KeyEvent e) {
				char key = e.getKeyChar();
				int code = e.getKeyCode();
				if(key == 'u') {
					changeLevel(component, "Arcade Game-Level 2", 1);
				} else if(key == 'd') {
					changeLevel(component, "Arcade Game-Level 1", 0);
				} 
				if(key == 'p' && won == false) {
					paused = !paused;
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
				if(!paused) {
					component.update();
					component.checkCollision();
					hasWon(component.getWon());
					hasLost(component.getLost());
					component.traverse();
					component.moveEnemy();
					component.gravity();
					updateCount(component);
					component.repaint();
					frame.repaint();
				}
			}	
		});
		timer.start();
		
		frame.add(informationPanel, BorderLayout.SOUTH);
		frame.add(component, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);	
	}
	
	public void updateCount(GameComponent component) {
		this.livesNum = component.getLives();
		this.scoreNum = component.getScore();
		
		this.score.setText("Score: " + scoreNum);
		this.lives.setText("Lives: " + livesNum);
	}
	
	public void hasWon(boolean ended){
		if(ended) {
			this.paused = true;
			this.won = true;
			frame.setTitle("Wow bro look at u");
//			informationPanel.removeAll();
//			JLabel msg = new JLabel("Congratulations! Press the U key for the next level");
//			msg.setFont(new Font("Verdana", Font.ITALIC, 13));
//			informationPanel.add(msg);
//			frame.add(informationPanel);
		} else {
			return;
		}
	}	
	public void hasLost(boolean ended){
		if(ended) {
			this.paused = true;
			frame.setTitle(":(");
//			informationPanel.removeAll();
//			JLabel msg = new JLabel("Better luck next time...");
//			msg.setFont(new Font("Verdana", Font.ITALIC, 18));
//			informationPanel.add(msg);
//			informationPanel.repaint();
		} else {
			return;
		}
	}	
	public void changeLevel(GameComponent component, String title, int level) {
		component.setFileName(fileNames.get(level));
		frame.setTitle(title);
		this.won = false;
		this.paused = false;
	}
}
	


	


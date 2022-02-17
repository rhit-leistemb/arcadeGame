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
	
	private boolean ready = false;
	private boolean paused = false;
	private boolean won = false;
	private boolean lost = false;
	
	private int bombNum = 0;
	private int livesNum = 3;
	private int staminaNum = 100;
	JLabel bombs;
	JLabel lives;
	JLabel stamina;
	
	JFrame frame;
	JFrame frame1;
	JPanel informationPanel;
	Timer timer;
	
	public GameViewer() {
//		try {
//			this.winImg = ImageIO.read(winImgFile);
//		} catch (IOException e) {
//			System.out.println("Cannot find win screen image file.");
//		}
	}
	
	public boolean createStart() {
		frame1 = new JFrame();
		frame1.setTitle("Welcome!");
		frame1.setPreferredSize( new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameComponent component = new GameComponent("Levels/Level-0", ready);
		
		frame1.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if(code == KeyEvent.VK_ENTER) {
					ready = true;
				}
				component.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}		
			
		});
		
		timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkReady();
//				component.update();
//				component.checkCollision();
//				component.moveEnemy();
//				component.gravity();
//				component.repaint();
			}	
		});
		timer.start();
	
		frame1.add(component, BorderLayout.CENTER);
		frame1.pack();
		frame1.setVisible(true);
		
		return ready;
	}
	
	public boolean checkReady() {
		if(ready) {
			System.out.println("closed");
			timer.stop();
//			timer.removeActionListener(null);
//			timer = null;
			frame1.removeAll();
			frame1.setVisible(false);
			frame1.dispose();
			createGame();
		}
		return ready;
	}
	
	public void createGame() {
		fileNames.add("Levels/Level-1");
		fileNames.add("Levels/Level-2");
		
		frame = new JFrame();
		frame.setTitle("Arcade Game-Level 1");
		frame.setPreferredSize( new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		informationPanel = new JPanel();
		//informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.X_AXIS));
		bombs = new JLabel("Bombs: " + bombNum, JLabel.LEFT);
		bombs.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		
		lives = new JLabel("Lives: " + livesNum, JLabel.RIGHT);
		lives.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		
		stamina = new JLabel("Stamina: "+staminaNum, JLabel.RIGHT);
		stamina.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		
		informationPanel.add(bombs);
		informationPanel.add(lives);
		informationPanel.add(stamina);
		
		Color informationPanelColor = new Color(204, 204, 0);
		informationPanel.setBackground(informationPanelColor);
		

		
		GameComponent component = new GameComponent(fileNames.get(0), ready);
		
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
				if(key == 'u' && lost == false) {
					changeLevel(component, "Arcade Game-Level 2", 1);
				} else if(key == 'd' && lost == false) {
					changeLevel(component, "Arcade Game-Level 1", 0);
				} 
				if(key == 'p' && won == false && lost == false) {
					paused = !paused;
					component.setPause();
				}
				if(key == 'r') {
					if(lost == true || won == true) {
						changeLevel(component, "Arcade Game-Level 1", 0);
						lost = false;
						won = false;
					} else {
						//change logic so changeLevel iterates, then pick right level
						changeLevel(component, "Arcade Game-Level 1", 0);
						lost = false;
						won = false;
					}
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
		
		timer = new Timer(20, new ActionListener() {

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
		this.bombNum = component.getScore();
		this.staminaNum =  component.getStamina();
		
		
		this.bombs.setText("Bomb: " + bombNum +"     ");
		this.lives.setText("Lives: " + livesNum+"     ");
		this.stamina.setText("Stamina: "+ staminaNum);
	}
	
	
	
	public void hasWon(boolean ended){
		if(ended) {
			this.paused = true;
			this.won = true;
			this.lost = false;
//			informationPanel.removeAll();
			informationPanel.setVisible(false);
//			JLabel msg = new JLabel("Congratulations! Press the U key for the next level");
//			msg.setFont(new Font("Verdana", Font.ITALIC, 13));
			//informationPanel.add(msg);
			//informationPanel.setVisible(true);
//			frame.add(informationPanel);
		} else {
			return;
		}
	}	
	public void hasLost(boolean ended){
		if(ended) {
			this.paused = true;
			this.lost = true;
			this.won = false;
			frame.setTitle(":(");
//			informationPanel.setVisible(false);
			//informationPanel.removeAll();
//			JLabel msg = new JLabel("Better luck next time...");
//			msg.setFont(new Font("Verdana", Font.ITALIC, 18));
//			informationPanel.add(msg);
			//informationPanel.revalidate();
			//informationPanel.repaint();
		} else {
			return;
		}
	}	
	public void changeLevel(GameComponent component, String title, int level) {
		component.setFileName(fileNames.get(level));
		frame.setTitle(title);
		informationPanel.setVisible(true);
		this.won = false;
		this.paused = false;
	}
	
	public boolean getReady() {
		return ready;
	}
}
	


	


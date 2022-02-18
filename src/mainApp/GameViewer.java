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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 * Class: GameViewer
 * @author Bhargav Nagalamadaka and Mathew Leister
 * <br>Purpose: Monitors user input and creates as well as tells the game to change in response to that input
 * <br>Restrictions: None
 * <br>For example: 
 * <pre>
 *    GameViewer exGameViewer = new GameViewer();
 * </pre>
 */
public class GameViewer {
	private ArrayList<String> fileNames = new ArrayList<String>();
	public static final int FRAME_WIDTH = 450;
	public static final int FRAME_HEIGHT = 450;
	public static final Color LIGHT_GRAY = new Color(200,200,200);
	
	protected static Graphics g;
	
	private boolean ready = false;
	private boolean paused = false;
	private boolean won = false;
	private boolean lost = false;
	
	private int bombNum = 0;
	private int livesNum = 3;
	private int staminaNum = 100;
	private int levelIndex = 0;
	JLabel bombs;
	JLabel lives;
	JLabel stamina;
	
	JFrame frame;
	JFrame frame1;
	JPanel informationPanel;
	JPanel staminaPanel;
	JPanel staminaBar;
	Timer timer;
	
	public GameViewer() {
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
				
			}		
			
		});
		
		timer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkReady();
				component.update();
				component.checkCollision();
				component.moveEnemy();
				component.gravity();
				component.repaint();
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
		fileNames.add("Levels/Level-3");
		
		frame = new JFrame();
		frame.setTitle("Arcade Game-Level 1");
		frame.setPreferredSize( new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		informationPanel = new JPanel();
		staminaPanel = new JPanel();
		
		staminaPanel.setPreferredSize(new Dimension(110, 20));
		staminaPanel.setBackground(Color.black);
		staminaPanel.setOpaque(false);
		Border blackLine = BorderFactory.createLineBorder(Color.black);
		staminaPanel.setBorder(blackLine);
		
		
		
		staminaBar = new JPanel();
		staminaBar.setPreferredSize(new Dimension(100, 10));
		staminaBar.setLocation(0, 0);
		staminaBar.setBackground(Color.green);
		
		staminaPanel.add(staminaBar);
		bombs = new JLabel("Bombs: " + bombNum, JLabel.LEFT);
		bombs.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		
		lives = new JLabel("Lives: " + livesNum, JLabel.RIGHT);
		lives.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		
		stamina = new JLabel("Stamina: "+staminaNum, JLabel.RIGHT);
		stamina.setFont(new Font("MS Gothic", Font.PLAIN, 10));
		
		informationPanel.add(bombs);
		informationPanel.add(lives);

		informationPanel.add(staminaPanel);
		Color informationPanelColor = new Color(204, 204, 0);
		informationPanel.setBackground(informationPanelColor);
		
		GameComponent component = new GameComponent(fileNames.get(0), ready);
		
		
		frame.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {	
			
			}

			@Override
			public void keyPressed(KeyEvent e) {
				char key = e.getKeyChar();
				int code = e.getKeyCode();
				if(key == 'u' && lost == false) {
					if(levelIndex != fileNames.size()-1) {
						levelIndex++;
					}
					
					changeLevel(component, "Arcade Game-Level "+(levelIndex+1), levelIndex);
				} else if(key == 'd' && lost == false) {
					if(levelIndex != 0) {
						levelIndex--;
					}
					changeLevel(component, "Arcade Game-Level "+(levelIndex+1), levelIndex);
				} 
				if(key == 'p' && won == false && lost == false) {
					paused = !paused;
					component.setPause();
				}
				if(key == 'r') {
					if(lost == true || won == true) {
						levelIndex = 0;
						changeLevel(component, "Arcade Game-Level 1", 0);
						lost = false;
						won = false;
					} else {
						levelIndex = 0;
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
					component.checkCollision();
					component.update();
					component.checkCollision();
					hasWon(component.getWon());
					hasLost(component.getLost());
					component.traverse();
					component.checkCollision();
					component.moveEnemy();
					component.checkCollision();
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
		if(staminaNum >50) {
			staminaBar.setBackground(Color.green);
		}else if(staminaNum >25){
			staminaBar.setBackground(Color.yellow);
		}else {
			staminaBar.setBackground(Color.red);
		}
		staminaBar.setSize(new Dimension(staminaNum, 10));
		staminaBar.setLocation(5, staminaPanel.getHeight()/2-5);
	}
	
	
	
	public void hasWon(boolean ended){
		if(ended) {
			this.paused = true;
			this.won = true;
			this.lost = false;
			informationPanel.setVisible(false);
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
	


	


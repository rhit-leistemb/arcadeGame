package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * 
 */
public class GameViewer {
	private ArrayList<String> fileNames = new ArrayList<String>();
	public static final int FRAME_WIDTH = 450;
	public static final int FRAME_HEIGHT = 430;
	public static final Color LIGHT_GRAY = new Color(200,200,200);
	protected static Graphics g;
	
	
//	public 
	
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
		GameComponent component = new GameComponent(fileNames.get(0));
		
		frame.addKeyListener(new KeyListener() {
			public boolean up = false;
			public boolean right = false;
			private boolean left = false;
			
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
//					component.repaint();
				} else if(key == 'd') {
					component.setFileName(fileNames.get(0));
					frame.setTitle("Arcade Game-Level 1");
//					component.repaint();
				} 
//				if (code == KeyEvent.VK_UP) {
//					this.up = true;
//				}
//				if (code == KeyEvent.VK_RIGHT) {
//					this.right = true;
//				}
//				if (code == KeyEvent.VK_LEFT) {
//					this.left = true;
//				}
//				component.traverse(e, this.up, this.right, this.left);
				component.traverse(e);
				component.repaint();
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
//				int code = e.getKeyCode();
//				if (code == KeyEvent.VK_UP) {
//					this.up = false;
//				}
//				if (code == KeyEvent.VK_RIGHT) {
//					this.right = false;
//				}
//				if (code == KeyEvent.VK_LEFT) {
//					this.left = false;
//				}
				component.hault(e);
				component.repaint();
			}
			
		});
		
		
		frame.add(component, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);	
	}
	
}

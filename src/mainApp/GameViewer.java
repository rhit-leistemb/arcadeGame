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
	public static final int FRAME_WIDTH = 670;
	public static final int FRAME_HEIGHT = 330;
	public static final Color LIGHT_GRAY = new Color(200,200,200);
	protected static Graphics g;
	
	public GameViewer() {
	}

	public void createGame() {
		fileNames.add("Levels/Level-1");
		fileNames.add("Levels/Level-2");
		
		JFrame frame = new JFrame();
		frame.setTitle("Arcade Game");
		frame.setPreferredSize( new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameComponent component = new GameComponent(fileNames.get(0));
		
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//consider changing to getKeyCode
				char key = e.getKeyChar();
				if(key == 'u') {
					component.setFileName(fileNames.get(1));
//					component.repaint();
				} else if(key == 'd') {
					component.setFileName(fileNames.get(0));
//					component.repaint();
				}
				component.traverse(e);
				component.repaint();
				
			}

			@Override
			public void keyReleased(KeyEvent e) {

				
			}
			
		});
		
		
		frame.add(component, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);	
	}
	
}

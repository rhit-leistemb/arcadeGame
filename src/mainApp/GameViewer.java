package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GameViewer {
	public static final int FRAME_WIDTH = 670;
	public static final int FRAME_HEIGHT = 330;
	public static final Color LIGHT_GRAY = new Color(200,200,200);
	
	public GameViewer() {
	}

	public void createGame() {
		JFrame frame = new JFrame();
		frame.setTitle("Arcade Game");
		frame.setPreferredSize( new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameComponent component = new GameComponent();
		frame.add(component, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);	
	}
	
}

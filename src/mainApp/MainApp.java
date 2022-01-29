package mainApp;


/**
 * Class: MainApp
 * @author Put your team name here
 * <br>Purpose: Top level class for CSSE220 Project containing main method 
 * <br>Restrictions: None
 */
public class MainApp {
	
	LevelReader reader = new LevelReader("Levels/Level-1");
	
	private void runApp() {
		//System.out.println("Write your cool arcade game here!");
		GameViewer viewer = new GameViewer();
		viewer.createGame();
		reader.readFile();
	} // runApp

	/**
	 * ensures: runs the application
	 * @param args unused
	 */
	public static void main(String[] args) {
		MainApp mainApp = new MainApp();
		mainApp.runApp();		
	} // main

}
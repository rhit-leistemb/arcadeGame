package mainApp;


/**
 * Class: MainApp
 * @author A_301
 * <br>Purpose: Top level class for CSSE220 Project containing main method 
 * <br>Restrictions: None
 */
public class MainApp {
	
	
	private void runApp() {
		//System.out.println("Write your cool arcade game here!");
		GameViewer viewer = new GameViewer();
		viewer.createStart();
		
//		boolean start = false;
//		while(!start) {
//			start = viewer.checkReady();
//			System.out.println("still running");
//			if(start) {
//				break;
//			}
//		}
//		viewer.
//		System.out.println("Moving on");
//		while(true) {
//			if(start) {
//				System.out.println("hittt");
//				viewer = new GameViewer();
//				viewer.createGame();
//				break;
//			} else {
//				System.out.println("Enter not hit yet");
//			}
//		}
			
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
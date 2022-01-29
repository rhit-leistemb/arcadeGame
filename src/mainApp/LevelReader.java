package mainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelReader {
	
	String fileName;
	ArrayList<String> lines = new ArrayList<String>();
	
	public LevelReader(String fileName) {
		this.fileName = fileName;
	}
	
	public void readFile() {
		Scanner scanner = null;
		File file = null;
		

		try {
			file = new File(fileName);
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Bad File Name Try Again");
			System.exit(1);
		}
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lines.add(line);
			System.out.println(line);
		}
		scanner.close();
	}
	
	public ArrayList<String> getLines() {
		return lines;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}

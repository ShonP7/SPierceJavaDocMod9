package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Shon Pierce
 * @version 1.1
 * @since July 7, 2020
 */

public class TextAnalyzerGUI extends Application {

	Button button1, button2;
	Rectangle topButtonBar;
	
	/**
	 * @param primaryStage is the entry point for this TextAnalyzerJavaDoc application. This is called after the init method has returned and system is ready
	 * for the application to begin running. the primary stage is where the TextAnalyzerJavaDoc application scene is set.
	 *   
	 * @throws FileInputStream throws a FileNotFoundException if the file indicated by the specified pathname has failed.
	 */
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Text Analyzer");
		
		Image find = new Image(new FileInputStream("C:\\Users\\Shon Pierce\\Desktop\\Find.jpg"));
		ImageView findView = new ImageView(find); 
		findView.setX(140); 
		findView.setY(100); 
		findView.setFitHeight(100); 
		findView.setFitWidth(100); 
		
		Image poem = new Image(new FileInputStream("C:\\Users\\Shon Pierce\\Desktop\\Poem.jpg"));
		ImageView poemView = new ImageView(poem); 
		poemView.setX(25); 
		poemView.setY(100); 
		poemView.setFitHeight(100); 
		poemView.setFitWidth(100); 
		
		button1 = new Button("_The Raven");
		
		/**
		 * @returns button1 prints the file denoted in the scanner parameter. The denoted file prints out line by line. 
		 * 
		 */
		
		button1.setOnAction(e -> {
			try {
				File file = new File("theRaven.txt");
				Scanner input;

				input = new Scanner(file);

				while (input.hasNextLine()) {
					String line = input.nextLine();
					System.out.println(line);
				}
				input.close();
				
			} catch (FileNotFoundException r) {
				System.out.println("File not Found");
				
			}	
		});
		
		
		button2 = new Button("_Generate Report");
		
		/**
		 * @returns from the file denoted in the scanner parameter, button 2 prints an array of all the words in the file and a number indicating 
		 * how many times they occur. The array is sorted in ascending based on the number of times they appear.   
		 * 
		 */
		
		button2.setOnAction(e -> {

			try (Scanner theRaven = new Scanner(new File ("theRaven.txt"));) {
				TreeMap<String, Integer> arrayInput = new TreeMap<String, Integer>();
				 
				while(theRaven.hasNext()) {
					String words = theRaven.next();
					String lowerCaseWords = words.toLowerCase();
					lowerCaseWords = lowerCaseWords.replace("," , " ");
					lowerCaseWords = lowerCaseWords.replace("." , " ");
					lowerCaseWords = lowerCaseWords.replace(";" , " ");
					lowerCaseWords = lowerCaseWords.replace(":" , " ");
					lowerCaseWords = lowerCaseWords.replace("?" , " ");
					lowerCaseWords = lowerCaseWords.replace("!" , " ");
					lowerCaseWords = lowerCaseWords.replace("'" , " ");
					lowerCaseWords = lowerCaseWords.replace("-" , " ");
					lowerCaseWords = lowerCaseWords.replace("_" , " ");
					lowerCaseWords = lowerCaseWords.replace("--" , " ");
					lowerCaseWords = lowerCaseWords.replaceAll(" ", "");
					lowerCaseWords = lowerCaseWords.replaceAll("-", "");
					
					
					if(arrayInput.containsKey(lowerCaseWords)) {
						int count = arrayInput.get(lowerCaseWords) + 1;
						arrayInput.put(lowerCaseWords, count);
					}
					else {
						arrayInput.put(lowerCaseWords, +1);	
						
						}
					} 	
				
				LinkedHashMap<String, Integer> entryOutput = arrayInput.entrySet().stream().sorted(Entry.comparingByValue())
						.collect(
								Collectors.toMap(Entry::getKey,  Entry::getValue, (x,y) -> x, LinkedHashMap::new));
						
					for(HashMap.Entry<String, Integer> sortedEntryOutput:entryOutput.entrySet()) {	
					 System.out.println(sortedEntryOutput);
					 
				}
			}
				 catch (FileNotFoundException a) {
					System.out.println("File can not be found. Check file name and resubmit");
					
				} catch (IOException a) {
				System.out.println("Problem reading the file");
				}
		});
		
		button1.setLayoutX(30);
		button1.setLayoutY(225);
		button1.setFont(new Font("Arial", 14));
		button2.setLayoutX(130); 
		button2.setLayoutY(225);
		button2.setFont(new Font("Arial", 14));	
		
		Text fileTitleText = new Text("The Raven Word Occurances Generator");
		fileTitleText.setFont(new Font("Arial",30));
		fileTitleText.setFill(Color.ORANGERED);
		fileTitleText.setX(30);
		fileTitleText.setY(40);
		
		
		Text instTitleText = new Text("Select Generate report to generate word occurances count");
		instTitleText.setFont(new Font("Arial",14));
		instTitleText.setFill(Color.ORANGERED);
		instTitleText.setX(150);
		instTitleText.setY(330);
		
		
		Group root = new Group();
		
		Scene scene = new Scene(root, 700, 350, Color.BLACK);
		
		root.getChildren().addAll(button1, button2, fileTitleText, findView, poemView, instTitleText);
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	/**
	 * 
	 * @param args this is the command line argument passed to the application. Does not return until the application has exited.  
	 */
	public static void main(String[] args) {
		launch(args);

	}
} // end TextAnalyzerGUI class



		
		

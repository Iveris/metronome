package com.warneriveris.metronome;

/*
 * Program: Metronome Application
 * Author: Warner Iveris
 * Date Created: 27 October 2020
 * Last Modified: 5 November 2020
 * Purpose: To mark intervals of time with audible clicks. 
 * 			Program is meant for musicians to use as a practice tool.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class App extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("Layout.fxml"));
			Scene scene = new Scene(root,250,250);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Metronome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {Metronome.killProgram();}
	
	public static void main(String[] args) 
	{
		Runtime.getRuntime().addShutdownHook(new Thread(() -> Metronome.killProgram()));
		launch(args);

	}
}
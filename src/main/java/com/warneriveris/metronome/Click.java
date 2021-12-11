package com.warneriveris.metronome;
// todo: Clip.open is deprecated - update 
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class Click implements Runnable{

	private File beat = new File("./src/main/resources/com/warneriveris/metronome/click.wav");
	private final Clip lineIn;
	private int startLoop = 0;
	private int endLoop; // value of frame position to end 
	private float frameRate;
	
	public Click() 
	{
		if(!beat.exists())
		{
			var<Boolean> dialog = new Dialog<>();
			var button = ButtonType.OK;
			dialog.getDialogPane().getButtonTypes().addAll(button);
			dialog.setContentText("ERROR: Sound file not found");
			dialog.showAndWait();
			System.exit(1);
		}
		
		lineIn = getLine();
		try {
			lineIn.open();
		} catch (LineUnavailableException e) {
			e.printStackTrace(); System.exit(1);}
		
	}
	
	private Clip getLine() 
	{
		Clip temp = null;
		
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(beat);
			this.frameRate = audioInput.getFormat().getFrameRate();
			
			temp = AudioSystem.getClip();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return temp;
	}
	
	@Override
	public void run() 
	{	
		lineIn.setLoopPoints(startLoop, endLoop);
		lineIn.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void setTempo(int bpm) 
	{
		// calculating the number of frames to play in the loop
		// getting the number of beats per second (or fraction thereof)
		// and multiplying that by the number of frames per second
		endLoop = (int) ((60.0 / bpm) * frameRate);
		lineIn.setFramePosition(0); // ensure that the clip starts at the beginning
	}
	
	public void stop() {
		lineIn.stop();
		lineIn.drain();
	}
	
	public void quit() {
		stop();
		lineIn.close();
	}
	
}

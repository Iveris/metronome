package com.warneriveris.metronome;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

public interface Control {

	public void mouseClick(ActionEvent event);
	public void keyPressed(KeyEvent event);
	
	public int getTempo();
	public void faster();
	public void slower();
	
	public void startStop();

}
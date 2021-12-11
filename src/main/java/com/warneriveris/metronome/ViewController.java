package com.warneriveris.metronome;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewController {
	
	@FXML
	public Label BPM = new Label();
	
	MetronomeControls controls = new Metronome();
	
	
	public void decrement() {
		int newTempo = getBPM() - 1;
		BPM.setText(Integer.toString(newTempo));
//		controls.changeTempo(newTempo);
	}
	
	public void startStop() {
//		controls.startStop(getBPM());
	}
	
	public void increment() {
		int newTempo = getBPM() + 1;
		BPM.setText(Integer.toString(newTempo));
//		controls.changeTempo(newTempo);
	}
	
	private int getBPM() {
		return Integer.parseInt(BPM.getText());
	}
}

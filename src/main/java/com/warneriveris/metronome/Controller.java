package com.warneriveris.metronome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

public class Controller implements Control {
	
	@FXML
	public Label BPM = new Label();
	@FXML
	public Button startStop = new Button();
	@FXML
	public Button faster = new Button();
	@FXML
	public Button slower = new Button();
	
	
	@Override
	public void mouseClick(ActionEvent event) 
	{
		Button button = ((Button) event.getSource());
		
		if(button.equals(faster))
		{
			faster();
			return;
		}
		if(button.equals(slower))
		{
			slower();			
		}
		if(button.equals(startStop)) 
		{
			startStop();
		}

	}

	@Override
	public void keyPressed(KeyEvent event) 
	{
		switch(event.getCode())
		{
		case UP:
			faster(); break;
		case DOWN:
			slower(); break;
		case SPACE:
			startStop(); break;
		default: break;
		}
	}

	@Override
	public int getTempo() { return Integer.parseInt(BPM.getText()); }

	private void setTempo(int bpm) { Metronome.setTempo(bpm); }

	private void playClick() { Metronome.play(); }

	private void stopClick() { Metronome.stop(); }

	public void faster()
	{
		int tempoChange = Metronome.increment(this.getTempo());
		setTempo(tempoChange);
		BPM.setText(Integer.toString(tempoChange));
	}
	
	public void slower()
	{
		int tempoChange = Metronome.deincrement(this.getTempo());
		setTempo(tempoChange);
		BPM.setText(Integer.toString(tempoChange));
	}
	
	@Override
	public void startStop()
	{
		if(Metronome.isRunning())
		{
			stopClick();
			startStop.setText("PLAY");
		}
		else
		{
			playClick();
			startStop.setText("STOP");
		}
	}// end startStop
} // end class Controller

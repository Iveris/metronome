package com.warneriveris.metronome;

public interface MetronomeControls {

	public void startStop(int BPM);
	public void changeTempo(int BPM);
	public void quit();
}

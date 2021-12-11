package com.warneriveris.metronome;

public class Metronome implements MetronomeControls{

	private static boolean keepPlaying = false; // on/off switch for metronome
	private static Thread audio; // thread used to run audio player
	private static Click click = new Click(); // audio player
	
	@Override
	public void startStop(int BPM) {
		if(keepPlaying) {
			stop();
		}
		else {
			start(BPM);
		}
	}
	
	private void start(int BPM) {
		click.setTempo(BPM);
		audio = new Thread(click);
		keepPlaying = true;
		audio.start();
	}
	private void stop() {
		click.stop();
		audio.interrupt();
		keepPlaying = false;
	}

	@Override
	public void changeTempo(int BPM) {
		stop();
		start(BPM);
	}

	public void quit() {
		stop();
		click.quit();
	}
}

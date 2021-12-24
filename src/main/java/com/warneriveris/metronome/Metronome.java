package com.warneriveris.metronome;


public class Metronome 
{
	
	private static boolean keepPlaying = false; // on/off switch for metronome
	private static Thread fred; // thread used to run audio player
	private static final ClipClick clipClick = new ClipClick(); // audio player
	private static int BPM = 60; // tempo in standard Beats per Minute
	
	public static void play(){
		validateTempo();

		clipClick.setTempo(BPM);
		fred = new Thread(clipClick);
		
		keepPlaying = true;
		fred.start();		
	} // end play
	

	public static void stop()
	{
		keepPlaying = false;
		clipClick.stop();
		if(fred != null && fred.isAlive()) { fred.interrupt(); }
		
	}
	
	public static void setTempo(int bpm){
		if(!validTempo(bpm)) {
			return;
		}
		
		BPM = bpm;
		
		if(isRunning()) {
			stop();
			play();
		}
	} // end setTempo

	// used on shutdown
	public static void killProgram() {
		stop();
		clipClick.quit();
	}
		
	// check to see if proposed tempo is valid or not
	private static boolean validTempo(int bpm)
	{
		boolean valid = false;
		if(bpm >= 30 && bpm <= 208)
		{
			valid = true;
		}
		return valid;
	}
	
	// if an invalid tempo has made it to BPM, then program closes
	private static void validateTempo() {
		if(!validTempo(BPM)) {print("Invalid Tempo"); System.exit(1);}
	}
	
	public static boolean isRunning()
	{
		return keepPlaying;
	}
	
	// increment and deincrement change the tempo according to analog metronome values
	public static int increment(int t)
	{
		if(!validTempo(t + 8))
		{
			return t;
		}
		if(t >= 144)
		{
			return (t + 8);
		}
		if(t >= 120)
		{
			return (t + 6);
		}
		
		if(t >= 72)
		{
			return (t + 4);
		}
		
		if(t >= 60)
		{
			return (t + 3);
		}
		else
		{
			return (t + 2);
		}
	}
	
	public static int deincrement(int t)
	{
		if(!validTempo(t - 2))
		{
			return t;
		}
		if(t > 144)
		{
			return (t - 8);
		}
		if(t >= 126)
		{
			return (t - 6);
		}
		
		if(t >= 76)
		{
			return (t - 4);
		}
		
		if(t >= 63)
		{
			return (t - 3);
		}
		else
		{
			return (t - 2);
		}
	}
	
	private static void print(String message) {System.out.println(message);}
}

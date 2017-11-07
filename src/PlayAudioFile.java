import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayAudioFile {
	static Clip clip;
	
	private static String filename;
//	Thread playMusicT;
	
	
	public PlayAudioFile(String filename){
		PlayAudioFile.filename = filename;
	}
	
   
	public static void play()     // ta kalsa ma byæ runable?
	{	
		System.out.println(" %%%% T2 $$$$$ graj");
		
	   	System.out.println("TEST");
       
		try
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
		    clip.start();
		     
		    System.out.println(clip.isRunning()+"TEST "+ clip);
		} catch (LineUnavailableException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
	}
	
		
	public static void stop()
	{
		clip.stop();
		clip.close();
        System.out.println("Finished. PlayAudioFile");
	}
	
//  static Thread playMusicT1 = new Thread(new Runnable()
//  {   
//      public void run()
//      {
//  	    try
//  	    {
//  	 
//  	     	
//  	    }
//  	    catch (Exception exc)
//  	    {
//  	        exc.printStackTrace(System.out);
//  	    }
//      }
//  });
  
}










import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class Main {
	
	
	public static void main(String[] args)
	{
		
		try
		{
			AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
		//	AudioFormat format2 = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
				
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
			if(!AudioSystem.isLineSupported(info)){System.err.println("b³¹d");}
//			DataLine.Info info2 = new DataLine.Info(TargetDataLine.class, format2);
//			if(!AudioSystem.isLineSupported(info2)){System.err.println("b³¹d");}

		 	final TargetDataLine targetLine1 = (TargetDataLine)AudioSystem.getLine(info);
		// 	final TargetDataLine targetLine2 = (TargetDataLine)AudioSystem.getLine(info2);
			targetLine1.open();
			//targetLine2.open();
			
			System.out.println("Start rec... first mic ");
			targetLine1.start();
			
//			System.out.println("Start rec... second mic ");
//			targetLine2.start();
//			
			Thread thread1 = new Thread() 
			{
				@Override public void run() 
				{
					AudioInputStream audioStream = new AudioInputStream(targetLine1);
					File audioFile = new File("record1.wav");
					try {AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audioFile);}
					catch(IOException ioe){ioe.printStackTrace();}
					System.out.println("Stopped rec. first mic");
				}
			};
			
//			Thread thread2 = new Thread() 
//			{
//				@Override public void run() 
//				{
//					AudioInputStream audioStream2 = new AudioInputStream(targetLine2);
//					File audioFile2 = new File("record2.wav");
//					try {AudioSystem.write(audioStream2, AudioFileFormat.Type.WAVE, audioFile2);}
//					catch(IOException ioe){ioe.printStackTrace();}
//					System.out.println("Stopped rec. second mic");
//				}
//			};		
			
			thread1.start();
		//	thread2.start();
			thread1.sleep(2000);
		//	thread2.sleep(2000);
			thread1.sleep(2000);
			targetLine1.stop();
			targetLine1.close();
//			targetLine2.stop();
//			targetLine2.close();
	
		}
		catch(LineUnavailableException lue) {lue.printStackTrace();}
		
		catch(InterruptedException ie) {ie.printStackTrace();}
		
	}
	
}

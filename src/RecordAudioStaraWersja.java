import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class RecordAudioStaraWersja{


		
	public void RecordAudio()    
	{
		try{
		  
		   // final int count =0;
		    

			

			
		}
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
	
	  static Thread recordAudioT1 = new Thread(new Runnable()
	    {   
	    	
			@Override public void run() 
			{
				try
				{
				  final ByteArrayOutputStream out  = new ByteArrayOutputStream();
				  int numBytesRead = 0;
						final AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
						DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
					//	if(!AudioSystem.isLineSupported(info)){System.err.println("wielb��d");}
					
				 	TargetDataLine targetLine1 = null;
					try
					{
						targetLine1 = (TargetDataLine)AudioSystem.getLine(info);
					} catch (LineUnavailableException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				 	int buferSize = (int)format.getSampleRate() * format.getFrameSize();
				
				 	System.out.println("buffor...  "+buferSize);
				 	final byte[] data = new byte[targetLine1.getBufferSize()];
				 	
					targetLine1.open(); // open format ?
				
					
					System.out.println("Start rec...  ");
					targetLine1.start();
				System.out.println(" %%%% T1 $$$$$$");
				
				
		
				int count = targetLine1.read(data,0 , data.length);
				System.out.println("cont  "+count);
					
				out.write(data,0,count);   // co to robi
				
				for(int i =88000; i< 88200; i++){
					System.out.println(data[i]);
				}
				System.out.println("byte  "+data[0]+" "+data[1]+" "+data[2]);
				System.out.println("length " +data.length);
				
				AudioInputStream audioStream = new AudioInputStream(targetLine1);
				File audioFile = new File("record0.wav");
			
					AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audioFile);
					Thread.sleep(1000);	
					targetLine1.stop();
					targetLine1.close();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					System.out.println("1 err " + e);
				} 	
				
				
				 catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					 
					e.printStackTrace();
					System.out.println("2 err " + e);
				} catch (LineUnavailableException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("3 err " + e);
				}
				
			//	try{
//	    			for(int i = 0; i< 100; i++){
//	    				   System.out.println("tt one ");
//	   		            Thread.sleep(1000);
//	    			}
//						
//				
//				}catch (Exception e){
//
//				e.printStackTrace();
//				}
			}	
	    });
	public static void record()
	{
		recordAudioT1.start();
	}
	
		
		

    
//	targetLine1.stop();
//	targetLine1.close();
}

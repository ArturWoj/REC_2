import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.*;

public class PobierzProbke {
	
	static long RECORD_TIME = 10000;
	static long PLAY_TIME = 5000;
	
	public static void main(String[] args) throws InterruptedException, IOException
	{
		
		try{
				
			final RecordAudio ra = new RecordAudio();
					//System.out.println("byte  "+ Arrays.toString(data));	
			String napis = "C:\\Users\\artur\\workspace\\REC\\record2.wav";
			final PlayAudioFile paf = new PlayAudioFile(napis);
	
			Thread startRecT = new Thread(new Runnable(){
				@Override public void run() 
				{
					try
					{
						ra.start();			
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}	
				}		
			});
			
			Thread stopRecT = new Thread(new Runnable(){
				@Override public void run() 
				{
					try
					{
							
						Thread.sleep(RECORD_TIME);
							//RecordAudio ra = new RecordAudio();
						//	RecordAudio.record();
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					ra.finish();
				}		
			});
			startRecT.start();
			stopRecT.start();
		


			Thread playAudioT = new Thread(new Runnable(){
				@Override public void run() 
				{

					try{
						//while(System.currentTimeMillis() < tmpTime +1000){
						long tmpTime = System.currentTimeMillis();
						System.out.println(tmpTime+"<-tmp  @#$$  "+ System.currentTimeMillis() );
						
					 	paf.play();
					 	//Thread.sleep(PLAY_TIME);
						
					// dorobiæ jakiœ stop
				
						
					}catch (Exception e){
		
					e.printStackTrace();
					}
		
				}
			});
			// zrobiæ jeden w¹tek do który odpytuje co 10 milsec i stopowac
			Thread stopAudioT = new Thread(new Runnable(){
				@Override public void run() 
				{
					try
					{
							
						Thread.sleep(PLAY_TIME);
							//RecordAudio ra = new RecordAudio();
						//	RecordAudio.record();
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					paf.stop();
				}		
			});
			playAudioT.start();
			stopAudioT.start();
			
			
			Thread test = new Thread(){
				@Override public void run() 
				{
		
					try{
		    			for(int i = 0; i< 100; i++){
		    				   System.out.println("sdfsdf ");
		   		            Thread.sleep(1000);
		    			}
							
					
					}catch (Exception e){
		
					e.printStackTrace();
					}
				}
			};
		//	test.start();
			//Thread.sleep(3000);
		   //  thread2.sleep(8000);
		//    thread1.sleep(5000);
		
			
		}
		catch(Exception lue) {lue.printStackTrace();}
		
	
	}	
}











//
//targetLine1.stop();
//targetLine1.close();
//System.out.println("koniec  "+ Arrays.toString(data));


//#############################################################
// Playing....
//byte audio[] = out.toByteArray();
//	InputStream input = new ByteArrayInputStream(audio);
//	final AudioInputStream ais = new AudioInputStream(input, format, audio.length/format.getFrameSize());
//
//// zamiana TargetDataLine -> SourceDataLine	
//DataLine.Info info2 = new DataLine.Info(SourceDataLine.class, format);
//final SourceDataLine line2 = (SourceDataLine)AudioSystem.getLine(info2);
//line2.open(format);
//line2.start();
//#############################################################



//int bufferSize = (int) format.getSampleRate() * format.getFrameSize();
//byte buffer[] = new byte[bufferSize];
// 
//
//try
//{
//	int count2=1;
//	System.out.println("PlaY 1  " +ais.read(buffer, 0, buffer.length));
//	count2 = ais.read(buffer, 0, buffer.length);
//	while ( count2 >  -1) {
//		
//		System.out.println("PlaY 2 " +ais.read(buffer, 0, buffer.length));
//		count2 = ais.read(buffer, 0, buffer.length);
//	  if (count2 > 0) {
//		  System.out.println("PlaY 3 COUNT  " +count2);
//	    line2.write(buffer, 0, count2);
//	  }
//	}
//} catch (IOException e)
//{
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//line2.drain();
//line2.close();




//System.out.println("  ww  "+numBytesRead );
//
//numBytesRead = targetLine1.read(data, 0, data.length/5);
//out.write(data, 0, numBytesRead);
//System.out.println(  );
//
//Thread thread1 = new Thread() 
//{
//	@Override public void run() 
//	{
//		AudioInputStream audioStream = new AudioInputStream(targetLine1);
//		File audioFile = new File("record1.wav");
//		try {
//			AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audioFile);
//		
//			}
//		catch(IOException ioe){ioe.printStackTrace();}
//		System.out.println("Stopped rec. first mic");
//		
//	}
//};
//
//
//
//System.out.println("  ww  "+numBytesRead );

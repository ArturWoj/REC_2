import java.io.File;
import java.io.IOException;
import java.util.Queue;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class ttttttttt implements Runnable {
	// record duration, in milliseconds

	Thread t;
	
    static final long RECORD_TIME = 5000;	// 1 minute
    static public Queue<byte[]> kolejka;
	// path of the wav file
	
    File wavFile = new File("ZzzZzzz.wav");
    AudioInputStream ais;
    
    
     TargetDataLine targetLine;
     float sampleRate;
     int sampleSizeInBits;
     int channels;
     boolean signed;
     boolean bigEndian;
     volatile boolean running = true;
   
	// format of audio file
	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

	// the line from which audio data is captured
	TargetDataLine line;

	/**
	 * Defines an audio format
	 */
	AudioFormat getAudioFormat() {
		 sampleRate = 16000;
		 sampleSizeInBits = 8;
		 channels = 2;
		 signed = true;
		 bigEndian = true;
		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
											 channels, signed, bigEndian);
		return format;
	}

	/**
	 * Captures the sound and record into a WAV file
	 */
	public void initRecord() {
		try {
			System.out.println("init ////");
			AudioFormat format = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		
			// checks if system supports the data line
			if (!AudioSystem.isLineSupported(info)) {
				System.out.println("Line not supported");
				System.exit(0);
			}
			
			targetLine = (TargetDataLine) AudioSystem.getLine(info);
			targetLine.open(format);
			 ais = new AudioInputStream(targetLine);	
		
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		} 
	}
	
    public void Start(){
    	System.out.println("Start");
        if (t == null)
        {
            try {
                if(!targetLine.isOpen()) targetLine.open();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            System.out.println("Start in");
            targetLine.start();
            t = new Thread (this);
            running=true;
            t.start ();
        }
    }

    /**
     * Metoda zatrzymująca wątek.
     */
    public  void Stop(){
    	System.out.println("Stop ");
        targetLine.stop();
        targetLine.close();
        running=false;
        t=null;
    }


    @Override
    public void run()
    {
    	System.out.println("Run ");
        while(running)
        {
        	System.out.println("Run IN");
            byte[] data = new byte[channels * sampleSizeInBits / 8];
            targetLine.read(data, 0, data.length);
       //   kolejka.add(data);
        //	ais = new AudioInputStream(targetLine);
            
            try
			{
				AudioSystem.write(ais, fileType, wavFile);
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
			try
			{
				System.out.println("Run SLEEP");
				Thread.sleep(RECORD_TIME);
				System.out.println("Run END )");
			} catch (InterruptedException e)
			{
				System.out.println("Run INddfdf");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Thread.yield();
        }	
    }
    
    
	public static void main(String[] args) {
		 ttttttttt recorder = new ttttttttt();

		
		 
		
				 recorder.initRecord();
				 recorder.Start();
	
				
					try
					{
						System.out.println("ZXXX SLEEP");
						Thread.sleep(RECORD_TIME);
						System.out.println("XXXX END )");
					} catch (InterruptedException e)
					{
						System.out.println("XXXXX INddfdf");
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 recorder.Stop();
			
		// creates a new thread that waits for a specified
		// of time before stopping
//		Thread stopper = new Thread(new Runnable() {
//			public void run() {
//				try {
//					Thread.sleep(RECORD_TIME);
//				} catch (InterruptedException ex) {
//					ex.printStackTrace();
//				}
//				System.out.println("ZzzzZZ");
//				recorder.finish();
//			}
//		});
//
//		stopper.start();

		// start recording
	//	recorder.start();
		
	//	System.out.println(kolejka);
	}
}

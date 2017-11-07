import java.io.IOException;
import java.util.Queue;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class CaptureSignal implements Runnable{
	
	   public Thread t;
		Queue<byte[]> inputSignal;

	    TargetDataLine targetLine = createTargetDataLine();
	    private volatile boolean running = true;
	    
	    
	    
	    
//		ByteArrayOutputStream out  = new ByteArrayOutputStream();
//int numBytesRead;
//byte[] data = new byte[line.getBufferSize() / 5];
//
//// Begin audio capture.
//line.start();
//
//// Here, stopped is a global boolean set by another thread.
//while (!stopped) {
//   // Read the next chunk of data from the TargetDataLine.
//   numBytesRead =  line.read(data, 0, data.length);
//   // Save this chunk of data.
//   out.write(data, 0, numBytesRead);
//}     
	
	    
	    @Override
	    public void run() {
	        while(running)
	        {
	            byte[] data = new byte[targetLine.getBufferSize()];
	            targetLine.read(data, 0, data.length);
	            System.out.println("3 "+ targetLine.read(data, 0, data.length));
	            System.out.println("3 "+ targetLine.getFramePosition());
	            System.out.println(data);
	            inputSignal.add(data);
	            System.out.println("inp  "+inputSignal);
	      //      Thread.yield();
	        }
	    }

	    public void Start(){
	    	System.out.println("1");
	    	try
			{
				targetLine.open();
			} catch (LineUnavailableException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	targetLine.start();
	    	   t = new Thread (this);
	            running=true;
	            System.out.println("2"+ running + " " + targetLine.isOpen() );
	            t.start();
	    	
//	        if (t == null)
//	        {
//	            try {
//	                if(!targetLine.isOpen()) targetLine.open();
//	            } catch (LineUnavailableException e) {
//	                e.printStackTrace();
//	            }
//	            targetLine.start();
//	            t = new Thread (this);
//	            running=true;
//	            t.start();
//	        }
	    }
		
	    public  void Stop(){
	        targetLine.stop();
	        targetLine.close();
	        running=false;
	        t=null;
	    }

		public static void main(String[] args) throws IOException, InterruptedException
		{
			
			CaptureSignal cs = new CaptureSignal();
			System.out.println("start");
			cs.Start();
			
			//Thread.sleep(5000);
			System.out.println("stop");
			cs.Stop();
			
			System.out.println(cs.inputSignal);
		}
	
		/*
		TargetDataLine line;
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, 
		    format); // format is an AudioFormat object
		if (!AudioSystem.isLineSupported(info)) {
		    // Handle the error ... 

		}
		// Obtain and open the line.
		try {
		    line = (TargetDataLine) AudioSystem.getLine(info);
		    line.open(format);
		} catch (LineUnavailableException ex) {
		    // Handle the error ... 
		}
		
	
		
			*
			*
			*
			*/		
	    public TargetDataLine createTargetDataLine(){
	    	AudioFormat format1 = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
	        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format1);

	        TargetDataLine	targetDataLine = null;
	        try
	        {
	            targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
	            targetDataLine.open(format1);
	        }
	        catch (LineUnavailableException e)
	        {
	            e.printStackTrace();
	            System.exit(1);
	        }
	        return targetDataLine;
	    }
}

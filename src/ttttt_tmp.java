//
//public class ttttt_tmp {
//	
//}
//import java.io.File;
//import java.io.IOException;
//import java.util.Queue;
//
//import javax.sound.sampled.AudioFileFormat;
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.DataLine;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.TargetDataLine;
//
//public class ttttttttt {
//	// record duration, in milliseconds
//   
//    static final long RECORD_TIME = 5000;	// 1 minute
//    static public Queue<byte[]> kolejka;
//	// path of the wav file
//	
//    
//    File wavFile = new File("ZzzZzzz.wav");
//
//	// format of audio file
//	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
//
//	// the line from which audio data is captured
//	TargetDataLine line;
//
//	/**
//	 * Defines an audio format
//	 */
//	AudioFormat getAudioFormat() {
//		float sampleRate = 16000;
//		int sampleSizeInBits = 8;
//		int channels = 2;
//		boolean signed = true;
//		boolean bigEndian = true;
//		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
//											 channels, signed, bigEndian);
//		return format;
//	}
//
//	/**
//	 * Captures the sound and record into a WAV file
//	 */
//	void start() {
//		try {
//			AudioFormat format = getAudioFormat();
//			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
//
//			// checks if system supports the data line
//			if (!AudioSystem.isLineSupported(info)) {
//				System.out.println("Line not supported");
//				System.exit(0);
//			}
//			
//			line = (TargetDataLine) AudioSystem.getLine(info);
//	
//			line.open(format);
//			line.start();	// start capturing
//	
//
//		
//			
//			System.out.println("Start capturing...");
//
//			
//			//tylko do zapisu do pliku, @@testowanie
//			AudioInputStream ais = new AudioInputStream(line);
//
//			System.out.println("Start recording...");
//		    // start recording
//			AudioSystem.write(ais, fileType, wavFile);
//			
//			
//			final byte[] data = new byte[2];
//			line.read(data, 0, data.length);
//			//kolejka.add(data);
//			System.out.println("byte  "+data[0]+" "+data[1]+" "+data[0]+" "+data.length);
//			
//			for(int i = 0; i< data.length ;i++)
//			{
//				System.out.println(data[i]+" "+i);
//			}
//
//		} catch (LineUnavailableException ex) {
//			ex.printStackTrace();
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		}
//	}
//
//	/**
//	 * Closes the target data line to finish capturing and recording
//	 */
//	void finish() {
//		line.stop();
//		line.close();
//		System.out.println("Finished");
//	}
//
//	/**
//	 * Entry to run the program
//	 */
//	public static void main(String[] args) {
//		final ttttttttt recorder = new ttttttttt();
//
//		// creates a new thread that waits for a specified
//		// of time before stopping
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
//
//		// start recording
//		recorder.start();
//		
//	//	System.out.println(kolejka);
//	}
//}

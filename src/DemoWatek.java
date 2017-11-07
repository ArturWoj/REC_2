import java.io.IOException;

public class DemoWatek {
	static int count =0;
	
	public static synchronized void dodawaj(){
		count++;
	}
	
	public static void main(String[] args){
		
		Runnable[] runners = new Runnable[10];
    	
		//** Jak zadeklarowa� w�tki (Runnable) pojedzy�czo i poza klas� anonimow�
		//Runnable rrr = new Runnable(new DemoLiczRuny(1));
	
		
    	runners[0] = new DemoLiczRuny(0);
    	Thread t3 = new Thread(runners[0]);
	
		
    	Thread t1 = new Thread(new Runnable(){
    		
    		public void run(){
    			for(int i = 0; i<100; i++){
    				dodawaj();
    				System.out.println("t1 - "+ count);
    			}
    			
    		}	
    	});
    	
    	
    	Thread t2 = new Thread(new Runnable(){
    		
    		public void run(){
    			for(int i = 0; i<100; i++){
    				dodawaj();
    				System.out.println("t2 - "+ count);
    			}
    			
    		}	
    	});
    	
    	t1.start();
    	t2.start();
    	t3.start();
    	try
		{
			t1.join();   // poczekaj a� ten w�tek si� sko�czy ( musi si� doci�gn�� do ko��a
			t2.join();
			t3.join();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	 try
		{System.out.println(" �pie count - "+ count);
			Thread.sleep(1000);
			System.out.println(" nie �pie count - "+ count);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("count - "+ count);
    	System.out.println("get class - "+ runners.getClass());
    			
    	
    }
}

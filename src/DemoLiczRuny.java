
public class DemoLiczRuny extends DemoWatek implements Runnable {
	private int id;
	
	public DemoLiczRuny(int id){
		this.id = id;	
	}
	
	@Override
    public void run() {
      //  while(true) {
		
	//	for(int i = 0; i<10000; i++){
			dodawaj();
            System.out.println("Watek "+id+"  t3 - " +count);
//            try {
//                //usypiamy w¹tek na 100 milisekund
//               // Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        //}
	//	}
    }
	
	
}

package demo5;

/**
 * @author user
 *
 */

public class Synchronized1 {

	private int count = 0 ;
	
	/**
	 * All the multi-threading logic
	 */
	private void doWork() {
		Thread t1 = new Thread(this::printThread);
		Thread t2 = new Thread(this::printThread);
		
		t1.start();
		t2.start();
		
		//wait until all threads finish its calculation
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("Count is :" + count);
		
	}
	
	
	/**
	 * This is defined for method reference in Thread object
	 */
	private void printThread() {
		for (int i=0 ;i<10000;i++) {
			counting();
		}
	}
	
	/**
	 * Provide mutual exclusion for count++
	 */
	private synchronized void counting() {
		count++;
	}
	
	
	public void showThread5() {
		
		Synchronized1 app = new Synchronized1();
		app.doWork();
	}
}

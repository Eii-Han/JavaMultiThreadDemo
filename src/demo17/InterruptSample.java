package demo17;

import java.util.Random;

public class InterruptSample {

	public void show() {
		
		System.out.println("Starting...");
		
		Thread t1 = new Thread(this::run);
		
		t1.start();

		try {
			Thread.sleep(500);
			
			//Interrupt the running thread
			t1.interrupt();
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finished");
	}
	
	private void run() {
		Random random = new Random();
		
		for(int i = 0;i < 1E8; i++) {
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				System.out.println("We've been interrupted");
//				break;
//			}
			
			// If Interrupt was detected, exit the loop
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted!");
				break;
			}
			
			//Just a random process
			Math.sin(random.nextDouble());
		}
	}
	
}

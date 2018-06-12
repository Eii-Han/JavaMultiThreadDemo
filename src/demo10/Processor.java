package demo10;

import java.util.Scanner;

/**
 * @author user
 * This is a low-level usage of wait() and notify() sample. This shows how the wait() method hands
 * over the lock and notify() resumes the lock from other threads.
 */
public class Processor {

	private void producer() throws InterruptedException{
		
		synchronized (this) {
			System.out.println("Producer thread running.....");
			this.wait();
			System.out.println("Resume");
		}
	}
	
	private void consumer() throws InterruptedException{
		Scanner scanner = new Scanner(System.in);
		
		//To make sure producer runs first.
		Thread.sleep(2000);
		 
		synchronized (this) {
			System.out.println("Waiting for return key");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			//notify producer so that it can start again
			this.notify();
			//producer will not start again until the synchronized block is finished
			Thread.sleep(5000);
		}
		scanner.close();
		
	}
	
	public void showWaitandNotify() {
		Thread t1 = new Thread(() -> {
			try {
				producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread(() -> {
			try {
				consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
}

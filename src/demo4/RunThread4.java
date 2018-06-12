package demo4;

import java.util.Scanner;

class Processor extends Thread{

	//volatile means this variable will not be cached.
	private volatile boolean running = true;
	
	@Override
	public void run() {

		while(running) {
			for(int i=0;i<10;i++) {
				System.out.println("Hello");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void shutdown() {
		running = false;
	}
}


public class RunThread4 {

	public void showThread4() {
		Processor proc1 = new Processor();
		proc1.start();
		
		System.out.println("Press return to stop....");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();
		
		proc1.shutdown();
	}
	
}

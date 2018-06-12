package demo7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{

	private int id;

	public Processor(int id) {
		super();
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Starting: " + id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed: " + id);
	}
	
}

public class ThreadPool1 {
	
	public void showThread() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		for(int i = 0; i < 5; i++) {
			executor.submit(new Processor(i));
		}
		//It will allow current process to run but not allow new one to be added
		executor.shutdown();
		
		System.out.println("All tasks submitted.");
		
		try {
			//Stop the thread after 1 minute.
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed.");
	}
}

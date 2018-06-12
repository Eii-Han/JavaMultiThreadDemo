package demo15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SemaphoreSample {

	
	public void show() {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i=0;i<200;i++) {
			executor.submit(this::run);
		}
		
		try {
			executor.awaitTermination(2000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();

		
	}
	
	private void run() {
		Connection.getInstance().connect();
	}
}

package demo18;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InterruptThreadPool {

	public void show() {
		
		System.out.println("Starting...");
		
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<?> future = executor.submit(new CallClass());
		
		executor.shutdown();
		try {
			Thread.sleep(500);
			
			// Attempts to cancel execution of this task.
			future.cancel(true);
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Finished");
	}
	
}
	
class CallClass implements Callable<Void>{

	@Override
	public Void call() throws Exception {
		Random random = new Random();

		for(int i = 0;i < 1E8; i++) {
//						try {
//						Thread.sleep(1);
//					} catch (InterruptedException e) {
//						System.out.println("We've been interrupted");
//						break;
//					}
			//Random process
			Math.sin(random.nextInt(100));
			
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted!");
				break;
			}
			Math.sin(random.nextDouble());
		}
		return null;
	}
}
	

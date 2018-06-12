package demo16;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Difference between Callable and Runnable
 * Callable : return values
 * Runnable : not return value
 * 
 * @author user
 *
 */
public class CallabeAndFuture {

	public void show() {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		// RunClass was implemented by Runnable 
		executor.submit(new RunClass());
		
		// CallClass was implemented by Callable.
		// a result was returned after thread has been run
		Future<Integer> future = executor.submit(new CallClass());
		
		// This makes sure the thread is shut down after all thread finished
		executor.shutdown();
		
		try {
			// Wait for all thread finished
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// get the result after thread finished
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			//Get Original Message
			RuntimeException ex = (RuntimeException) e.getCause();
			System.out.println(ex.getMessage());
		}		
	}
	
}

class RunClass implements Runnable{

	@Override
	public void run() {
		Random random = new Random();
		int duration = random.nextInt(4000);
		
		System.out.println("Starting...");
		
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Finished...");
		
	}
	
}

class CallClass implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		Random random = new Random();
		int duration = random.nextInt(4000);
		
		System.out.println("Starting...");
		
		// It is a demo how to get a message from RuntimeException, but real exception
		if(duration > 1000) {
			throw new RuntimeException("Sleeping for too long");
		}
		
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Finished...");
		return duration;
	}
	
}

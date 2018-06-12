package demo9;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class BQ{

	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	private static int putCount = 0, takeCount = 0;

	public static void producer() {
		Random random = new Random();

		while(true) {
			//put the value into the end of array. If it is full, it will wait until there is space.
			try {
				queue.put(random.nextInt(100));
			} catch (InterruptedException e) { //To catch the awaitAntTermination
				//If awaitAndTermination is catch, force the process to end
				return;
			}
			putCount++;

		}
	}

	public static void consumer() {
		Random random = new Random();

		while(true) {
			if(random.nextInt(10)==0) {
				Integer value;
				try {
					Thread.sleep(1000);
					value = queue.take();
					takeCount++;
					
					System.out.println("Take value: " + value + "; Queue size is:" + queue.size() + 
							"; Put Count, take Count :" + putCount + "," + takeCount);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}
}

public class BlockQueueDemo {

	public void showBlockQueue() {
		ExecutorService executor = Executors.newScheduledThreadPool(2);
		
		executor.submit(BQ::producer);
		executor.submit(BQ::consumer);
		
		try {
			executor.awaitTermination(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		executor.shutdownNow();
		System.out.println("Time-Out.");

	}
}

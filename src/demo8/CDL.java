package demo8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Proccessor extends Thread{

	private CountDownLatch startLatch;
	private CountDownLatch endLatch;
	private int threadNum;
	private int startTime;
	
	public Proccessor(CountDownLatch startlatch, CountDownLatch endLatch, int threadNum, int startTime) {
		this.startLatch = startlatch;
		this.endLatch = endLatch;
		this.threadNum = threadNum;
		this.startTime = startTime;
	}

	@Override
	public void run() {

		startLatch.countDown();
		System.out.println("Started." + "Thread id: " + this.getId() + "; Thread Number: " + this.threadNum);

		try {
			//Wait until the latch is counted down to zero.
			startLatch.await();
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			endLatch.countDown();
			int endTime = (int)System.currentTimeMillis();
			System.out.println("End." + " Thread id: " + this.getId() + "; Time : " + (endTime - startTime));
		}

	}

}

public class CDL {

	/**
	 * About the usage of CountDownLatch, please refer to Oracle homepage.
	 */
	public void showCDL() {
		
		final int LIMIT = 8;
		final int STARTLATCHLIMIT = 4;
		final int STARTTIME = (int) System.currentTimeMillis();
		
		CountDownLatch startLatch = new CountDownLatch(STARTLATCHLIMIT);
		CountDownLatch endLatch = new CountDownLatch(LIMIT);
		
		ExecutorService executor = Executors.newFixedThreadPool(LIMIT);


		for(int i=0;i<LIMIT;i++) {
			executor.submit(new Proccessor(startLatch, endLatch, i, STARTTIME));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		try {
			//Wait until the latch is counted down to zero.
			endLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			System.out.println("Completed");
			executor.shutdown();
		}
	}

}

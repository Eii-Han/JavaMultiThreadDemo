package demo12;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	private void increment() {
		for(int i=0;i<10000;i++) {
			count++;
		}
	}
	
	private void firstThread() {
		try {
			lock.lock();
			
			System.out.println("Waiting");
			condition.await();
			System.out.println("Woken up");
			
			
			increment();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	private void secondThread() {
		
		Scanner scanner= new Scanner(System.in);
		try {
			Thread.sleep(1000);
			lock.lock();
			
			System.out.println("Please press return key");
			scanner.nextLine();
			System.out.println("Got return key!");
			
			condition.signal();
			
			increment();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
			scanner.close();
		}
	}
	
	private void finished() {
		System.out.println("Count is: " + count);
	}

	public void showReentrantLock() {
		Thread t1 = new Thread(this::firstThread);
		Thread t2 = new Thread(this::secondThread);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		finished();
	}
}



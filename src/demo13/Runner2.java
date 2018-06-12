package demo13;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner2 {

	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private void firstThread() throws InterruptedException {

		Random random = new Random();

		for(int i=0;i<10000; i++) {
			try {
				//If lock1 is locked, the process will try lock lock2.But it cannot, because lock2
				//is locked by another thread. However, the lock 1 in another thread is waiting for 
				//lock1 to be unlocked in this thread. It makes a deadlock.
				lock1.lock();
				lock2.lock();
				Account.transfer(acc1, acc2, random.nextInt(100));
			}
			finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	private void secondThread() throws InterruptedException {
		
		Random random = new Random();
		
		for(int i=0;i<10000; i++) {
			try {
				lock2.lock();
				lock1.lock();

				Account.transfer(acc2, acc1, random.nextInt(100));
			} finally {
				lock1.unlock();
				//lock2.unlock();
			}

		}
	}
	
	private void finished() {
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
	
	public void runThread() {
		Thread t1 = new Thread(() -> {
			try {
				firstThread();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				secondThread();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		} finally {
			finished();
		}
	}
	
}

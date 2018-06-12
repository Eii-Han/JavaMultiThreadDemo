package demo14;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import demo13.Account;

public class Runner3 {

	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	
	/**
	 * A method can get the lock in any order
	 * @param firstLock
	 * @param secondLock
	 */
	private void acquireLock(Lock firstLock, Lock secondLock) {
		while(true) {
			//Acquire locks
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			
			try {
				//tryLock() can get a lock and return boolean value 
				//whether it gets a lock or not
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			}finally {
				//only return the function only if two locks are got
				if(gotFirstLock && gotSecondLock) {
					return;
				}
				if(gotFirstLock) {
					firstLock.unlock();
				}
				if(gotSecondLock) {
					secondLock.unlock();
				}
			}
			
			//Lock not acquired
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void firstThread() {
		
		Random random = new Random();

		acquireLock(lock1, lock2);

		try {
			for (int i = 0; i < 10000; i++) {
				Account.transfer(acc1, acc2, random.nextInt(100));
			}
		}finally {
			lock1.unlock();
			lock2.unlock();
		}
	}
	
	private void secondThread() {

		Random random = new Random();

		acquireLock(lock1, lock2);

		try {
			for(int i=0 ; i< 10000; i++) {
				Account.transfer(acc2, acc1, random.nextInt(100));
			}
		}finally {
			lock1.unlock();
			lock2.unlock();
		}
	}
	
	private void finished() {
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
	
	public void show() {
		Thread t1 = new Thread(this::firstThread);
		Thread t2 = new Thread(this::secondThread);
		
		t1.start();
		t2.start();
		
		System.out.println(t1.isAlive() + " " + t2.isAlive());
		System.out.println(t1.isDaemon() + " " + t2.isDaemon());
		
		try {
			t1.join();
			t2.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println(t1.isAlive() + " " + t2.isAlive());
			System.out.println(t1.isDaemon() + " " + t2.isDaemon());
			this.finished();
		}
		
	}
	
}

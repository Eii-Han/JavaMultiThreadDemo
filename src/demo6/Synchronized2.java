package demo6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Synchronized2 {

	private Random random = new Random();
	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	/**
	 * private synchronized void stageOne(){.......}
	 * 
	 * just adding synchronized cannot solve the problem,
	 * since it only allows one thread to access this method
	 * but not mulit-threads cannot access it at the same time
	 * 
	 * It can be solved by using Object type to lock the method.
	 * So it can be running at the same time.
	 * 
	 * It's better to separate the lock Object ie. not using list itself to lock
	 * Otherwise, it will a headache how to change the value inside the list.
	 */
	private void stageOne() throws InterruptedException {
		synchronized (lock1) {
			Thread.sleep(1);
			list1.add(random.nextInt(100));
		}
	}
	
	private void stageTwo() throws InterruptedException {
		synchronized (lock2) {
			Thread.sleep(1);
			list2.add(random.nextInt(100));
		}
	}
	
	protected void process() {
		try {
			for(int i=0;i<1000;i++) {
				stageOne();
				stageTwo();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void showMain() throws InterruptedException {
		System.out.println("Starting......");
		
		long start = System.currentTimeMillis();
		
		Thread t1 = new Thread(this::process);
		t1.start();
		
		Thread t2 = new Thread(this::process);
		t2.start();
		
		t1.join();
		t2.join();
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time taken: " + (end-start));
		System.out.println("List1 " + list1.size() + "; List2 " + list2.size());
	}

	
}

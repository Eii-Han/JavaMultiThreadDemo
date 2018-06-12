package demo11;

import java.util.LinkedList;
import java.util.Random;

public class Processor2 {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();

	private void produce() {

		int value = 0;
		while (true) {
			synchronized (lock) {
				while(list.size() == LIMIT) {
					try {
						lock.wait();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				list.add(value++);
			}
		}
	}

	private void consume() {
		Random  random = new Random();
		
		while(true) {
			synchronized (lock) {
				while(list.size()==0) {
					try {	
						lock.wait();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is: " + value);
				lock.notify();
			}
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void showWaitAndNotify() {
		Thread t1 = new Thread(this::produce);
		Thread t2 = new Thread(this::consume);
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
			
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}

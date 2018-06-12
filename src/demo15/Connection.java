package demo15;

import java.util.concurrent.Semaphore;

/**
 * Connection Class which is making use of Singleton Design Pattern
 * 
 * @author user
 *
 */
public class Connection {

	//Fields
	private static Connection instance = new Connection();
	private int connection = 0;
	private static long startTime = System.currentTimeMillis();
	
	private static Semaphore sem = new Semaphore(10);
	
	private Connection() {
		
	}
	
	public static Connection getInstance() {
		return instance;
	}
	
	public void connect() {
		try {
			sem.acquire();
			doConnect();
		} catch (InterruptedException e) {
			System.out.println("Time out.");
			return;
		}finally {
			sem.release();
		}
	}
	
	private void doConnect() throws InterruptedException {
		
		synchronized (this) {
			connection++;
			long endTime = System.currentTimeMillis();
			System.out.println("Current Connections: " + connection + ", Time passed: " + (endTime - startTime));
		}
		
		Thread.sleep(1000);
		
		synchronized (this) {
			connection--;
		}
	}
}

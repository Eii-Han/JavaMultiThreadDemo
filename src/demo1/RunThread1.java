package demo1;

class Runner extends Thread{

	//Right click -> source (Or Alt + Shift + S) ->Override/implement method
	@Override
	public void run() {
		
		for(int i=0; i<10; i++) {
			System.out.println("Test Thread 1:" + i);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

public class RunThread1 {
	public void showThread1() {
		Runner runner1 = new Runner();
		runner1.start();
		
	}
}

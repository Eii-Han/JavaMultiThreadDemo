package demo3;

public class RunThread3 {

	public void showThread3() {
		
		//Lambda Expression
		Thread t1 = new Thread(()->{
			for(int i=0; i<10; i++) {
				System.out.println("Test Thread 3:" + i);

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();

		//Normal implementation(anonymous class)
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=0; i<10; i++) {
					System.out.println("Test Thread 3:" + i);

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		});
		t2.start();
		
		//By Method Reference
		Thread t3 = new Thread(this::printThread);
		t3.start();
	}
	
	private void printThread() {
		for(int i=0; i<10; i++) {
			System.out.println("Test Thread 3:" + i);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

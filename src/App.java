import java.util.Scanner;

import demo1.RunThread1;
import demo10.Processor;
import demo11.Processor2;
import demo12.Runner;
import demo13.Runner2;
import demo14.Runner3;
import demo15.SemaphoreSample;
import demo16.CallabeAndFuture;
import demo17.InterruptSample;
import demo18.InterruptThreadPool;
import demo2.RunThread2;
import demo3.RunThread3;
import demo4.RunThread4;
import demo5.Synchronized1;
import demo6.Synchronized2;
import demo7.ThreadPool1;
import demo8.CDL;
import demo9.BlockQueueDemo;

public class App {

	public static void main(String[] args) throws InterruptedException {
		
		int programId;
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("Choose program to run! Enter Integer");
		
		programId = readAInt(scanner);
		scanner.close();

		switch(programId) {
		case 1:
			//Run thread by extending Thread class
			RunThread1 run1 = new RunThread1();
			run1.showThread1();
			break;
		case 2:
			//Run thread by implementing Runnable interface
			RunThread2 run2 = new RunThread2();
			run2.showThread2();
			break;
		case 3:
			//Run thread by Runnable interface using Lambda expression & Anonymous class
			RunThread3 run3 = new RunThread3();
			run3.showThread3();
			break;
		case 4:
			//Demonstrate the usage of "volatile" modifier
			RunThread4 run4 = new RunThread4();
			run4.showThread4();
			break;
		case 5:
			//Demonstrate how "synchronized" works in a function
			Synchronized1  run5 = new Synchronized1();
			run5.showThread5();
			break;
		case 6:
			//Demonstrate how to lock a process by using synchronized block
			Synchronized2 run6 = new Synchronized2();
			run6.showMain();
			break;
		case 7:
			//Demonstrate how to use thread pool
			ThreadPool1 run7 = new ThreadPool1();
			run7.showThread();
			break;
		case 8:
			//A simple demonstration of CountDownLatch
			CDL run8 = new CDL();
			run8.showCDL();
			break;
		case 9:
			//A simple demonstration of BlockingQueue (which is thread-safe)
			BlockQueueDemo run9 = new BlockQueueDemo();
			run9.showBlockQueue();
			break;
		case 10:
			//wait(), notify() and synchronized block
			Processor run10 = new Processor();
			run10.showWaitandNotify();
			break;
		case 11:
			//wait(), notify() and synchronized block
			Processor2 run11 = new Processor2();
			run11.showWaitAndNotify();
			break;
		case 12:
			//ReentrantLock
			Runner run12 = new Runner();
			run12.showReentrantLock();
			break;
		case 13:
			//Demonstrate a happening Deadlock
			Runner2 run13 = new Runner2();
			run13.runThread();
		case 14:
			//Demonstrate a way to avoid deadlock by implementing a method
			//that can ensure two locks are locked successfully.
			Runner3 run14 = new Runner3();
			run14.show();
			break;
		case 15:
			// Demonstrate how Semaphore control the numbers of running thread
			SemaphoreSample run15 = new SemaphoreSample();
			run15.show();
			break;
		case 16:
			// Demonstrate the difference between Callable and Runnable
			CallabeAndFuture run16 = new CallabeAndFuture();
			run16.show();
			break;
		case 17:
			// 
			InterruptSample run17 = new InterruptSample();
			run17.show();
			break;
		case 18:
			InterruptThreadPool run18 = new InterruptThreadPool();
			run18.show();
			break;
			default:
				System.out.println("No such a program!!");
		}
		

	}
	
	private static int readAInt(Scanner scanner) {
		String line = scanner.nextLine();
		
		if(line.matches("[0-9]+")) {
			scanner.close();
			return Integer.parseInt(line);
		} else {
			System.out.println("Please enter a number as Integer again!!");
			return readAInt(scanner);
		}

		
	}

}

package org.examples.multithreading;

import static org.examples.multithreading.MainClass.Chairs;

import java.util.concurrent.ThreadLocalRandom;

public class SimpleTask implements Runnable {

	@Override
	public void run() {
		String usingChair = "";
		while (1 == 1) { // forever loop
			loop: {

			synchronized (Chairs) { // or you get the lock on
				// Chairs pool

				if (Chairs.size() == 0) {  //check the size of pool if zero then wait on that pool

					try {
						Chairs.wait();
						break loop;
					} catch (InterruptedException e) {
						System.out.println(e);
					}

				} else {
					usingChair = Chairs.remove(Chairs.size() - 1);
				}
			} // synchronized block

			System.out.println("I [" + Thread.currentThread()+ "] am using chair = " + usingChair);

			try {  //  random delay for using chair
				Thread.sleep(ThreadLocalRandom.current().nextInt(100) * 100);
			} catch (InterruptedException e) {	System.out.println(e);			}

			synchronized (Chairs) {
				System.out.println("I [" + Thread.currentThread()+ "] am returned chair = " + usingChair);

				Chairs.add(usingChair);
				usingChair = null;
				Chairs.notifyAll();

			}
		}
		try {  // delay after returning chair kinda thread resting
			Thread.sleep(ThreadLocalRandom.current().nextInt(100) * 100);} catch (InterruptedException e) {	System.out.println(e);		}

		}
		// this is new java 7 feature to generate a random number

	}

}

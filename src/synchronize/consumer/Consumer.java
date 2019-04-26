package synchronize.consumer;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class Consumer {
	private Thread thread;
	private Object waitForJobsMonitor;
	private Deque<String> queue;
	private volatile Boolean isTerminated = false;
	private String name;

	public String getName() {
		return name;
	}

	public Consumer init() {
		waitForJobsMonitor = new Object();
		queue = new ArrayDeque<String>();
		thread = getThread();
		thread.start();
		System.out.println(name+" is starting ...");
		return this;
	}

	private Thread getThread() {
		if (thread == null) {
			thread = new Thread() {
				public void run() {

					Consumer.this.run();
				}
			};
		}
		return thread;
	}

	private void run() {
		while (!isTerminated) {
			// job handling loop
			while (true) {
				String s;
				synchronized (queue) {
					if (queue.isEmpty())
						break;
					s = queue.remove();
				}
				if (s == null)
					break;
				onConsume(s);
			}
			// if we are not terminated and the queue is still empty
			// then wait until new jobs arrive.
			synchronized (waitForJobsMonitor) {
				if (isTerminated)
					break;
				if (queue.isEmpty()) {
					try {
						waitForJobsMonitor.wait();
					} catch (InterruptedException ex) {
					}
				}
			}
		}
	}

	public Consumer setName(String name) {
		this.name = name;
		return this;
	}

	public void add(String s) {
		queue.add(s);
		kickThread();
	}

	public void kickThread() {
		if (!this.thread.isInterrupted()) {
			synchronized (waitForJobsMonitor) {
				waitForJobsMonitor.notify();
			}
		}
	}

	protected abstract void onConsume(String s);

	public void terminateWait() throws InterruptedException {
		terminate();
		while (thread.isAlive()) {
			System.out.println(name+" is stoping ...");
//			Thread.sleep(100);
		}
		
		System.out.println(thread.isAlive());
	}

	protected void terminate() {
		isTerminated = true;
	}
}

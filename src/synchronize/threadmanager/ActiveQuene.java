package synchronize.threadmanager;

import java.util.ArrayDeque;
import java.util.Deque;

class ActiveQueue {
	private Deque<MethodRequest> queue;
	private final static int QUEUE_SIZE = 20;

	public ActiveQueue() {
		queue = new ArrayDeque<MethodRequest>();
	}

	public synchronized void enqueue(MethodRequest mr) {
		while (queue.size() > QUEUE_SIZE) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		queue.push(mr);
		notifyAll();
		System.out.println("Leave Queue");
	}

	public synchronized MethodRequest dequeue() {
		MethodRequest mr;

		while (queue.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		mr = (MethodRequest) queue.pop();
		notifyAll();

		return mr;
	}

}

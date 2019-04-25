package synchronize.threadmanager;

class ActiveObject extends Thread {
	private ActiveQueue queue;

	public ActiveObject() {
		queue = new ActiveQueue();
		start();
	}

	public void enqueue(MethodRequest mr) {
		queue.enqueue(mr);
	}

	public void run() {
		while (true) {
			MethodRequest mr = queue.dequeue();
			mr.call();
		}
	}

}
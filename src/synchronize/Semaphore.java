package synchronize;

public class Semaphore {
	private int count;

	Semaphore(int count) {
		this.count = count;
	}

	public synchronized void acquire() {
		System.out.println("count:" + count);
		while (count == 0) {
			try {
				wait();
				System.out.println("I am waiting ....");
			} catch (InterruptedException e) {

			}
		}

		count--;
	}

	public synchronized void release() {
		count++;
		System.out.println("I am ok");
		notify();
	}

	public static void main(String[] args) {
		Semaphore sp = new Semaphore(4);
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				sp.acquire();
				System.out.println("I am working ...");
				
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				
				sp.release();
			}).start();
		}
	}
}

package synchronize;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest implements Runnable {
	private AtomicInteger foo = new AtomicInteger(0);

	public int getFoo() {
		return foo.get();
	}

	public void setFoo(int f) {
		foo.set(f);
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			int current = foo.get();
			while (!foo.compareAndSet(current, current + 1)) {
				current = foo.get();
			}
		}
//		System.out.println("ok");
		 System.out.println(foo.get());
	}

	public static void main(String[] args) {
		AtomicTest test = new AtomicTest();
		for (int i = 0; i < 100; i++) {
			new Thread(test).start();
		}
	}
}

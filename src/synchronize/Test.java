package synchronize;

public class Test implements Runnable {

	private int foo = 0;

	public synchronized int getFoo() {
		return foo;
	}

	public synchronized void setFoo(int f) {
		foo = f;
	}

	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			//not synchronized in multi thread 
			setFoo(getFoo() + 1);
		}
		
		System.out.println(foo);
	}

	public static void main(String[] args) {
		Test test = new Test();
		for (int i = 0; i < 10; i++) {
			new Thread(test).start();
		}
	}

}

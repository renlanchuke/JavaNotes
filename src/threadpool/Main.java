package threadpool;

public class Main {
	private ThreadPool threadPool = null;
	public Object testlock = new Object();
	public int count = 0;
	int jobsNumber = 200;
	int sizeOfThreadPool = 20;
	int debug = 2;

	public Main(int threadPoolSize, int jobsNum) {
		sizeOfThreadPool = threadPoolSize;
		jobsNumber = jobsNum;
		long timeNoPool = 0;
		long timePool = 0;
		count = 0;
		System.out.println("Begin of testing strategy -- no pool");
		long start = System.currentTimeMillis();
		try {
			for (int i = 0; i < jobsNumber; i++) {
				CalculationTaskTest calculationTask = new CalculationTaskTest();
				new Thread(calculationTask).start();
			}
		} catch (OutOfMemoryError er) {
			System.out.println("No pool :OutOfMemoryError");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		sleepToWait(5);
		if (debug > 3)
			System.out.println("no pool time:" + (System.currentTimeMillis() - start));
		timeNoPool = System.currentTimeMillis() - start;
		System.out.println("End of no pool test");
		count = 0;
		System.out.println("Begin of  creating pool");
		threadPool = new ThreadPool(sizeOfThreadPool);
		System.out.println("End of  creating pool");
		System.out.println("Begin of testing the strategy  -- pool");
		start = System.currentTimeMillis();
		try {
			for (int i = 0; i < jobsNumber; i++) {
				CalculationTaskTest calculationTaskTest = new CalculationTaskTest();
				threadPool.addTask(calculationTaskTest);
			}
		} catch (OutOfMemoryError er) {
			System.out.println("pool :OutOfMemoryError" + " " + sizeOfThreadPool + " " + jobsNumber);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		sleepToWait(5);
		if (debug > 3)
			System.out.println("pool time:" + (System.currentTimeMillis() - start));
		timePool = System.currentTimeMillis() - start;
		System.out.println("End of thread pool test");
		System.out.println("without pool: " + timeNoPool + "    with pool: " + timePool);

		System.exit(0);

	}

	public void sleepToWait(long l) {
		while (true) {
			synchronized (testlock) {
				if (count == jobsNumber)
					return;
			}
			try {
				// you can change it to wait end of all tasks
				Thread.sleep(l);
			} catch (Exception ex) {
			}
		}
	}

	public static void main(String[] args) {
		int poolSize = 20;
		int jobs = 200;
		new Main(poolSize, jobs);
	}

	private class CalculationTaskTest implements Task, Runnable {
		private boolean isEnd = true;

		public CalculationTaskTest() {
			isEnd = true;
		}

		public void setEnd(boolean flag) {
			isEnd = flag;
		}

		public void run() {
			int i = 1;
			int r = 1;
			try {
				for (int ii = 0; ii < 100; ii++)
					r = r + i * i;
			} catch (Exception ex) {
			}
			synchronized (testlock) {
				count++;
			}

		}

		public void startTask() throws Exception {
			run();
		}

		public void endTask() throws Exception {
		}

		public boolean isEnd() {
			return isEnd;
		}
	}
}

package threadpool;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
	/**
	 * the number of threads in pool
	 */
	private int threadNum;
	/**
	 * the default number of threads in pool
	 */
	private int defaultThreadNum;
	/**
	 * the vector of threads in pool
	 */
	private List<Worker> workThreadVector;
	/**
	 * the List of tasks
	 */
	private List<Task> taskList;

	/**
	 * @param i
	 */
	public ThreadPool(int i) {
		taskList = new ArrayList<Task>(10);
		// initial thread number
		defaultThreadNum = 10;
		if (i > 0)
			defaultThreadNum = i;
		// call thread
		CreateThreadPool(i);
	}

	public ThreadPool() {
		this(10);
	}

	/**
	 *
	 * @return
	 */
	public boolean isAllTaskFinish() {
		return taskList.isEmpty();
	}


	public int getThreadNum() {
		return threadNum;
	}

	/**
	 * create thread pool
	 * 
	 * @param i
	 */
	private void CreateThreadPool(int i) {
		if (workThreadVector == null)
			workThreadVector = new ArrayList<Worker>(i);
		Object obj = null;
		// create threads
		synchronized (workThreadVector) {
			for (int j = 0; j < i; j++) {
				threadNum++;
				Worker worker = new Worker(taskList, threadNum);
				workThreadVector.add(worker);
			}

		}
	}

	/**
	 * add task to task vector and notify work Threads in pool to do it
	 * 
	 * @param taskObj
	 */
	public void addTask(Task taskObj) {
		if (taskObj == null)
			return;
		synchronized (taskList) {
			taskList.add(taskObj);
			taskList.notifyAll();
		}
	}

	/**
	 * destroy threads in pool
	 */
	public void closeThread() {
		while (!workThreadVector.isEmpty()) {

			try {
				Worker workThread = (Worker) workThreadVector.remove(0);
				workThread.closeThread();
				continue;
			} catch (Exception exception) {

				exception.printStackTrace();
			}
			break;
		}
	}
}

package threadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableAndFuture2 {
	public static void main(String[] args) {
		ExecutorService threadPool=Executors.newCachedThreadPool();
		CompletionService<Integer> cs=new ExecutorCompletionService<Integer>(threadPool);
		for(int i=1;i<5;i++) {
			final int taskID=i;
			cs.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {	
					return taskID;
				}
			});
		}
		
		for(int i=1;i<5;i++) {
			try {
				System.out.println(cs.take().get());
			}catch(InterruptedException e) {
				e.printStackTrace();
			}catch(ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}

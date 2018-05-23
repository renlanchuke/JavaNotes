package threadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture3 {
	
	public static void main(String[] args) {
		ExecutorService threadPool=Executors.newCachedThreadPool();
		
		List<Future<Integer>> futureList=new ArrayList<Future<Integer>>();
		
		for(int i=1;  i<5;i++) {
			final int taskID=i;
			futureList.add(threadPool.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					
					return taskID;
				}
				
			}));
			
		}
		
		for(Future<Integer> fu:futureList) {
			try {
				System.out.println(fu.get());
			}catch(InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				
				e.printStackTrace();
			}
		}
	}
}

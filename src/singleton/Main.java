package singleton;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Random random=new Random();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
//				try {
//					//if thread not get instance at the same time, 
//					//then will not initialize multi times
//					Thread.sleep(random.nextInt(10)*100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				SingletonTest singletonTest = SingletonTest.getInstance();
				
				SingletonConfig config=SingletonConfig.getInstance();
				
			}).start();
		}
	}

}

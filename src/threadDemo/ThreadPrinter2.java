package threadDemo;

public class ThreadPrinter2 implements Runnable{
	
	private static Integer count=0;
	private int order;
	private String name;

	ThreadPrinter2(String name,int order){
		this.name=name;
		this.order=order;
	}
	
	@Override
	public void run() {
		int number=10;
		
		while(number >0) {
			synchronized(count) {
				if((count-order)%3==0) {
					System.out.println(name);
					number--;
					count++;
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		ThreadPrinter2 tp1=new ThreadPrinter2("A",0);
		ThreadPrinter2 tp2=new ThreadPrinter2("B",1);
		ThreadPrinter2 tp3=new ThreadPrinter2("C",2);
		
		new Thread(tp1).start();
		new Thread(tp2).start();
		new Thread(tp3).start();
		
	}
}

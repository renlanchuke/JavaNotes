package threadDemo;

public class ThreadPrinter implements Runnable{
	private String name;
	private Object prev;
	private Object self;
	
	private ThreadPrinter(String name,Object prev,Object self) {
		this.name=name;
		this.prev=prev;
		this.self=self;
	}
	
	@Override
	public void run() {
		int count=10;
		
		while(count >0) {
			synchronized(prev){
				synchronized(self) {
					System.out.println(this.name);
					count--;
					self.notifyAll();
					
				}
				try {
					prev.wait();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) throws Exception{
		Object a=new Object();
		Object b=new Object();
		Object c=new Object();
		
		ThreadPrinter tp1=new ThreadPrinter("A",c,a);
		
		ThreadPrinter tp2=new ThreadPrinter("B",a,b);	
		ThreadPrinter tp3=new ThreadPrinter("C",b,c);
		
		new Thread(tp1).start();
		Thread.sleep(100);
		new Thread(tp2).start();
		Thread.sleep(100);
		new Thread(tp3).start();
	}
}

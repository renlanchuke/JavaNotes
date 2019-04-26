package synchronize.consumer;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		MessagesProcesser ms=new MessagesProcesser();
		ms.gotMessageEvent("FirstaMessage");
		ms.gotMessageEvent("SecondMesage");
		
		Thread.sleep(5000);
		ms.terminate();
	}

}

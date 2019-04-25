package synchronize.threadmanager;

public class Client {
	public Client(Service s) {
		service = s;
	}

	public void requestService() {
		try {
			service.sayHello();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Service service;
}

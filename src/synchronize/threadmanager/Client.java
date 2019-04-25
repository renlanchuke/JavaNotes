package synchronize.threadmanager;

public class Client {
	public Client(Service s) {
		service = s;
	}

	public void requestService() {
		service.sayHello();
	}

	private Service service;
}

package synchronize.threadmanager;

class SayHello implements MethodRequest {
	private Service service;

	public SayHello(Service s) {
		service = s;
	}

	public void call() {
		try {
			service.sayHello();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
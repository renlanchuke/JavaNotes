package synchronize.threadmanager;

class SayHello implements MethodRequest {
	private Service service;

	public SayHello(Service s) {
		service = s;
	}

	public void call() {
		service.sayHello();
	}

}
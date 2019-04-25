package synchronize.threadmanager;

class ServiceProxy implements Service {
	private Service service;
	private ActiveObject activeObject;

	public ServiceProxy() {
		service = new ServiceImp();
		activeObject = new ActiveObject();
	}

	public void sayHello() {
		MethodRequest mr = new SayHello(service);
		activeObject.enqueue(mr);
		
		
	}

}
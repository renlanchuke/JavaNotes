package synchronize.threadmanager;

class ServiceImp implements Service
{
    public void sayHello() {
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Hello World!");
    }
}

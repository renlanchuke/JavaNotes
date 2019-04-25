package synchronize.threadmanager;

public class Main {
	public static void main(String[] args) {
		Service s = new ServiceImp();
		Client c = new Client(s);
		c.requestService();
		
		
	}
}

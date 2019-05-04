package singleton;

public class SingletonTest {
	private static SingletonTest instance=null;
	
	private SingletonTest() {
		
	}
	
	public static SingletonTest getInstance() {
		if(null==instance) {
			System.out.println("SingletonTest is to be initial ...");
			instance=new SingletonTest();
		}
		
		return instance;
	}
}

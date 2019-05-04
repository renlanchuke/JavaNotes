package singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * using inner class to maintain instance in singleton design patern
 * @author renlan
 *
 */
public class SingletonConfig2 {
   
    private Map<String,String> property=null;
    
    private SingletonConfig2() {
    	System.out.println("SingletonConfig2 is to be initianized ... ");
    	property=new HashMap<String,String>();
    }
    
    private static class FactoryInstance {
    	
    	private static SingletonConfig2 instance=new SingletonConfig2();
    }
    
    public static SingletonConfig2 getInstance() {
    	
    	return FactoryInstance.instance;
    }
    
    public Map<String,String> getProperties(){
    	return property;
    }
    
    //update peoperty
    public void update() {
    	SingletonConfig2 config=new SingletonConfig2();
    	property=config.getProperties();
    }
    
}

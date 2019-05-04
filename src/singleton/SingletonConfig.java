package singleton;

import java.util.HashMap;
import java.util.Map;

public class SingletonConfig {
    private static SingletonConfig instance=null;
    private Map<String,String> property=null;
    
    private SingletonConfig() {
    	//load the system config
    	property=new HashMap<String,String>();
    }
    
    private static synchronized void syncInit() {
    	if(null==instance) {
    		System.out.println("SingletonConfig is to be initianized ...");
    		instance=new SingletonConfig();
    	}
    }
    
    public static SingletonConfig getInstance() {
    	if(null==instance) {
    		syncInit();
    	}
    	return instance;
    }
    
    public Map<String,String> getProperties(){
    	return property;
    }
    
    //update peoperty
    public void update() {
    	SingletonConfig config=new SingletonConfig();
    	property=config.getProperties();
    }
    
}

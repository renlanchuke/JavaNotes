package aspect;

import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {
		AssetService target =new AssetServiceImp();
		AssetServiceProxy proxy=new AssetServiceProxy(target);
		
		AssetService assetService=(AssetService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				AssetServiceImp.class.getInterfaces(),proxy);
		
		Asset as=new Asset.Builder().build();
		
		assetService.addAsset(as);
		assetService.getAsset("2");
	}
	
}

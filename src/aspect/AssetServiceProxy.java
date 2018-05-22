package aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AssetServiceProxy implements InvocationHandler{

	private Object target;	// 被代理的对象
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("do sth before....");
		Object result =  method.invoke(target, args);
		System.out.println("do sth after....");
		return result;
	}
	
	AssetServiceProxy(Object target){
		this.target=target;
	}
	
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}

}

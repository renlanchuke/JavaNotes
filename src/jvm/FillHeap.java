package jvm;

import java.util.ArrayList;
import java.util.List;
/**
 * -Xms500m -Xmx500m -XX:+UseSerialGC
 *  throw OutOfMemoryError
 *  using jconsole to mnoitor memory state 
 */
public class FillHeap {
	static class OOMObject{
		public byte[] placeholder=new byte[640*1024];
	}
	
	public static void fillHeap(int num) throws InterruptedException{
		List<OOMObject> list=new ArrayList<OOMObject>();
		for(int i=0;i<num;i++) {
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		fillHeap(1000);
		System.gc();
	}
}

package synchronize.threadmanager;

import java.util.Stack;

class ActiveQueue{
	 private Stack queue;
	 private final static int QUEUE_SIZE = 20;
     public ActiveQueue() {
     queue = new Stack();
         }
 public synchronized void enqueue(MethodRequest mr) {
     while(queue.size() > QUEUE_SIZE) {
         try {
                wait();
         }catch (InterruptedException e) {
                e.printStackTrace();
         }   
     }
       
     queue.push(mr);
     notifyAll();
     System.out.println("Leave Queue");
 }
 
 public synchronized MethodRequest dequeue() {
     MethodRequest mr;
      
     while(queue.empty()) {
         try {
             wait();
         }catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
     mr = (MethodRequest)queue.pop();
     notifyAll();
      
 return mr;
 }    

}

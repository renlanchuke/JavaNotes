package io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class SocketClient {
	private Socket client;

	public void run(int port) throws InterruptedException {
		try {
			client = new Socket("127.0.0.1", port);
			while (true) {
				OutputStream out = client.getOutputStream();
				out.write((new Date()+" Hello world!").getBytes());
				Thread.sleep(5000);
			}
			
		
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		SocketClient sc=new SocketClient();
		sc.run(10000);
	}
}

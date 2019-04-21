package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * demo socket server support multible client connecting  simultaneously
 * 
 **/
public class SocketServer2 {

	private ServerSocket serverSocket;
	private static int count = 0;

	public void run(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("server is starting ...");
		while (true) {
			try {
				Socket socket = serverSocket.accept();

				new Thread(() -> {
					int count=getCount();
					System.out.println(String.format("%dth client is connecting ...", count));
					try {
						byte[] data = new byte[1024];
						InputStream inputStream;
						inputStream = socket.getInputStream();
						while (true) {
							int len;
							while ((len = inputStream.read(data)) != -1) {
								System.out.println(
										String.format("%dth client said: ", count) + new String(data, 0, len));
								
							}
							
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}).start();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

	}
	
	private int getCount() {
		return ++count;
	}

	public static void main(String[] args) throws Exception {
		SocketServer2 ss = new SocketServer2();
		ss.run(18888);

	}

}

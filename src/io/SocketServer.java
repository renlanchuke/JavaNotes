package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	private ServerSocket serverSocket;

	public void run(int port) throws IOException {
		serverSocket = new ServerSocket(port);

		try {
			System.out.println("server is starting");
			
			Socket socket = serverSocket.accept();
			byte[] data = new byte[1024];
			InputStream inputStream = socket.getInputStream();

			while (true) {
				int len;
				while((len=inputStream.read(data))!=-1) {
					System.out.println(new String(data,0,len));
				}
				
				
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) throws Exception {
		SocketServer ss=new SocketServer();
		ss.run(18888);
		
		
	}
}

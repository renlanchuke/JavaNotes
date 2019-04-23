package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiPortEcho {
	private int ports[];
	private ByteBuffer buffer = ByteBuffer.allocate(1024);

	public MultiPortEcho(int ports[]) {
		this.ports = ports;
	}

	public void run() throws IOException {
		Selector selector = Selector.open();
		ServerSocketChannel serverSocketChannel;
		ServerSocket ss;
		System.out.println(ports[0]);
		for (int i = 0; i < ports.length; i++) {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			InetSocketAddress address = new InetSocketAddress(ports[i]);
			SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

			ss = serverSocketChannel.socket();
			ss.bind(address);
		}

		while (true) {
			int num = selector.select();

			Set selectedKeys = selector.selectedKeys();
			Iterator it = selectedKeys.iterator();

			while (it.hasNext()) {
				SelectionKey key = (SelectionKey) it.next();

				if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
					// Accept the new connection
					ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
					SocketChannel sc = ssc.accept();
					sc.configureBlocking(false);

					// Add the new connection to the selector
					SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
					it.remove();

					System.out.println("Got connection from " + sc);
				} else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
					// Read the data
					SocketChannel sc = (SocketChannel) key.channel();

					// Echo data
					int bytesEchoed = 0;
					while (true) {
						buffer.clear();

						int r = sc.read(buffer);

						if (r <= 0) {
							break;
						}

						buffer.flip();

						sc.write(buffer);
						bytesEchoed += r;
					}

					System.out.println("Echoed " + bytesEchoed + " from " + sc);

					it.remove();
				}

			}
		}
	}

	static public void main(String args[]) throws Exception {
		int ports[] = { 10000, 10001, 10002 };
		MultiPortEcho mpe=new MultiPortEcho(ports);
		mpe.run();
	}

}

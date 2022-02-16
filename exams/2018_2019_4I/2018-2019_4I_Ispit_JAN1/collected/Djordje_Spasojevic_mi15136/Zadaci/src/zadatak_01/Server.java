package zadatak_01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Server {
	private final static int port = 12345;
	private final static String host = "localhost";

	public static void main(String[] args) {
		try(ServerSocketChannel serverChannel = ServerSocketChannel.open(); Selector selector = Selector.open()) {
			serverChannel.bind(new InetSocketAddress(host, port));
			serverChannel.configureBlocking(false);
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);

			while(true) {
				selector.select();

				Set<SelectionKey> readyKeys = selector.selectedKeys();
				System.out.println(readyKeys.size());
				Iterator<SelectionKey> iterator = readyKeys.iterator();

				while (iterator.hasNext()) {
					SelectionKey key = (SelectionKey) iterator.next();
					iterator.remove();

					@SuppressWarnings("unused")
					boolean write = false;

					try {
						if (key.isAcceptable()) {
							ServerSocketChannel server = (ServerSocketChannel)key.channel();
							SocketChannel client = server.accept();

							client.bind(new InetSocketAddress(host, port));
							client.configureBlocking(false);

						} else if(key.isReadable()) {
							SocketChannel client = (SocketChannel)key.channel();
							ByteBuffer buffer  = (ByteBuffer)key.attachment();
							client.bind(new InetSocketAddress(host, port));
							client.configureBlocking(false);

							if (buffer.toString().equalsIgnoreCase("start")) {
								write = true;
							}
						} else if(key.isWritable()) {
							SocketChannel client = (SocketChannel)key.channel();
							ByteBuffer buffer = ByteBuffer.allocate(20);

							if(write = true) {
								for (int i = 0; i < 20; i++) {
									Random rand = new Random();
									int b = rand.nextInt();
									buffer.put((byte)b);
								}

								client.write(buffer);

							}
						}
					} catch (IOException e) {
						key.cancel();
						try {
							key.channel().close();
						} catch (IOException e2) {
						}
					}

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

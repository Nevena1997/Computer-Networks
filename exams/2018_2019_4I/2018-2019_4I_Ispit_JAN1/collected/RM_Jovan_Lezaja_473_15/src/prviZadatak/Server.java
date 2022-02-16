package prviZadatak;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;


public class Server {

	private static String letters = "ABCDEFGHIJKLMNOPQRSTUVXYZ";

	public static void main(String[] args) {

		ByteBuffer message = ByteBuffer.allocate(20);
		char[] lettersArray = letters.toCharArray();

		for (int i=0; i<message.capacity(); ++i) {
			message.put((byte) lettersArray[new Random().nextInt()]);
		}

		try (ServerSocketChannel serverChannel = ServerSocketChannel.open();
			 Selector selector = Selector.open();) {
			ServerSocket ss = serverChannel.socket();
			ss.bind(new InetSocketAddress(12345));
			serverChannel.configureBlocking(false);
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {
				selector.select();

				System.out.println(selector.keys().size());

				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					iterator.remove();

					try {
						if (key.isAcceptable()) {
							ServerSocketChannel channel = (ServerSocketChannel) key.channel();
							SocketChannel client = channel.accept();
							client.configureBlocking(false);
							client.register(selector, SelectionKey.OP_READ);
							ByteBuffer buf = ByteBuffer.allocate(20);
							buf.put(message);
							key.attach(buf);
						} else if (key.isReadable()) {
							SocketChannel client = (SocketChannel) key.channel();
							ByteBuffer buf = ByteBuffer.allocate(128);
							client.read(buf);
							if (buf.hasRemaining()) {
								if (buf.toString().equalsIgnoreCase("start")){
									client.register(selector, SelectionKey.OP_WRITE);
								}
							}
						} else if (key.isWritable()) {
							SocketChannel client = (SocketChannel) key.channel();
							ByteBuffer buf = ByteBuffer.allocate(20);
							buf.put(message);
							client.write(buf);
						}
					} catch (Exception e) {
						key.cancel();
						try {
						key.channel().close();
						} catch (IOException ce) {
							System.out.println(ce);
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

package p01_socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {

	public static final int PORT = 12345;

	public static void main(String[] args) {

		try (ServerSocketChannel serverChannel = ServerSocketChannel.open(); Selector selector = Selector.open(); ) {

			serverChannel.bind(new InetSocketAddress(PORT));
			serverChannel.configureBlocking(false);
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);

			while (true){

				selector.select();

				Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

				while (keys.hasNext()) {

					SelectionKey key = keys.next();
					keys.remove();

					try {
						if (key.isAcceptable()) {
							ServerSocketChannel server = (ServerSocketChannel) key.channel();
							SocketChannel client = server.accept();

							client.configureBlocking(false);
//							SelectionKey key2 = client.register(selector, SelectionKey.OP_READ);

						}

						else if (key.isReadable()) {
							SocketChannel client = (SocketChannel) key.channel();
							ByteBuffer buffer = ByteBuffer.allocate(20);

							client.read(buffer);

						}

						else if (key.isWritable()) {
							SocketChannel client = (SocketChannel) key.channel();
							ByteBuffer buffer = (ByteBuffer) key.attachment();

							while (buffer.hasRemaining()) {
								client.write(buffer);
								buffer.flip();
							}
						}

					} catch (IOException ex) {

						key.cancel();
						try {

							key.channel().close();
						} catch (IOException cex) {
							System.err.println(cex);
						}

					}
				}

			}



		} catch (IOException e) {

			System.err.println(e);
		}

	}

}

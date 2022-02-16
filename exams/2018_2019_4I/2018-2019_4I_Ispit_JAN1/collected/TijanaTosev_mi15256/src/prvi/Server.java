package prvi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import javax.net.ssl.SSLServerSocket;

public class Server {
	public static void main(String[] args) {
		int port = 12345;

		try (ServerSocketChannel serverChannel = ServerSocketChannel.open(); Selector selector = Selector.open()) {

			if (!serverChannel.isOpen() || !serverChannel.isOpen()) {
				System.err.println("Could not open");
				System.exit(-1);
			}

			serverChannel.bind(new InetSocketAddress(port));
			serverChannel.configureBlocking(false);
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);

			while(true) {
				selector.select();

				Set<SelectionKey> keys = selector.keys();
				Iterator<SelectionKey> iterator = keys.iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					iterator.remove();

					if (key.isAcceptable()) {

					} else if (key.isWritable()) {

					}
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}

	}
}

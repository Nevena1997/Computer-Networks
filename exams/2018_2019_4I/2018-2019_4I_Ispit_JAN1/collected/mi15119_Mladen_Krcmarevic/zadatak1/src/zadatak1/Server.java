package zadatak1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		try (ServerSocketChannel socket = ServerSocketChannel.open(); Selector selector = Selector.open()){
			SocketAddress adress = new InetSocketAddress(12345);
			socket.bind(adress);
			socket.configureBlocking(false);

			socket.register(selector, SelectionKey.OP_ACCEPT);

			while (true){
				selector.select();

				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();

				//System.out.println(keys.size());
				while (iterator.hasNext()){
					SelectionKey key = iterator.next();
					iterator.remove();

					if (key.isAcceptable()){
						ServerSocketChannel server2 = (ServerSocketChannel) key.channel();
						SocketChannel client = server2.accept();
						client.configureBlocking(false);

						client.register(selector, SelectionKey.OP_READ);

					}
					if (key.isReadable()){
						SocketChannel server2 = (SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(100);
						server2.read(buffer);

						//Nekako izvucemo string iz buffer-a

					}
					if (key.isWritable()){

						ByteBuffer buffer= (ByteBuffer) key.attachment();

						System.out.println("nesto");

					}

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

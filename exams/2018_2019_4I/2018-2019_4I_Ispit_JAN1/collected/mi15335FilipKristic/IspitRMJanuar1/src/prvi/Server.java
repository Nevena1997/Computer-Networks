package prvi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

	public static void main(String[] args) {
		try(ServerSocketChannel srv = ServerSocketChannel.open();
			Selector selector = Selector.open();){

			srv.bind(new InetSocketAddress(12345));
			srv.configureBlocking(false);
			srv.register(selector, SelectionKey.OP_ACCEPT);

			int connectedClients = 0;

			while(true){
				System.out.println("Connected clients: " + connectedClients);
				selector.select();
				Set<SelectionKey> readyKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = readyKeys.iterator();
				while(iterator.hasNext()){
					SelectionKey key = iterator.next();
					iterator.remove();
					if(key.isAcceptable()){
						ServerSocketChannel channel = (ServerSocketChannel)key.channel();
						SocketChannel connection = channel.accept();
						connectedClients += 1;
						ByteBuffer buffer = ByteBuffer.allocate(20);
						key.attach(buffer);
						connection.configureBlocking(false);
					}
					else if(key.isReadable()){
						SocketChannel client = (SocketChannel)key.channel();
						key.interestOps(SelectionKey.OP_WRITE);
						ByteBuffer buffer = (ByteBuffer)key.attachment();
						client.read(buffer);
						while(buffer.hasRemaining()){
							System.out.print(buffer.asCharBuffer().get());
						}


					}
					else if(key.isWritable()){
						SocketChannel client = (SocketChannel)key.channel();
						key.interestOps(SelectionKey.OP_READ);
						ByteBuffer buffer = (ByteBuffer)key.attachment();
						buffer.putChar('A');
						buffer.flip();
						client.write(buffer);
					}
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}

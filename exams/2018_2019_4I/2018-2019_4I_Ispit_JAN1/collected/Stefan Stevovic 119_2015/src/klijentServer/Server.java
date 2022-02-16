package klijentServer;

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
	public static final int DEFAULT_PORT = 12345;
	public static int NUM_CLIENTS = 0;

	public static void main(String[] args) {
		try(ServerSocketChannel serverChannel = ServerSocketChannel.open(); Selector selector = Selector.open()) {
			System.err.println("Server listening on port " + DEFAULT_PORT);

			if(!serverChannel.isOpen() || !selector.isOpen()){
				System.err.println("Cannot open selector or server channel");
				System.exit(1);
			}

			serverChannel.bind(new InetSocketAddress(DEFAULT_PORT));
			serverChannel.configureBlocking(false);
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);

			while(true){
				System.err.println("Num clients: " + NUM_CLIENTS);
				selector.select();

				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();

				while(iterator.hasNext()){

					SelectionKey key = iterator.next();
					iterator.remove();

					try {
						if(key.isAcceptable()){
							NUM_CLIENTS++;

							ServerSocketChannel server = (ServerSocketChannel)key.channel();
							SocketChannel client = server.accept();

							client.configureBlocking(false);
							SelectionKey key2 = client.register(selector, SelectionKey.OP_READ);

							ByteBuffer byteBuffer = ByteBuffer.allocate(21);
							key2.attach(byteBuffer);
						}
						if(key.isReadable()){
							SocketChannel client = (SocketChannel) key.channel();
							ByteBuffer byteBuffer = (ByteBuffer) key.attachment();

							client.read(byteBuffer);
							String s = new String(byteBuffer.array()).trim();
							System.err.println("String glasi:" + s);


							if(s.equalsIgnoreCase("start")){
								key.interestOps(SelectionKey.OP_WRITE);
								byteBuffer.clear();

								key.attach(byteBuffer.duplicate());
							} else if(s.equalsIgnoreCase("stop")){
								NUM_CLIENTS--;
								key.cancel();
								client.close();

								try {
									key.channel().close();
								} catch (Exception e2) {
									e2.printStackTrace();
								}
							} else {
								String str = "stop or start";
								client.write(ByteBuffer.allocate(21).put(str.getBytes()));
							}
						}
						if(key.isWritable()){
							SocketChannel client = (SocketChannel) key.channel();
							ByteBuffer buff = (ByteBuffer) key.attachment(); // 20 bytes + '\n'

							for(int i = 0; i < 20; i++){
								int pos = (int)(Math.random() * 30);

								char c = (char)(i + pos);
								System.out.println(c);
								buff.put((byte) c);
							}
							buff.put((byte)'\n');

							buff.flip();
							client.write(buff);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

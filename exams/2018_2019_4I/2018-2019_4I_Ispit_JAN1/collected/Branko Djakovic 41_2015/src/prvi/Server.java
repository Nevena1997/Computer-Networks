package prvi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


public class Server {
	public static final int DEFAULT_PORT = 12345;
	public static void main(String[] args) {
		try(ServerSocketChannel serverChannel = ServerSocketChannel.open(); Selector selector = Selector.open()){
			serverChannel.bind(new InetSocketAddress(DEFAULT_PORT));
			serverChannel.configureBlocking(false);
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

			while(true){
				selector.select();
				Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
				while(keys.hasNext()){
					SelectionKey key = keys.next();
					keys.remove();
					try {
						if(key.isAcceptable()){
							ServerSocketChannel server = (ServerSocketChannel)key.channel();
							SocketChannel client = server.accept();
							client.configureBlocking(false);
							SelectionKey key2 = client.register(selector, SelectionKey.OP_READ);
							ByteBuffer buffer1 = ByteBuffer.allocate(4096);
							key2.attach(buffer1);
						} else if(key.isReadable()){
							//System.out.println("WAT");
							SocketChannel client = (SocketChannel)key.channel();
							ByteBuffer buffer = ByteBuffer.allocate(4096);

							ByteBuffer buffer1 = (ByteBuffer)key.attachment();
							client.read(buffer);
							buffer.flip();
							StringBuffer sb = new StringBuffer();
							while(buffer.hasRemaining())
								sb.append((char)buffer.get());
							buffer.rewind();
							if(sb.toString().compareToIgnoreCase("start")==0){
								sb.delete(0, sb.length()-1);
								key.interestOps(SelectionKey.OP_WRITE);
								for(int i = 0; i < 20; i++){
									char randomChar = chars.charAt((int)(Math.random()*26));
									buffer1.put((byte)randomChar);
								}
								buffer1.put((byte)'\r');
								buffer1.put((byte)'\n');
								buffer1.flip();
							}
						} else if(key.isWritable()){
							SocketChannel client = (SocketChannel)key.channel();
							ByteBuffer buffer = (ByteBuffer)key.attachment();
							client.write(buffer);
							client.close();
						}
					} catch (IOException e) {
						key.cancel();
						try {
							key.channel().close();
						} catch (IOException e2) {
							// TODO: handle exception
						}
					}
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}

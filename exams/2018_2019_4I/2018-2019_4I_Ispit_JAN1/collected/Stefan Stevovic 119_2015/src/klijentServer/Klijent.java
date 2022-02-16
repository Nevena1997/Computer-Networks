package klijentServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;


public class Klijent {
	public static void main(String[] args) {
		try(SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", Server.DEFAULT_PORT));) {
			System.err.println("Connected on server");
			client.configureBlocking(false);

			ReadableByteChannel in = Channels.newChannel(System.in);
			WritableByteChannel out = Channels.newChannel(System.out);

			ByteBuffer buffer = ByteBuffer.allocate(21);
			ByteBuffer bufferOut = ByteBuffer.allocate(21);

			//String input = new String(buffer.array()).trim();
			while(true){
				System.err.println("Get line:");
				in.read(buffer);
				client.write(buffer);
				buffer.clear();

				int n = client.read(bufferOut);
				if (n > 0){
					bufferOut.flip();
					out.write(bufferOut);
					bufferOut.clear();
				}
				else
					break;
			}

			System.err.println("Disconnected from server");
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

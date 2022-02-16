package zadatak1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SocketChannel socket;
		try {
			SocketAddress address = new InetSocketAddress("localhost", 12345);
			socket = SocketChannel.open(address);

			//BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
			while (true){
				String line = userIn.readLine();
				System.out.println(line);
				if (line.equalsIgnoreCase("stop"))
					break;

				ByteBuffer buffer = ByteBuffer.allocate(4096);
				for (int i=0; i<line.length(); i++){
					buffer.putChar(line.charAt(i));
				}
				//buffer.rewind();
				buffer.flip();
				socket.write(buffer);
				//out.write(line);
				//out.flush();
				//System.out.println(in.readLine());
			}
			socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

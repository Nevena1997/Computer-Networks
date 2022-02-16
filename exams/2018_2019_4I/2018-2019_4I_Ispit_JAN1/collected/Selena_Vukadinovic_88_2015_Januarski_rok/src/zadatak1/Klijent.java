package zadatak1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class Klijent {

	public static void main(String[] args) {
		int port = 12345;
		String host = "localhost";


			try {
				SocketChannel klijent = SocketChannel.open(new InetSocketAddress(host, port));

				klijent.configureBlocking(false);

				ByteBuffer buf = ByteBuffer.allocate(20);
				WritableByteChannel out = Channels.newChannel(System.out);

				int i = 0;

				while(true){
					i++;

					//namestamo da se ne salje uvek poruka
					if(i%3 == 0){
						i = 0;

						byte[] pocetak = {'s', 't', 'a', 'r', 't'};
						buf.put(pocetak);

						klijent.write(buf);
						buf.clear();
					}
					if(klijent.read(buf) != -1){
						buf.rewind();
						if(buf.equals("stop")){
							break;
						}

						buf.flip();
						out.write(buf);
						buf.clear();

					}else{
						break;
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}


	}

}

package zadatak1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Server {

	public static void main(String[] args) {
		int port = 12345;
		String host = "localhost";


		try(ServerSocketChannel sch = ServerSocketChannel.open();
				Selector sel = Selector.open();){

			if(!sch.isOpen() || !sel.isOpen()){
				System.err.println("Bar jedan se nije otvorio.");
				System.exit(1);
			}

			sch.bind(new InetSocketAddress(host, port));
			sch.configureBlocking(false);
			sch.register(sel, SelectionKey.OP_ACCEPT);


			while(true){

				sel.select();
				Set<SelectionKey> skup = sel.selectedKeys();
				Iterator<SelectionKey> iterator = skup.iterator();

				System.out.println("Korisnika: " + skup.size());

				while(iterator.hasNext()){
					SelectionKey jedinka = iterator.next();
					iterator.remove();

					Scanner sc = new Scanner(System.in);
					StringBuilder tekst = new StringBuilder();
					char karakter;
					double broj;
					int range = 'Z' - 'A';
					int begin = 'A';

					try{

						if(jedinka.isAcceptable()){

							ServerSocketChannel server = (ServerSocketChannel) jedinka.attachment();
							SocketChannel klijent = server.accept();
							klijent.configureBlocking(false);


						}else if(jedinka.isReadable()){

							SocketChannel klijent = (SocketChannel) jedinka.attachment();
							klijent.configureBlocking(false);

							klijent.register(sel, SelectionKey.OP_WRITE);
							SelectionKey kljuc = (SelectionKey) sel.selectedKeys();

							ByteBuffer buf = ByteBuffer.allocate(20);

							for (int i = 0; i < 20; i++) {
								broj = Math.random();
								karakter = (char) (begin + (int) broj*range);
								tekst.append(karakter);
							}

							buf.rewind();
							buf.put((ByteBuffer) tekst.chars());

							buf.flip();
							kljuc.attach(buf);

							tekst.delete(0, 20);


						}else if(jedinka.isWritable()){

							SocketChannel klijent = (SocketChannel) jedinka.attachment();
							ByteBuffer buf = ByteBuffer.allocate(20);

							for (int i = 0; i < 20; i++) {
								broj = Math.random();
								karakter = (char) (begin + (int) broj*range);
								tekst.append(karakter);
							}

							buf.rewind();
							buf.put((ByteBuffer) tekst.chars());

							buf.flip();
							klijent.write(buf);


							tekst.delete(0, 20);
						}

					}catch(IOException e){
						sc.close();
						jedinka.cancel();
						try{
							jedinka.channel().close();
						}catch(IOException ex){
							System.err.println("Nismo uspeli da zatvorimo.");
						}
					}

					sc.close();

				}


			}


		} catch (IOException e) {
			System.err.println("Nismo uspeli da otvorimo.");
		} catch(Exception ex){
			System.err.println("Neocekivana greska.");
		}

	}

}

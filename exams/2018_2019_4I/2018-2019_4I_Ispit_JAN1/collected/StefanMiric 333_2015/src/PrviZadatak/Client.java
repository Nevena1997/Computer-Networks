package PrviZadatak;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args)  {
		int port = 12345;
		String address = "localhost";


		try{
			Socket client = new Socket(address,port);
			Scanner in = new Scanner(System.in);
			BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
			String niska;
			while((niska=in.next()) != "stop"){
				byte[] niska1 = niska.getBytes();
				out.write(niska1);
			}

			in.close();
			client.close();


		}
		catch (UnknownHostException e){
			System.err.println("unknown host " + e);
		}
		catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}

}

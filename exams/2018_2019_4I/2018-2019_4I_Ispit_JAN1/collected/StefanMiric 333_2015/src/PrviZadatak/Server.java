package PrviZadatak;

import java.io.IOException;
import java.net.Socket;

public class Server {


	public void main(String[] args){
		int port = 12345;

		try{
			Socket server = new Socket("localhost",port);

		}
		catch(IOException e){
			System.err.println(e);
		}

	}
}

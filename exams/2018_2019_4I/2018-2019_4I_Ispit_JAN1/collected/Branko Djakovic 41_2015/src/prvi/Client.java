package prvi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		String	host = "localhost";
		int port = 12345;
		Scanner sc = new Scanner(System.in);
		String rec;
		try(Socket socket = new Socket(host, port);) {

			OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			while(true){
				rec = sc.next();
				if(rec.compareToIgnoreCase("stop")==0)
					break;
				out.write(rec);
				out.flush();
				if(rec.compareToIgnoreCase("start")==0){
					int b;
					while((b=in.read()) != -1){
						System.out.write(b);
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		sc.close();
	}

}

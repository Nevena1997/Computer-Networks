package zadatak_01;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private static final int port = 12345;
	private static final String host = "localhost";

	public static void main(String[] args) {

		try(Socket client = new Socket(host, port);
				Scanner sc = new Scanner(System.in);
				InputStreamReader in = new InputStreamReader(client.getInputStream(), "UTF-8");
				OutputStreamWriter out = new OutputStreamWriter(client.getOutputStream(), "UTF-8")) {
			String unos;


			while(true) {
				unos = sc.next();
				if(unos.equalsIgnoreCase("stop"))
					break;

				out.write(unos);
				out.flush();

				int b;
				while((b = in.read()) != -1) {
					System.out.println((char)b);
				}
			}
		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

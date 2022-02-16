package prviZadatak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private static String host = "localhost";
	private static int port = 12345;

	public static void main(String[] args) {

		try (Socket connection = new Socket(host, port)) {
			PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

			String line;
			line = userIn.readLine();
			if (line.equalsIgnoreCase("stop")){
				System.exit(0);
			}

			out.append(line);
			out.flush();

			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

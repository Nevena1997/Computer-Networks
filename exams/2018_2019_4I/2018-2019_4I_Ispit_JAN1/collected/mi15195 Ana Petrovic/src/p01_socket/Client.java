package p01_socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {

		String host = "localhost";
		int port = 12345;

		InputStreamReader in = null;
		PrintWriter out = null;

		Scanner sc = new Scanner(System.in);

		try (Socket socket = new Socket(host, port)) {

			in = new InputStreamReader(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());

			//InputStreamReader user = new InputStreamReader(System.in);

			//String s;
			while (sc.hasNext()) {
				String line = sc.next();
				if (line.trim().equalsIgnoreCase("stop"))
					break;

				out.write(line);
				out.flush();
			}

			int c;
			while ((c = in.read()) != 0)
				System.out.print((char) c);

			System.out.println();

		} catch (IOException ex) {

			System.err.println(ex);

		} finally {
			try {
				if (in != null)
					in.close();
				if(out != null)
					out.close();
				if (sc != null)
					sc.close();
			} catch (IOException e) {

				System.err.println(e);
			}
		}

	}
}

package prvi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		String host = "localhost";
		int port = 12345;
		Socket socket = null;
		BufferedReader in = null;
		BufferedWriter out = null;
		Scanner sc = null;
		try {
			socket = new Socket(host, port);

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			sc = new Scanner(System.in);

			while (true) {
				if (sc.hasNext()) {
					if (!sc.next().equals("stop"))
						out.write(sc.next());
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				if (socket != null)
					socket.close();
				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (sc != null)
					sc.close();
			} catch (IOException ex) {
				 System.err.println(ex);
			}
		}

	}
}

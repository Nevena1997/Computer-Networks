package mi15071_Nikola_Vukovic_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Server {

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(12345)) {
			while (true) {
				System.out.println("Server waiting for connection");
				try (
						Socket s = server.accept();
						BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
						BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					) {
					while (true) {
						System.out.println("Server waiting for message");
						String msg = in.readLine();
						System.out.println("Server received " + msg);
						if (msg == null) break;
						if (msg.trim().compareToIgnoreCase("start") == 0) {
							Random r = new Random();
							String reply = "";
							while (reply.length() < 20) {
								reply += (char)('A' + Math.abs(r.nextInt()) % 26);
							}
							out.write(reply);
						}
						out.write("\n");
						out.flush();
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

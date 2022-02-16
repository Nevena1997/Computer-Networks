package mi15071_Nikola_Vukovic_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try (
				Socket s = new Socket ("localhost", 12345);
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			) {
			while (true) {
				System.out.println("Enter message for server (or 'exit')");
				String msg = sin.readLine();
				if (msg.trim().compareToIgnoreCase("exit") == 0) {
					in.close();
					out.close();
					s.close();
					break;
				}
				out.write(msg);
				out.write("\n");
				out.flush();
				msg = in.readLine();
				if (msg.trim().compareToIgnoreCase("") != 0)
					System.out.println("Received from server: " + msg);
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

package prvi_zadatak;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	final static String host = "localhost";
	final static int port = 12345;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		try(Socket socket = new Socket(host, port);
			OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream()))
		{
			String rec;
			while(true)
			{
				rec = sc.next();
				if(rec.equalsIgnoreCase("stop"))
					break;

				out.write(rec);
				out.flush();
			}
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		sc.close();

	}

}

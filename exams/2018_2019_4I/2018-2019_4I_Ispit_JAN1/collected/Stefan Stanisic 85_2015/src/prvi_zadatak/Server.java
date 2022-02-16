package prvi_zadatak;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

public class Server
{
	public final static String host = "localhost";
	public final static int port = 12345;

	public static void main(String[] args)
	{
		int numberOfClients = 0;
		try(ServerSocketChannel channel = ServerSocketChannel.open())
		{

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}

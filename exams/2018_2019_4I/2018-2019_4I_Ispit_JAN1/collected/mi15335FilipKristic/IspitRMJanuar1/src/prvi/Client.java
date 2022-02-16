package prvi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		Socket client = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try{
			client = new Socket("localhost", 12345);
			in = new BufferedReader(
					new InputStreamReader(System.in));
			out = new PrintWriter(client.getOutputStream());
			while(true){
				String line = in.readLine();
				if(line.trim().equalsIgnoreCase("stop")){
					break;
				}
				out.write(line);
				out.flush();
			}
		}
		catch(ConnectException e){
			System.err.print("Can't connect to localhost server at port 12345.");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(client != null)
					client.close();
				if(in != null)
					in.close();
				if(out != null)
					out.close();
			}catch(IOException ex){ex.printStackTrace();}
		}

	}

}

package prvi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class prvi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String fajl = sc.nextLine();
		sc.close();

		Scanner in = null;
		BufferedWriter out = null;

		try {
			in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(fajl), "UTF-8")));
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("phonenumbers.txt"),"UTF-8"));

			while(in.hasNext()){
				String broj = in.next();
				if (jeste(broj)){
					out.write(broj);
					out.newLine();
				}
			}
		} catch (UnsupportedEncodingException e) {
			System.err.println("Encoding");
		} catch (FileNotFoundException e) {
			System.err.println("File");
		} catch (IOException e) {
			System.err.println("IO");
		} finally {
			try{
				if (in != null)
					in.close();
				if (out != null){
					out.flush();
					out.close();
				}
			} catch (IOException e){
				System.err.println("IO");
			}
		}
	}



public static boolean jeste(String broj){
	if (broj.length() > 13)
		return false;

	if (Character.isDigit(broj.charAt(1)) && Character.isDigit(broj.charAt(2)) &&  Character.isDigit(broj.charAt(3)) && Character.isDigit(broj.charAt(5)) && Character.isDigit(broj.charAt(6)) && Character.isDigit(broj.charAt(7)) && Character.isDigit(broj.charAt(8)) && Character.isDigit(broj.charAt(10)) && Character.isDigit(broj.charAt(11)) && Character.isDigit(broj.charAt(12)))
		return true;
	else
		return false;
}
}
package treci;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException, FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String putanja = sc.nextLine();
		sc.close();

		URL url = new URL(putanja);
		Scanner in =new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(url.getFile()), "UTF-8")));
		System.out.print(in);

		while (in.hasNext()){
			String linija = in.nextLine();
		// Nisam siguran da li ovako moze pa sam ostavio pod komentarom.
		//	String urlputanja=linija.substring(linija.indexOf("/")-1);
		//	URL url2 = new URL(urlputanja);
		//	String fajl= url2.getPath();

			String fajl = linija.substring(linija.lastIndexOf("/"));
			String adresaprot = linija.substring(linija.indexOf(":"), linija.lastIndexOf(":"));
			String[] niz = adresaprot.split(":");
			String adresa = null;
			for (int i = 0 ; i < niz.length -1 ; i++){
				adresa += niz[i];
			}
			String protokol = niz[niz.length - 1];

			int verzija;
			if (adresa.length() > 16){
				verzija = 6;
			}
			else{
				verzija = 4;
			}
			String protokol1 = "http";
			String protokol2 = "sftp";
			boolean prot = false;
			if (protokol.equalsIgnoreCase(protokol1) || protokol.equalsIgnoreCase(protokol2)){
				prot = true;
			}
			if (verzija == 4 || verzija == 6){
				if (prot){
					System.out.printf("v%d:%s:/%s", verzija, protokol,fajl);
				}
			}
		}


	}

}

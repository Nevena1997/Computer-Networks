package zadatak2;

import java.awt.EventQueue;
import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class WebBrowser {

	public static void main(String[] args) {
		String url1 = "C:\\Users\\nalog\\workspace\\Selena_Vukadinovic_88_2015_Januarski_rok\\1.html";
		String url2 = "C:\\Users\\nalog\\workspace\\Selena_Vukadinovic_88_2015_Januarski_rok\\2.html";
		String url3 = "C:\\Users\\nalog\\workspace\\Selena_Vukadinovic_88_2015_Januarski_rok\\3.html";


		JFrame f = new JFrame("fajerfoks");
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setSize(600, 400);
		f.setResizable(false);

		//f.setLayout(new );

		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);
		try {
			jep.setPage(url1);
		} catch (IOException e) {
			jep.setContentType("text/html");
			jep.setText("<html><h1>Stranica "+url1+" nije dostupna.</h1></html>");
		}

		PaserCallbackImp h = new PaserCallbackImp(jep);
		jep.addHyperlinkListener(h);

		JScrollPane scr = new JScrollPane(jep);

		f.setContentPane(scr);

		JButton undoBtn = new JButton("<");
		undoBtn.setBounds(0, 0, 50, 30);
		f.add(undoBtn);

		JButton redoBtn = new JButton(">");
		redoBtn.setBounds(50, 0, 50, 30);
		f.add(redoBtn);

		JButton ci = new JButton("ci");
		ci.setBounds(100, 0, 50, 30);
		f.add(ci);

		EventQueue.invokeLater(new Klasa(f));


	}

}

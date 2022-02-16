package zadatak_02;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.text.html.HTMLEditorKit.Parser;

public class Browser {

	private static final String homepage = "C:/Users/nalog/Desktop/Djordje_Spasojevic_mi15136/Zadaci/1.html";

	public static void main(String[] args) {

		JFrame f = new JFrame();
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setSize(800, 600);


		addElements(f.getContentPane());


		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				f.setVisible(true);
			}
		});
	}

	private static void addElements(Container pane) {
		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);

		URL url = null;
		try {
			url = new URL(homepage);
			jep.setPage(url);
		} catch (IOException e) {
			jep.setContentType("text/html");
			jep.setText("Cannot load " + homepage);
		}

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JScrollPane scrollPane = new JScrollPane(jep);
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.weighty = 1;
		c.weightx = 1;
		c.gridwidth = 600;
		c.fill = GridBagConstraints.HORIZONTAL;

		pane.add(scrollPane, c);

		JTextArea text = new JTextArea(homepage);
		c.gridx = 3;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.weighty = 1;
		c.weightx = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;

		pane.add(text, c);

		LinkFollower lf = new LinkFollower(jep);

		JButton lButton = new JButton("<");
		lButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				lf.undo();
			}
		});
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 1;
		c.gridwidth = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;

		pane.add(lButton, c);

		JButton rButton = new JButton(">");
		rButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				lf.redo();
			}
		});

		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.weighty = 1;
		c.weightx = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;

		pane.add(rButton, c);

		ParserGetter pg = new ParserGetter();
		//Nisam mogao da se setim sta treba da se stavi za output stream
		ParserCallbackImpl pc = null;
		try {
			pc = new ParserCallbackImpl(new OutputStreamWriter(null, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		Parser p = pg.getParser();

		InputStreamReader in = null;
		try {
			in = new InputStreamReader(url.openStream(), "UTF-8");
			p.parse(in, pc, false);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		JButton ciButton = new JButton("ci");
		ciButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0){

			}

		});

		c.gridx = 2;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.weighty = 1;
		c.weightx = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;

		pane.add(ciButton, c);
	}
}

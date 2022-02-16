package swing;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.text.html.HTMLEditorKit;


public class MainBrowser {

	public static void main(String[] args) {
		JFrame frame = new JFrame("fajerfoks");
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		addComponents(frame.getContentPane());

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setVisible(true);
			}
		});

	}

	private static void addComponents(Container contentPane) {
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);

		LinkHandler lh = new LinkHandler(jep);
		jep.addHyperlinkListener(lh);

		JScrollPane scroll = new JScrollPane(jep);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 500;
		c.ipady = 500;
		c.gridwidth = 5;
		contentPane.add(scroll, c);

		JButton undoButton = new JButton("<");
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lh.undo();
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridwidth = 1;
		contentPane.add(undoButton, c);

		URL url = null;
		try {
			 url = new URL("file:///C:/Users/nalog/Desktop/src/1.html");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		try {
			jep.setPage(url);
		} catch (IOException e) {
			e.printStackTrace();
		}


		JButton redoButton = new JButton(">");
		redoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lh.redo();
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		contentPane.add(redoButton, c);

		JButton formattingButton = new JButton("ci");
		formattingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ParserGetter pg = new ParserGetter();
					ParserCallbackImp pc = new ParserCallbackImp();
					HTMLEditorKit.Parser p = pg.getParser();

					StringReader in = new StringReader(jep.getText());
					p.parse(in, pc, true);

					String htmlData = pc.getText();
					jep.setText(htmlData);

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		contentPane.add(formattingButton, c);

	}
}

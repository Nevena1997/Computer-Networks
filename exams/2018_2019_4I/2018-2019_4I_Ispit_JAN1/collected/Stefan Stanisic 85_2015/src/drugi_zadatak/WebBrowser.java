package drugi_zadatak;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class WebBrowser
{

	public static void main(String[] args)
	{
		JFrame f = new JFrame("fajerfox");

		f.setSize(600, 600);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				f.setVisible(true);
			}
		});

		addComponents(f.getContentPane());
	}

	private static void addComponents(Container pane)
	{
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton undo = new JButton("<");
		JEditorPane jep = new JEditorPane();
		JScrollPane scrollPane = new JScrollPane(jep);
		LinkFollower lf = new LinkFollower(jep);

		try
		{
			jep.setPage("file:///C:\\Users\\nalog\\workspace\\ispit\\src\\drugi_zadatak\\1.html");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		jep.addHyperlinkListener(lf);

		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lf.undo();
			}
		});

		JButton redo = new JButton(">");
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lf.redo();
			}
		});

		JButton ci = new JButton("ci");

		pane.add(jep);
		pane.add(scrollPane);
		pane.add(undo);
		pane.add(redo);
		pane.add(ci);
	}



}

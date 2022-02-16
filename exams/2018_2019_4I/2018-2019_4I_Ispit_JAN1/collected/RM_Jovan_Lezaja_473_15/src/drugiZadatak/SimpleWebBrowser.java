package drugiZadatak;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class SimpleWebBrowser {

	private static String HOME_PAGE = "file:///C:/Users/nalog/Desktop/RM_Jovan_Lezaja/src/1.html";

	public static void main(String[] args) {
		JFrame frame = new JFrame("fajerfoks");
		frame.getDefaultCloseOperation();
		frame.setSize(800, 600);
		frame.setResizable(false);

		addComponents(frame.getContentPane());

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	private static void addComponents(Container pane) {
		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JScrollPane scrollPane = new JScrollPane(jep);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		c.ipadx = 750;
		c.ipady = 500;
		c.weightx = 0.0;
		c.weighty = 1.0;
		pane.add(scrollPane, c);

		LinkHandler handler = new LinkHandler(jep, scrollPane);
		jep.addHyperlinkListener(handler);

		handler.goToPage(HOME_PAGE);

		JButton undoButton = new JButton("<");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 0.0;
		c.weighty = 1.0;
		pane.add(undoButton, c);

		undoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				handler.undo();
			}
		});

		JButton redoButton = new JButton(">");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 0.0;
		c.weighty = 1.0;
		pane.add(redoButton, c);

		redoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				handler.redo();
			}
		});

		JButton ciButton = new JButton("ci");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 0.0;
		c.weighty = 1.0;
		pane.add(ciButton, c);

		ciButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
	}
}

package p02_swing;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class WebBrowser {

	public static final String URL = "file:///C:/Users/nalog/workspace/mi15195/1.html";

	public static void main(String[] args) {

		JFrame f = new JFrame("fajerfoks");
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setSize(600,400);
	//	f.setResizable(false);

		addComponents(f.getContentPane());

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				f.setVisible(true);

			}
		});

	}

	public static void addComponents(Container pane) {

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);

		JScrollPane scroll = new JScrollPane(jep);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = 0;
		c.ipady = 400;
		c.gridheight = 0;
		c.gridwidth = 0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(scroll, c);

		JButton btnUndo = new JButton("<");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		pane.add(btnUndo, c);

		JButton btnRedo = new JButton(">");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		pane.add(btnRedo, c);

		JButton btnCi = new JButton("ci");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		pane.add(btnCi, c);

		JLabel lbl = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		pane.add(lbl, c);

		LinkHandler lh = new LinkHandler(jep);
		jep.addHyperlinkListener(lh);

		lh.goToPage(URL);

		btnUndo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lh.undo();

			}
		});

		btnRedo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lh.redo();

			}
		});

		btnCi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lh.remove();

			}
		});


	}

}

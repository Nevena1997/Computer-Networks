package drugi;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.net.URL;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class application {

	public static void main(String[] args) {
		JFrame f = new JFrame("fajerfoks");
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setSize(600,400);
		addComponents(f);

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				f.setVisible(true);

			}
		});
	}

	public static void addComponents(Container pane){
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(jep);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 0;
		c.ipady = 300;
		c.gridwidth = 4;
		c.weightx = 1;
		c.weighty = 0;
		pane.add(scrollPane,c);

		LinkFollower lf = new LinkFollower(jep);
		jep.addHyperlinkListener(lf);
		lf.goToPage("1.html");

		JButton undoButton = new JButton("<");
		undoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lf.undo();
			}
		});

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 1;
		pane.add(undoButton, c);

		JButton redoButton = new JButton(">");
		redoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lf.redo();
			}
		});

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 1;
		pane.add(redoButton, c);

		JButton ci = new JButton("ci");
		ci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lf.clear();
			}
		});

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		pane.add(ci, c);
	}

}

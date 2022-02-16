package DrugiZadatak;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class webBrowser {

	public static void main(String[] args) {
		JFrame f = new JFrame("fajerfoks");
		f.setResizable(false);
		f.setSize(600, 400);
		addContent(f.getContentPane());

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				f.setVisible(true);
			}
		});
	}


	public static void addContent(Container pane){
		GridBagLayout g = new GridBagLayout();
		GridBagConstraints c = g.getConstraints(pane);

		JPanel j = new JPanel();
		j.setEnabled(true);

		JScrollPane scrollPane = new JScrollPane(j);
		c.gridheight=1;
		c.gridwidth=0;
		c.gridx=0;
		c.gridy=0;
		c.ipadx=0;
		c.ipady=0;

		pane.add(scrollPane,c);
		c.gridheight=1;
		c.gridwidth=0;
		c.gridx=1;
		c.gridy=0;
		c.ipadx=0;
		c.ipady=0;


		JLabel lbl = new JLabel();
		pane.add(lbl,c);


		linkHandler lh = new linkHandler(lbl);


		JButton btnUndo = new JButton("<");
		btnUndo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lh.undo();
			}
		});


		JButton btnRedo = new JButton(">");
		JButton btnImg = new JButton("ci");
	}

}

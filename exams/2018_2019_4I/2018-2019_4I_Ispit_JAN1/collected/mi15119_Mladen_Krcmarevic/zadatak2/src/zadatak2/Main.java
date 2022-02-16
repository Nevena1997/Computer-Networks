package zadatak2;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.text.html.HTMLEditorKit;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;


public class Main {

	public static void main(String[] args) {
		//HTMLEditorKit.par
		JFrame f = new JFrame("Mini browser");
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setSize(600, 400);
		// TODO Auto-generated method stub
		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);

		JScrollPane scroll = new JScrollPane(jep);

		HyperlinkHandler hl = new HyperlinkHandler(jep);

		JButton back = new JButton("<");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				hl.back();
			}
		});
		JButton fwd = new JButton(">");
		fwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				hl.forward();
			}
		});
		JButton ci = new JButton("ci");
		ci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				hl.clearImages();
			}
		});



		f.setContentPane(scroll);


		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				f.setVisible(true);

			}
		});
	}

}

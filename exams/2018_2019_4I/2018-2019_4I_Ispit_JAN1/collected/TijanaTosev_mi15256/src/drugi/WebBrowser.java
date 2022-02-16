package drugi;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.text.html.HTMLEditorKit;

public class WebBrowser {
	public static void main(String[] args){

		JFrame f = new JFrame("simple web browser");
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setSize(600, 400);
		f.setResizable(false);

		addComponents(f);
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				f.setVisible(true);
			}
		});
	}

	private static void addComponents(JFrame f) {
		GridBagConstraints c = new GridBagConstraints();
		f.setLayout(new GridBagLayout());
		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);

		try {
			jep.setPage("http://C:\\Users\\nalog\\Desktop\\TijanaTosev_mi15256\\1.html/");
		} catch (IOException e) {

		}

		JScrollPane jsp = new JScrollPane(jep);
		jsp.createVerticalScrollBar();
		jsp.createHorizontalScrollBar();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 0;
		c.gridheight = 480;
		c.weightx = 800;
		c.weighty = 600;
		f.add(jsp, c);


//		LinkHandler lh = new LinkHandler(jep);
		JButton btnUndo = new JButton("<");
////		btnUndo.addActionListener(new ActionListener() {
////
////			@Override
////			public void actionPerformed(ActionEvent arg0) {
////				lh.undo();
////			}
////		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		f.add(btnUndo, c);
//
//
		JButton btnRedo = new JButton(">");
////		btnRedo.addActionListener(new ActionListener() {
////
////			@Override
////			public void actionPerformed(ActionEvent arg0) {
////				lh.redo();
////			}
////		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 0;
		c.weightx = 1;
		f.add(btnRedo, c);
//
		JButton btnCI = new JButton("CI");
		btnCI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ParserGetter pg = new ParserGetter();
					ParserCallbackImpl pc = new ParserCallbackImpl(new StringBuffer(jep.getText().toString()));
					HTMLEditorKit.Parser p = pg.getParser();
//					StringBuffer str = new StringBuffer();
//					InputStreamReader in = new InputStreamReader()
//					p.parse(arg0, arg1, arg2);
					String s = jep.getText().toString();
					s.replaceAll("<img .* />", " ");
					jep.setText(s);
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 0;
		c.weightx = 1;
		f.add(btnCI, c);
	}

}

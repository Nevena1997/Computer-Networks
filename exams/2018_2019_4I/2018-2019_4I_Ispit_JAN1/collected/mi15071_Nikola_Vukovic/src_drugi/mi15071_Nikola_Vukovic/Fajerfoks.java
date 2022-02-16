package mi15071_Nikola_Vukovic;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Fajerfoks {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);

		GridBagLayout layout = new GridBagLayout();
		frame.setLayout(layout);

//		NoImageEditor test = new NoImageEditor();
		JEditorPane editor = new JEditorPane();
//		test.install(editor);
		LinkListener linkListener = new LinkListener(editor);
		editor.addHyperlinkListener(linkListener);
		editor.setEditable(false);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		JButton undo = new JButton("<");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				linkListener.undo();
			}
		});
		frame.getContentPane().add(undo, c);
		c.gridx = 1;

		JButton redo = new JButton(">");
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				linkListener.redo();
			}
		});
		frame.getContentPane().add(redo, c);
		c.gridx = 2;
		JButton clear = new JButton("cl");
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String content = editor.getText();
				editor.setText(content.replaceAll("<img [^>]*>", ""));
			}
		});
		frame.getContentPane().add(clear, c);

		JScrollPane EditorWrapper = new JScrollPane(editor);
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		frame.getContentPane().add(EditorWrapper, c);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setVisible(true);
			}
		});
	}

}

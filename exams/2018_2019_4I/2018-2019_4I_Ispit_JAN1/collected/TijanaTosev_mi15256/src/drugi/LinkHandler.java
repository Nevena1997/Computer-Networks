package drugi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;

public class LinkHandler {
	private JEditorPane pane;
	private List<URL> urlStack;
	private int index;

	public LinkHandler(JEditorPane pane) {
		this.pane = pane;
		this.urlStack = new ArrayList<URL>();
		this.index = -1;
	}

	public void undo() {
		if (this.index < this.urlStack.size() - 1) {
			this.index--;
			URL u = this.urlStack.get(this.index);
			try {
				this.pane.setPage(u);
			} catch(IOException e) {
				this.pane.setText("<html>Could not load " + u.toString() + "</html>");
			}
		}
	}

	public void redo() {

	}
}

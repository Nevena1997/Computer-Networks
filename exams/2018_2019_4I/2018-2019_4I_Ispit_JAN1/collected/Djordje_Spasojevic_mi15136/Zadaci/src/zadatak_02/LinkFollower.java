package zadatak_02;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class LinkFollower implements HyperlinkListener {
	private int level;
	private ArrayList<URL> urlList;
	private JEditorPane pane;

	public LinkFollower(JEditorPane pane) {
		this.level = -1;
		this.urlList = new ArrayList<URL>();
		this.pane = pane;
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void undo() {
		if (level > -1) {
			level--;
			URL url = urlList.get(level);
			try {
				pane.setPage(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void redo() {
		if(level > urlList.size()) {
			level++;
			URL url = urlList.get(level);
			try {
				pane.setPage(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

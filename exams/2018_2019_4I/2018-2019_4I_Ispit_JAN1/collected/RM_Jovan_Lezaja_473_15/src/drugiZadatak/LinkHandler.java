package drugiZadatak;

import java.awt.Container;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class LinkHandler implements HyperlinkListener {

	private JEditorPane jep;
	private Container pane;
	private List<URL> urlStack;
	private int index;

	public LinkHandler(JEditorPane jep, Container pane) {
		this.jep = jep;
		this.pane = pane;
		this.urlStack = new ArrayList<URL>();
		this.index = -1;
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		HyperlinkEvent.EventType type = e.getEventType();

		if (type == HyperlinkEvent.EventType.ACTIVATED) {
			URL url = e.getURL();
			goToPage(url);
		}
	}

	public void undo() {
		if (index > 0) {
			index--;

			URL url = urlStack.get(index);

			try {
				jep.setPage(url);
			} catch (IOException e1) {
				jep.setText("<html> Could not load " + url + "</html>");
			}
		}
	}

	public void redo() {
		if (index < urlStack.size()-1) {
			index++;

			URL url = urlStack.get(index);

			try {
				jep.setPage(url);
			} catch (IOException e1) {
				jep.setText("<html> Could not load " + url + "</html>");
			}
		}
	}

	private void goToPage(URL url) {
		index++;

		for (int i = index; i < urlStack.size(); i++) {
			urlStack.remove(i);
		}

		try {
			jep.setPage(url);
		} catch (IOException e1) {
			jep.setText("<html> Could not load " + url + "</html>");
		}
	}

	public void goToPage(String urlString) {
		try {
			URL url = new URL(urlString);
			goToPage(url);
		} catch (MalformedURLException e) {
			System.err.println(e);
			jep.setText("<html> Could not load page </html>");
		}
	}
}

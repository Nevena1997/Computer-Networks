package mi15071_Nikola_Vukovic;

import java.io.IOException;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class LinkListener implements HyperlinkListener {
	static final String HOMEPAGE = "file:///C:/Users/nalog/Desktop/mi15071_Nikola_Vukovic/testfiles/1.html";

	JEditorPane browser;
	Vector<String> stack;
	int currentPage = -1;

	public LinkListener(JEditorPane browser) {
		super();
		this.browser = browser;
		this.stack = new Vector<String>();
		this.goTo(HOMEPAGE);
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
			this.goTo(e.getURL().toString());
	}

	public void goTo(String url) {
		try {
			this.browser.setPage(url);
			if (this.currentPage + 1 < this.stack.size())
				for (int i = this.currentPage + 1; i < stack.size();) {
					stack.removeElementAt(i);
				}
			this.stack.add(url);
			this.currentPage += 1;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void undo() {
		if (this.stack.size() > 1 && this.currentPage > 0)
			try {
				this.browser.setPage(this.stack.elementAt(currentPage - 1));
				this.currentPage -= 1;
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void redo() {
		if (this.stack.size() > this.currentPage + 1)
			try {
				this.browser.setPage(this.stack.elementAt(currentPage + 1));
				this.currentPage += 1;
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@SuppressWarnings("unused")
	private void printStack() {
		for (int i = 0; i < stack.size(); i++) {
			System.out.println("" + i + " " + stack.elementAt(i));
		}
	}


}

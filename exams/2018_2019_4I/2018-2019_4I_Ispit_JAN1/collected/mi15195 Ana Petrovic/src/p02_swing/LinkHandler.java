package p02_swing;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class LinkHandler implements HyperlinkListener {

	private JEditorPane jep;
	private List<URL> urls;
	private int index;

	public LinkHandler(JEditorPane jep) {
		this.jep = jep;

		this.urls = new ArrayList<URL>();
		this.index = -1;
	}

	public void hyperlinkUpdate(HyperlinkEvent e) {

		HyperlinkEvent.EventType type = e.getEventType();

		if (type == HyperlinkEvent.EventType.ACTIVATED)
			goToPage(e.getURL());

	}

	public void undo() {
		if (this.index > 0) {
			this.index--;

			goToPage(this.urls.get(this.index));
		}
		else {
			System.out.println("No previous page");
		}

	}

	public void redo() {
		if(this.index < this.urls.size()) {
			this.index++;

			goToPage(this.urls.get(this.index));
		}
		else {
			System.out.println("nothing to redo");
			return;
		}
	}

	public void remove() {
		ParserGetter pg;
		ParserCallback1 pc = new ParserCallback1(this.jep);
		//HTMLEditorKit.Parser p = pg.getParser();


	}

	public void goToPage(URL url) {

		this.urls.add(this.index+1, url);
		this.index++;

	//	for(int i = this.index + 1; i < urls.size(); i ++ )
	//		urls.remove(i);

		try {
			this.jep.setPage(url);

		} catch (IOException e) {
			System.err.println(e);
		}

	}

	public void goToPage(String urlS) {

		try {
			URL url = new URL(urlS);

			goToPage(url);

		} catch (IOException e) {
			System.err.println(e);
		}

	}

}
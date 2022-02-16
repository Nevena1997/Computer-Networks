package swing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class LinkHandler implements HyperlinkListener{
	private JEditorPane jep;
	private List<URL> listURL;
	private int index;

	public LinkHandler(JEditorPane jep) {
		this.jep = jep;
		this.listURL = new ArrayList<URL>();
		this.index = -1;
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		//URL url = this.listURL.get(this.index);
		if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
			this.goToPage(e.getURL());
		}
	}

	public void goToPage(URL url) {
		this.index = this.index+1;
		this.listURL.add(url);

		for(int i = this.index + 1; i < this.listURL.size(); i++){
			this.listURL.remove(i);
		}

		try {
			this.jep.setPage(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void goToPage(String urlAdress){
		URL url = null;
		try {
			url = new URL(urlAdress);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		this.goToPage(url);
	}

	public void undo(){
		if(this.index > 0){
			this.index--;

			URL url = this.listURL.get(this.index);

			try {
				this.jep.setPage(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void redo(){
		if(this.index < this.listURL.size() - 1){
			this.index++;

			URL url = this.listURL.get(this.index);

			try {
				this.jep.setPage(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

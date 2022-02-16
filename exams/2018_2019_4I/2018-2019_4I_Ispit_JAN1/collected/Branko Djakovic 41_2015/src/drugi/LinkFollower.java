package drugi;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class LinkFollower implements HyperlinkListener{
	JEditorPane pane;
	int index = -1;
	ArrayList<URL> urls;



	public LinkFollower(JEditorPane pane) {
		this.pane = pane;
		urls = new ArrayList<URL>();
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		HyperlinkEvent.EventType type = e.getEventType();
		if(type == HyperlinkEvent.EventType.ACTIVATED && e.getURL() != this.urls.get(this.index)){
			goToPage(e.getURL());
			if(this.urls.size() > this.index+1){
				for(int i = 0; i < this.urls.size()-index-1; i++){
					this.urls.remove(this.urls.size()-1);
				}
			}
		}
	}

	public void undo(){
		if(this.index > 0){
			this.index--;
			try {
				this.pane.setPage(this.urls.get(this.index));
			} catch (IOException e) {
				this.pane.setText("<html>Could not open url " + this.urls.get(this.index).toString()+"</html>");
			}
		}
	}

	public void redo(){
		if(this.index < this.urls.size()-1){
			this.index++;
			try {
				this.pane.setPage(this.urls.get(this.index));
			} catch (IOException e) {
				this.pane.setText("<html>Could not open url " + this.urls.get(this.index).toString()+"</html>");
			}
		}
	}

	public void clear(){
		ParserGetter pg = new ParserGetter();
		StringBuffer sb = new StringBuffer();
		ParserCallbackImpl pc = new ParserCallbackImpl(sb);
	}

	private void goToPage(URL url){
		try {
			String u = url.toString();
			u = u.substring(u.lastIndexOf('/')+1);
			url = new URL("file", "localhost", u);
			this.urls.add(++index, url);
			this.pane.setPage(url);

		} catch (IOException e) {
			this.pane.setText("<html>Could not open url " + url.toString()+"</html>");
		}
	}

	public void goToPage(String url){
		try {
			URL u = new URL("file", "localhost", url);
			this.urls.add(++index, u);
			this.pane.setPage(u);

		} catch (IOException e) {
			this.pane.setText("<html>Could not open url " + url.toString()+"</html>");
		}
	}
}

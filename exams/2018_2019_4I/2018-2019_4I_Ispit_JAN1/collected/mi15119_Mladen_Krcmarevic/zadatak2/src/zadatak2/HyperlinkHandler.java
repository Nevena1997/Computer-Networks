package zadatak2;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent.EventType;

public class HyperlinkHandler implements HyperlinkListener{
	List<URL> history;
	JEditorPane jep;
	int index = -1;

	public HyperlinkHandler(JEditorPane jep) {
		super();
		this.history = new ArrayList<URL>();
		this.jep = jep;
		jep.addHyperlinkListener(this);
		try {
			goToPage(new URL("file:///C:/Users/nalog/Desktop/zadatak2/1.html"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		// TODO Auto-generated method stub
		if (e.getEventType() == EventType.ACTIVATED)
			goToPage(e.getURL());
	}

	private void goToPage(URL url){
		try {
			index++;
			jep.setPage(url);
			history.add(index, url);

			while (index+1 < history.size()-1){
				history.remove(index+1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void back(){
		if (index-1 < 0){
			return;
		}
		try {
			index--;
			jep.setPage(history.get(index));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void forward(){
		if (index+1 >= history.size()){
			return;
		}
		try {
			index++;
			jep.setPage(history.get(index));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clearImages(){

	}



}

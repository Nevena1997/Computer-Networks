package zadatak2;

import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;

public class PaserCallbackImp extends ParserCallback implements HyperlinkListener {

	private JEditorPane jep;
	private URL link;
	//on click on ci becomes true
	private boolean ci;

	public PaserCallbackImp(JEditorPane jep) {
		this.jep = jep;
		link = jep.getPage();
		this.ci = false;
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
			URL strana = e.getURL();

			ci = false;
			try {
				jep.setPage(strana);
			} catch (IOException e1) {
				jep.setContentType("text/html");
				jep.setText("<html><h1>Stranica "+strana+" nije dostupna.</h1></html>");
			}
		}

	}

	//function called on click on ci button
	public void ciClick(){
		ci = true;
		try {
			jep.setPage(link);
		} catch (IOException e1) {
			jep.setContentType("text/html");
			jep.setText("<html><h1>Stranica "+ link +" nije dostupna.</h1></html>");
		}
	}


	@Override
	public void handleStartTag(Tag t, MutableAttributeSet a, int pos) {
		if((!t.equals(Tag.A) && ci) || !ci){
			super.handleStartTag(t, a, pos);
		}

	}



}

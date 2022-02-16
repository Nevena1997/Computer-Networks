package p02_swing;

import javax.swing.JEditorPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class ParserCallback1 extends HTMLEditorKit.ParserCallback {

	private JEditorPane jep;
	private StringBuffer sb;

	public ParserCallback1(JEditorPane jep) {
		this.jep = jep;
		jep = new JEditorPane("text/html", sb.toString());
		jep.setVisible(true);
	}

	public void handleText(char[] text, int pos)
	{
		this.sb.append(text);

	}

    public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
    	if (t == HTML.Tag.IMG)
    		sb.append("\r\n");
    }

    public void handleEndTag(HTML.Tag t, int pos) {
    	if(t == HTML.Tag.IMG)
    		sb.append("\r\n");
    }

    public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {
    	if(t == HTML.Tag.IMG)
    		sb.append("\r\n");
    }

}

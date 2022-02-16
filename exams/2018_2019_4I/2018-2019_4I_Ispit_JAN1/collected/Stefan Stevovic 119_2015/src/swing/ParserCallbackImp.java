package swing;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTML.Tag;

public class ParserCallbackImp extends HTMLEditorKit.ParserCallback {
	private StringBuffer text;

	public ParserCallbackImp() {
		this.text = new StringBuffer();
	}

	public String getText() {
		return this.text.toString();
	}

	@Override
	public void handleText(char[] data, int pos) {
		this.text.append(data);
	}

	@Override
	public void handleStartTag(Tag t, MutableAttributeSet a, int pos) {
		this.text.append("<").append(t.toString()).append(">");
	}

	@Override
	public void handleEndTag(Tag t, int pos) {
		this.text.append("</").append(t.toString()).append(">");
	}

	@Override
	public void handleSimpleTag(Tag t, MutableAttributeSet a, int pos) {
		if(t != HTML.Tag.IMG){
			this.text.append("<").append(t.toString()).append(" />");
		}
	}


}

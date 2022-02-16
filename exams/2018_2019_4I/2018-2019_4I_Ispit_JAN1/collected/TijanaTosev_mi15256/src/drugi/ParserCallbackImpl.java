package drugi;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTML.Tag;

public class ParserCallbackImpl extends HTMLEditorKit.ParserCallback {
	private StringBuffer str;

	public ParserCallbackImpl(StringBuffer str) {
		this.str = str;
	}

	@Override
	public void handleStartTag(Tag t, MutableAttributeSet a, int pos) {
		if (t == Tag.IMG) {
			this.str.append("\r\n");
		}
	}

	@Override
	public void handleText(char[] text, int pos) {
		this.str.append(text);
		this.str.append("\r\n");
	}

	public String getHTML() {
		return this.str.toString();
	}
}

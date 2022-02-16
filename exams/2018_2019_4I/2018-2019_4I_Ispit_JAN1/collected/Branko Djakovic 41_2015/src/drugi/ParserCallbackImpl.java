package drugi;

import java.io.Writer;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Tag;

import javafx.scene.web.HTMLEditor;

public class ParserCallbackImpl extends HTMLEditorKit.ParserCallback{
	StringBuffer out;
	boolean inImg = false;
	public ParserCallbackImpl(StringBuffer out) {
		this.out = out;
	}

	@Override
	public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
	}
}

package zadatak_02;

import java.io.IOException;
import java.io.Writer;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;

public class ParserCallbackImpl extends ParserCallback{
	private Writer out;
	public ParserCallbackImpl(Writer writer) {
		this.out = writer;
	}

	@Override
	public void handleStartTag(Tag tag, MutableAttributeSet atributte, int position) {
		if (tag != Tag.IMG) {
			try {
				this.out.write(tag.toString());
				this.out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}

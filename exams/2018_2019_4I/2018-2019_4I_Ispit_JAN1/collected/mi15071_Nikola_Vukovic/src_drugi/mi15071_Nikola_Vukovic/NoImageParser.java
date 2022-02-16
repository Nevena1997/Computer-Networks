package mi15071_Nikola_Vukovic;

import java.io.IOException;
import java.io.Reader;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.DTD;
import javax.swing.text.html.parser.DocumentParser;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.text.html.parser.TagElement;

public class NoImageParser extends javax.swing.text.html.parser.ParserDelegator {

	@Override
	public void parse(Reader r, ParserCallback cb, boolean ignoreCharSet) throws IOException {
		System.out.println("test");
		new NoImageDocumentParser(null).parse(r, cb, ignoreCharSet);
	}
}

class NoImageDocumentParser extends javax.swing.text.html.parser.DocumentParser {
	public NoImageDocumentParser(DTD dtd) {
		super(dtd);
	}

	@Override
	protected void handleStartTag(TagElement tag) {
		System.out.println(tag.getHTMLTag().toString());
		super.handleStartTag(tag);
	}


}

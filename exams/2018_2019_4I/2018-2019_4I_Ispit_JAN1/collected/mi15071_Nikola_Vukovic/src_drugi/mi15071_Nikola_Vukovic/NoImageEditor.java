package mi15071_Nikola_Vukovic;

import javax.swing.text.html.HTMLEditorKit;

public class NoImageEditor extends HTMLEditorKit {

	@Override
	protected Parser getParser() {
		return (Parser) new NoImageParser();
	}

}

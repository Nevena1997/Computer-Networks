package swing;

import javax.swing.text.html.HTMLEditorKit;

public class ParserGetter extends HTMLEditorKit{
	// ukljucio sam da bih uklonio warnning
	private static final long serialVersionUID = 1L;

	public HTMLEditorKit.Parser getParser(){
		return super.getParser();
	}
}

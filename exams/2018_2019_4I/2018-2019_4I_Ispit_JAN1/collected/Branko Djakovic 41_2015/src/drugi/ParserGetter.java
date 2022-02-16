package drugi;

import javax.swing.text.html.HTMLEditorKit;

import javafx.scene.web.HTMLEditor;

public class ParserGetter extends HTMLEditorKit {
	public HTMLEditorKit.Parser getParser(){
		return super.getParser();
	}
}

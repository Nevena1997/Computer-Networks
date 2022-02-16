package drugi_zadatak;

import javax.swing.text.html.HTMLEditorKit;

import javafx.scene.web.HTMLEditor;

public class ParserGetter extends HTMLEditor
{
	public HTMLEditorKit.Parser parser;

	public ParserGetter(HTMLEditorKit.Parser parser)
	{
		this.parser = parser;
	}
}

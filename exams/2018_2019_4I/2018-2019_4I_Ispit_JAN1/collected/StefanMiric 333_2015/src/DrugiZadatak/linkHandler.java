package DrugiZadatak;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;

public class linkHandler implements HyperlinkListener{
	private JLabel lbl;
	private List<URL> urlStack;
	private int index;

	public linkHandler(JLabel lbl){
		this.lbl=lbl;
		this.urlStack = new LinkedList<URL>();
		this.index=-1;
	}




	public void undo(){
		if(this.index >= 0){
			this.index--;
			URL url = this.urlStack.get(this.index);

				goToPage(url);

		}

	}

	public void redo(){
		if(this.index < this.urlStack.size() ){
			index++;
			URL url = this.urlStack.get(this.index);

			goToPage(url);
		}
	}

	public void goToPage(URL url){
		setPage(url);
	}




	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		// TODO Auto-generated method stub

	}
}

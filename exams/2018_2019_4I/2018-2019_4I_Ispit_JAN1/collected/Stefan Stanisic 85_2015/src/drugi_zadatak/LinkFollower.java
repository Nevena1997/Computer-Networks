package drugi_zadatak;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class LinkFollower implements HyperlinkListener
{
	private JEditorPane pane;
	private List<URL> urlList;
	private int index;

	public LinkFollower(JEditorPane pane)
	{
		this.pane = pane;
		this.index = -1;
		this.urlList = new ArrayList<URL>();
	}


	@Override
	public void hyperlinkUpdate(HyperlinkEvent e)
	{
		HyperlinkEvent.EventType evt = e.getEventType();
		URL u = e.getURL();
		if(evt == HyperlinkEvent.EventType.ACTIVATED)
			goToPage(u.toString());
	}

	public void goToPage(URL u)
	{
		this.urlList.add(this.index++, u);
		try
		{
			this.pane.setPage(this.urlList.get(this.index));
		}
		catch(Exception e)
		{
			System.out.println("<html>Couldn't go to " + u.toString() + " </html>");
		}
	}

	public void goToPage(String u)
	{
		try
		{
			this.pane.setPage(u);
		}
		catch(IOException e)
		{
			System.out.println("<html>Couldn't go to " + u.toString() + " </html>");
		}
	}



	public void undo()
	{
		if(this.index > 0)
		{
			this.index--;
			URL u = this.urlList.get(this.index);
			try
			{
				goToPage(u.toString());
			}
			catch(Exception e)
			{

			}
		}
	}

	public void redo()
	{
		if(this.index < this.urlList.size() - 1)
		{
			this.index++;
			URL u = this.urlList.get(this.index);
			try
			{
				goToPage(u);
			}
			catch(Exception e)
			{

			}
		}
	}
}

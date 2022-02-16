package drugi;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class Browser extends JApplet{
	public static void main(String[] args){
		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);

		try{
			URL url = new URL("FILE","localhost","C:\\Users\\nalog\\Desktop\\mi15335FilipKristic\\IspitRMJanuar1\\1.html");
			jep.setPage(url);

		}
		catch(MalformedURLException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}

		JButton undoBttn = new JButton("<");
		JButton redoBttn = new JButton(">");
		JButton ciBttn = new JButton("ci");
		jep.add(undoBttn);
		jep.add(redoBttn);
		jep.add(ciBttn);
		jep.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent event) {
				if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
					try{
						jep.setPage(event.getURL());
					}catch(MalformedURLException e){jep.setText("Failed to load url");}
					catch(IOException e){e.printStackTrace();}
				}
			}
		});

		JScrollPane jsp = new JScrollPane(jep);

		JFrame jf = new JFrame("fajerfoks");
		jf.setContentPane(jsp);
		jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jf.setSize(800,600);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				jf.setVisible(true);
			}
		});
	}
}

package zadatak2;

import javax.swing.JFrame;

public class Klasa implements Runnable {

	private JFrame f;

	public Klasa(JFrame f) {
		this.f = f;
	}

	@Override
	public void run() {
		f.setVisible(true);

	}

}

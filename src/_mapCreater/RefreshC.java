package _mapCreater;

import _main.Main;

public class RefreshC implements Runnable {

	@Override
	public void run() {
		while(true) {
			Main.pc.repaint();
			try {Thread.sleep(15);}catch(Exception e) {}
		}
	}

}

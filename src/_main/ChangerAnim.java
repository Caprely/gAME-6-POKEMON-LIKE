package _main;

public class ChangerAnim implements Runnable{

	private String to;
	private int xp, yp;
	
	public ChangerAnim(String to, int xp, int yp) {
		this.to = to;
		this.xp = xp;
		this.yp = yp;
	}

	@Override
	public void run() {
		for(int i = 0; i < 255; i++) {
			Ppan.globalAlpha++;
			try {Thread.sleep(5);}catch(Exception e) {}
		}
		Ppan.currentMap = to;
		Ppan.p.setX(250);
		Ppan.p.setY(250);
		Ppan.decX = 250-xp*45;
		Ppan.decY = 250-yp*45;
		Ppan.mapReader();
		for(int i = 0; i < 255; i++) {
			Ppan.globalAlpha--;
			try {Thread.sleep(5);}catch(Exception e) {}
		}
		Ppan.globalAlpha = 0;
		Ppan.p.setCanMove(true);
		Ppan.tp = false;
	}
	
}

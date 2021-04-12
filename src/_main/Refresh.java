package _main;

public class Refresh implements Runnable{

	private boolean jour = true;
	private boolean nuit = false;
	private int nuitLong = 60;
	private int jourlong = 80;
	
	@Override
	public void run() {
		while(true) {
			if(!Ppan.battle) {
				Main.p.repaint();
			}
			Ppan.frameCompt++;
			if(Ppan.frameCompt%20000==0) {
				if(jour) {
					if(jourlong>0) {
						jourlong--;
					} else {
						if(Ppan.al<180) {
							Ppan.al++;
						} else {
							jour = false;
							nuit = true;
							jourlong = 80;
						}
					}
				} else if(nuit) {
					if(nuitLong>0) {
						nuitLong--;
					} else {
						if(Ppan.al>0) {
							Ppan.al--;
						} else {
							jour = true;
							nuit = false;
							nuitLong = 60;
						}
					}
				}
			}
			try { Thread.sleep(10); } catch(Exception e) {}
		}
	}

}

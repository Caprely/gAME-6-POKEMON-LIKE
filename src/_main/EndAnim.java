package _main;

import javax.swing.JPanel;

public class EndAnim implements Runnable{

	private JPanel p;
	
	public EndAnim(JPanel p) {
		this.p = p;
	}
	
	@Override
	public void run() {
		try {Thread.sleep(1500); }catch(Exception e) {}
		for(int i = 0; i < 250; i++) {
			BattlePad.alpha++;
			try {Thread.sleep(5);}catch(Exception e) {}
		}
		Ppan.globalAlpha = 250;
		Ppan.battle = false;
		BattlePad.alpha = 0;
		BattlePad.playerTurn = true;
		BattlePad.animEnd = false;
		BattlePad.end = false;
		BattlePad.adv = null;
		BattlePad.info = "";
		BattlePad.info2 = "";
		
		Main.f.remove(p);
		Main.f.setContentPane(Main.p);
		Main.p.invalidate();
		Main.p.validate();
		Main.p.requestFocus();
		Main.f.invalidate();
		Main.f.validate();
		
		System.gc();
		
		for(int i = 0; i < 249; i++) {
			Ppan.globalAlpha--;
			try {Thread.sleep(5);}catch(Exception e) {}
		}
		Ppan.globalAlpha = 0;
	}

}

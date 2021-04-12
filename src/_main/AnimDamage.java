package _main;

import javax.swing.ImageIcon;

public class AnimDamage implements Runnable{

	private boolean owner;
	private int att;
	
	public AnimDamage(boolean owner, int att) {
		this.owner = owner;
		this.att = att;
	}
	
	@Override
	public void run() {
		for(int e = 1; e < 10; e++) {
			if(!owner) {
				BattlePad.overlay2 = new ImageIcon("resr/Animations/attack"+e+".png").getImage();
			} else {
				BattlePad.overlay1 = new ImageIcon("resr/Animations/attack"+e+".png").getImage();
			}
			try {Thread.sleep(50);}catch(Exception z) {}
		}
		for(int e = 0; e < 9; e++) {
			if(!owner) {
				BattlePad.overlay2 = new ImageIcon("resr/Animations/attack"+(10-e)+".png").getImage();
			} else {
				BattlePad.overlay1 = new ImageIcon("resr/Animations/attack"+(10-e)+".png").getImage();
			}
			try {Thread.sleep(50);}catch(Exception z) {}
		}
		BattlePad.overlay1 = new ImageIcon("resr/Images/void.png").getImage();
		BattlePad.overlay2 = new ImageIcon("resr/Images/void.png").getImage();
		
		for(int i = 0; i < 3; i++) {
			for(int e = 0; e < 6; e++) {
				if(owner) {
					BattlePad.overlay2 = new ImageIcon("resr/Animations/damage"+e+".png").getImage();
				} else {
					BattlePad.overlay1 = new ImageIcon("resr/Animations/damage"+e+".png").getImage();
				}
				try {Thread.sleep(40);}catch(Exception z) {}
			}
		}
		
		BattlePad.overlay1 = new ImageIcon("resr/Images/void.png").getImage();
		BattlePad.overlay2 = new ImageIcon("resr/Images/void.png").getImage();
		
		if(owner) {
			for(int i = 0; i < (int) this.att*BattlePad.pok.get(BattlePad.selectedPok).getAttack(); i++) {
				if(BattlePad.adv.getHp()<=0) {
					break;
				}
				BattlePad.adv.setHp(BattlePad.adv.getHp() - 1);
				try {Thread.sleep(50);}catch(Exception z) {}
			}
		} else {
			System.out.println(BattlePad.adv.getAttack());
			for(int i = 0; i < (int) this.att*BattlePad.adv.getAttack(); i++) {
				if(BattlePad.pok.get(BattlePad.selectedPok).getHp()<=0) {
					break;
				}
				BattlePad.pok.get(BattlePad.selectedPok).setHp(BattlePad.pok.get(BattlePad.selectedPok).getHp() - 1);
				try {Thread.sleep(50);}catch(Exception z) {}
			}
		}
		
		BattlePad.attacking = false;
		if(BattlePad.playerTurn) {
			BattlePad.playerTurn = false;
		} else {
			BattlePad.playerTurn = true;
		}
	}
}

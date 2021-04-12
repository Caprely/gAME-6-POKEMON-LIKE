package _main;

import java.util.Random;

import javax.swing.ImageIcon;

public class Grass extends Elements{
	
	public Grass(int x, int y) {
		super(x, y);
		
		this.img = new ImageIcon("resr/Images/Grass.png").getImage();
	}

	@Override
	public void col(int xp, int yp) {
		boolean inX = false;
		if((xp+35>=this.x*45+Ppan.decX&&xp+35<=this.x*45+45+Ppan.decX)||(xp<=this.x*45+45+Ppan.decX&&xp>=this.x*45+Ppan.decX)) {
			inX = true;
		}
		boolean inY = false;
		if((yp+33>=this.y*45+Ppan.decY&&yp+33<=this.y*45+45+Ppan.decY)||(yp+2>=this.y*45+Ppan.decY&&yp+2<=this.y*45+45+Ppan.decY)||(yp+2<=this.y*45+Ppan.decY&&yp+33>=this.y*45+45+Ppan.decY)) {
			inY = true;
		}
		
		if(xp+33>=this.x*45+Ppan.decX&&xp+33<=this.x*45+5+Ppan.decX&&inY) {
			launchBattle();
		} else if(xp>=this.x*45+45-10+Ppan.decX&&xp<=this.x*45+45+Ppan.decX&&inY) {
			launchBattle();
		} else if(yp+35>=this.y*45+Ppan.decY&&yp+35<=this.y*45+5+Ppan.decY&&inX) {
			launchBattle();
		} else if(yp>=this.y*45+45-5+Ppan.decY&&yp<=this.y*45+45+Ppan.decY&&inX) {
			launchBattle();
		}
	}
	
	public void launchBattle() {
		Random r = new Random();
		int cu = r.nextInt(10000);
		if(Ppan.battleCool==0) {
			if(cu<50||cu>9900) {
				int id = Ppan.pokPool.get((int) (r.nextInt(Ppan.pokPool.size()*10)/10));
				int level = r.nextInt(6)+5;
				
				Ppan.battle = true;
				Ppan.p.stop();
				Ppan.battleCool=1000;
				
				int sel = 0;
				for(int i = 0; i < Ppan.p.getPokemon().size(); i++) {
					if(Ppan.p.getPokemon().get(i).getHp()>0) {
						sel = i;
						break;
					}
				}
				
				Main.f.remove(Main.p);
				BattlePad.pok = Ppan.p.getPokemon();
				BattlePad.adv = new Pokemon(id, level);
				BattlePad.run = true;
				BattlePad.selectedPok = sel;
				Main.f.setContentPane(Main.bp);
				Main.f.invalidate();
				Main.f.validate();
			}
		} else {
			Ppan.battleCool--;
		}
	}
	
	@Override
	public void particular() {
		
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

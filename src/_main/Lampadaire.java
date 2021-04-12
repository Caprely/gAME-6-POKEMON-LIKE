package _main;

import javax.swing.ImageIcon;

public class Lampadaire extends Elements{

	public Lampadaire(int x, int y) {
		super(x, y);
		
		this.img = new ImageIcon("resr/Images/lampadaire.png").getImage();
	}

	@Override
	public void ligth() {
		int al = Ppan.al;
		
		Ppan.alpha[this.y][this.x] = 10;
		
		/*for(int i = 1; i < 4; i++) {
			Ppan.alpha[this.y-i][this.x] = al-al/(i+1);
			if(Ppan.tab[this.y-i][this.x]!=null&&!Ppan.tab[this.y-i][this.x].getClass().getSimpleName().equals("Path")&&!Ppan.tab[this.y-i][this.x].getClass().getSimpleName().equals("PNJtalk")) {
				break;
			}
		}
		for(int i = 1; i < 4; i++) {
			Ppan.alpha[this.y+i][this.x] = al-al/(i+1);
			if(Ppan.tab[this.y+i][this.x]!=null&&!Ppan.tab[this.y+i][this.x].getClass().getSimpleName().equals("Path")&&!Ppan.tab[this.y+i][this.x].getClass().getSimpleName().equals("PNJtalk")) {
				break;
			}
		}
		for(int i = 1; i < 4; i++) {
			Ppan.alpha[this.y][this.x-i] = al-al/(i+1);
			if(Ppan.tab[this.y][this.x-i]!=null&&!Ppan.tab[this.y][this.x-1].getClass().getSimpleName().equals("Path")&&!Ppan.tab[this.y][this.x-1].getClass().getSimpleName().equals("PNJtalk")) {
				break;
			}
		}
		for(int i = 1; i < 4; i++) {
			Ppan.alpha[this.y][this.x+i] = al-al/(i+1);
			if(Ppan.tab[this.y][this.x+i]!=null&&!Ppan.tab[this.y][this.x+1].getClass().getSimpleName().equals("Path")&&!Ppan.tab[this.y][this.x+1].getClass().getSimpleName().equals("PNJtalk")) {
				break;
			}
		}
		
		try{Ppan.alpha[this.y+1][this.x+1] = (int) (al-al/2.5);}catch(Exception e) {}
		try{Ppan.alpha[this.y+1][this.x-1] = (int) (al-al/2.5);}catch(Exception e) {}
		try{Ppan.alpha[this.y-1][this.x+1] = (int) (al-al/2.5);}catch(Exception e) {}
		try{Ppan.alpha[this.y-1][this.x-1] = (int) (al-al/2.5);}catch(Exception e) {}
		
		try{Ppan.alpha[this.y-3][this.x] = al-al/3;}catch(Exception e) {}
		try{Ppan.alpha[this.y+3][this.x] = al-al/3;}catch(Exception e) {}
		
		try{Ppan.alpha[this.y][this.x-3] = al-al/3;}catch(Exception e) {}
		try{Ppan.alpha[this.y][this.x+3] = al-al/3;}catch(Exception e) {}
		
		try{Ppan.alpha[this.y-2][this.x-1] = al-al/3;}catch(Exception e) {}
		try{Ppan.alpha[this.y-2][this.x+1] = al-al/3;}catch(Exception e) {}
		
		try{Ppan.alpha[this.y+2][this.x-1] = al-al/3;}catch(Exception e) {}
		try{Ppan.alpha[this.y+2][this.x+1] = al-al/3;}catch(Exception e) {}
		
		try{Ppan.alpha[this.y+1][this.x-2] = al-al/3;}catch(Exception e) {}
		try{Ppan.alpha[this.y+1][this.x+2] = al-al/3;}catch(Exception e) {}
		
		try{Ppan.alpha[this.y-1][this.x-2] = al-al/3;}catch(Exception e) {}
		try{Ppan.alpha[this.y-1][this.x+2] = al-al/3;}catch(Exception e) {}*/
		
		try{Ppan.alpha[this.y+1][this.x] = al-al/2;}catch(Exception e) {}
		try{Ppan.alpha[this.y][this.x+1] = al-al/2;}catch(Exception e) {}
		try{Ppan.alpha[this.y-1][this.x] = al-al/2;}catch(Exception e) {}
		try{Ppan.alpha[this.y][this.x-1] = al-al/2;}catch(Exception e) {}
		
		try{Ppan.alpha[this.y-2][this.x] = (int) (al-al/2.5);}catch(Exception e) {}
		try{Ppan.alpha[this.y+2][this.x] = (int) (al-al/2.5);}catch(Exception e) {}
		
		try{Ppan.alpha[this.y][this.x-2] = (int) (al-al/2.5);}catch(Exception e) {}
		try{Ppan.alpha[this.y][this.x+2] = (int) (al-al/2.5);}catch(Exception e) {}
		
		try{Ppan.alpha[this.y+1][this.x+1] = (int) (al-al/2.5);}catch(Exception e) {}
		try{Ppan.alpha[this.y+1][this.x-1] = (int) (al-al/2.5);}catch(Exception e) {}
		try{Ppan.alpha[this.y-1][this.x+1] = (int) (al-al/2.5);}catch(Exception e) {}
		try{Ppan.alpha[this.y-1][this.x-1] = (int) (al-al/2.5);}catch(Exception e) {}
		
		try{Ppan.alpha[this.y-3][this.x] = al-al/3;}catch(Exception e) {}
		try{Ppan.alpha[this.y+3][this.x] = al-al/3;}catch(Exception e) {}
		
		try{Ppan.alpha[this.y][this.x-3] = al-al/3;}catch(Exception e) {}
		try{Ppan.alpha[this.y][this.x+3] = al-al/3;}catch(Exception e) {}
		
		try{Ppan.alpha[this.y-2][this.x-1] = al-al/3;}catch(Exception e) {}
		try{Ppan.alpha[this.y-2][this.x+1] = al-al/3;}catch(Exception e) {}
		
		try{Ppan.alpha[this.y+2][this.x-1] = al-al/3;}catch(Exception e) {}
		try{Ppan.alpha[this.y+2][this.x+1] = al-al/3;}catch(Exception e) {}
		
		try{Ppan.alpha[this.y+1][this.x-2] = al-al/3;}catch(Exception e) {}
		try{Ppan.alpha[this.y+1][this.x+2] = al-al/3;}catch(Exception e) {}
		
		try{Ppan.alpha[this.y-1][this.x-2] = al-al/3;}catch(Exception e) {}
		try{Ppan.alpha[this.y-1][this.x+2] = al-al/3;}catch(Exception e) {}
	}

	@Override
	public boolean check() {
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void particular() {
		// TODO Auto-generated method stub
		
	}

}

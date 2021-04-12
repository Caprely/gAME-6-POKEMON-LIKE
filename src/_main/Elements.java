package _main;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Elements{

	int x, y;
	Image img;
	
	public Elements(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
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
			Ppan.p.setRight(false);
		} else if(xp>=this.x*45+45-10+Ppan.decX&&xp<=this.x*45+45+Ppan.decX&&inY) {
			Ppan.p.setLeft(false);
		} else if(yp+35>=this.y*45+Ppan.decY&&yp+35<=this.y*45+5+Ppan.decY&&inX) {
			Ppan.p.setDown(false);
		} else if(yp>=this.y*45+45-5+Ppan.decY&&yp<=this.y*45+45+Ppan.decY&&inX) {
			Ppan.p.setUp(false);
		}
		
	}

	public abstract void particular();
	public abstract void update();
	public abstract boolean check();
	
	public void ligth() {
		
	}
	
	public Image getImg() {
		return img;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}

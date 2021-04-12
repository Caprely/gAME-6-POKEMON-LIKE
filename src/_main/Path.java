package _main;

import javax.swing.ImageIcon;

public class Path extends Elements {

	boolean update = false;
	
	public Path(int x, int y) {
		super(x, y);
		
		this.img = new ImageIcon("resr/Images/pathho.png").getImage();
		
	}

	@Override
	public void col(int xp, int yp) {
		if(xp+22>=this.x*45+Ppan.decX&&xp+22<=this.x*45+45+Ppan.decX&&yp+22>=this.y*45+Ppan.decY&&yp+22<=this.y*45+45+Ppan.decY) {
		}
	}
	
	@Override
	public void update() {
		if(!update) {
			boolean up = false, down = false, right = false, left = false;
			
			try {if(Ppan.tab[y-1][x].getClass().getSimpleName().equals("Path")) {up = true;}}catch(Exception e) {}
			try {if(Ppan.tab[y+1][x].getClass().getSimpleName().equals("Path")) {down = true;}}catch(Exception e) {}
			try {if(Ppan.tab[y][x-1].getClass().getSimpleName().equals("Path")) {left = true;}}catch(Exception e) {}
			try {if(Ppan.tab[y][x+1].getClass().getSimpleName().equals("Path")) {right = true;}}catch(Exception e) {}
		
			if(up||down) {
				this.img = new ImageIcon("resr/Images/pathve.png").getImage();
			}
			
			if(up) {
				if(left) {
					this.img = new ImageIcon("resr/Images/pathtl.png").getImage();
				} else if(right) {
					this.img = new ImageIcon("resr/Images/pathtr.png").getImage();
				}
			}
			if(down) {
				if(left) {
					this.img = new ImageIcon("resr/Images/pathdl.png").getImage();
				} else if(right) {
					this.img = new ImageIcon("resr/Images/pathdr.png").getImage();
				}
			}
			
			if(up&&down) {
				if(left) {
					this.img = new ImageIcon("resr/Images/pathdul.png").getImage();
				} else if(right) {
					this.img = new ImageIcon("resr/Images/pathdur.png").getImage();
				}
			}
			
			if(right&&left) {
				if(up) {
					this.img = new ImageIcon("resr/Images/pathlru.png").getImage();
				} else if(down) {
					this.img = new ImageIcon("resr/Images/pathlrd.png").getImage();
				}
			}
			
			if(up&&down&&right&&left) {
				this.img = new ImageIcon("resr/Images/pathc.png").getImage();
			}
			
			update = true;
		}
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void particular() {
		// TODO Auto-generated method stub
		
	}

}

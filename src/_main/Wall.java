package _main;

import javax.swing.ImageIcon;

public class Wall extends Elements {

	public Wall(int x, int y) {
		super(x, y);
		
		if(y==Ppan.sizeY-1) {
			this.img = new ImageIcon("resr/Images/wallDown.png").getImage();
		} else {
			this.img = new ImageIcon("resr/Images/wallUp.png").getImage();
		}
	}

	@Override
	public void particular() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		boolean up = false, down = false, right = false, left = false;
		
		try {if(Ppan.tab[y-1][x].getClass().getSimpleName().equals("Wall")) {up = true;}}catch(Exception e) {}
		try {if(Ppan.tab[y+1][x].getClass().getSimpleName().equals("Wall")) {down = true;}}catch(Exception e) {}
		try {if(Ppan.tab[y][x-1].getClass().getSimpleName().equals("Wall")) {left = true;}}catch(Exception e) {}
		try {if(Ppan.tab[y][x+1].getClass().getSimpleName().equals("Wall")) {right = true;}}catch(Exception e) {}
		
		if(up||down) {
			this.img = new ImageIcon("resr/Images/wallSide.png").getImage();
		}
		
		/*if(up) {
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
		}*/
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

}

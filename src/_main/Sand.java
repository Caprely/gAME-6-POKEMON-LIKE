package _main;

import javax.swing.ImageIcon;

public class Sand extends Elements {

	public Sand(int x, int y) {
		super(x, y);
		
		this.img = new ImageIcon("resr/Images/sand.png").getImage();
	}

	@Override 
	public void col(int xp, int yp) {
		
	}
	
	@Override
	public void particular() {
		
	}

	@Override
	public boolean check() {
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

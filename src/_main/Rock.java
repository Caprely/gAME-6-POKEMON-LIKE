package _main;

import javax.swing.ImageIcon;

public class Rock extends Elements{

	public Rock(int x, int y) {
		super(x, y);
		
		this.img = new ImageIcon("resr/Images/Rock.png").getImage();
	}

	@Override
	public void particular() {
		// TODO Auto-generated method stub
		
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

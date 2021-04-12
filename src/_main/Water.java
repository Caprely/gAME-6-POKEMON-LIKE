package _main;

import javax.swing.ImageIcon;

public class Water extends Elements{

	private int currentFrame = 1;
	
	public Water(int x, int y) {
		super(x, y);
		
		this.img = new ImageIcon("resr/Images/water1.png").getImage();
	}

	@Override
	public void particular() {
		if(Ppan.frameCompt%10==0) {
			currentFrame++;
			if(currentFrame==4) {
				currentFrame = 1;
			}
			this.img = new ImageIcon("resr/Images/water"+currentFrame+".png").getImage();
		}
	}

	@Override
	public boolean check() {
		return true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

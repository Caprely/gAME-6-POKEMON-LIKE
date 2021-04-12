package _main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class MapChanger extends Elements {

	private String to = "";
	private int xp = 0, yp = 0;
	
	public MapChanger(int x, int y) {
		super(x, y);
		
		this.img = new ImageIcon("resr/Images/changer.png").getImage();
		
		try(FileInputStream fis = new FileInputStream(new File("resr/Maps/"+Ppan.currentMap+"/changer.txt"))) {
			Scanner sc = new Scanner(fis);
			int line = 1;
			while(sc.hasNextLine()) {
				String cur = sc.nextLine();
				String[] spl = cur.split(",");
				if(Integer.valueOf(spl[0])==x&&Integer.valueOf(spl[1])==y) {
					to = spl[2];
					xp = Integer.valueOf(spl[3]);
					yp = Integer.valueOf(spl[4]);
				}
			}
		} catch(Exception e) {}
	}

	@Override
	public void col(int xp, int yp) {
		if(xp+22>=this.x*45+Ppan.decX&&xp+22<=this.x*45+45+Ppan.decX&&yp+22>=this.y*45+Ppan.decY&&yp+22<=this.y*45+45+Ppan.decY&&!Ppan.tp) {
			Ppan.tp = true;
			Thread change = new Thread(new ChangerAnim(to,this.xp,this.yp));
			Ppan.p.stop();
			Ppan.p.setCanMove(false);
			change.start();
		}
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

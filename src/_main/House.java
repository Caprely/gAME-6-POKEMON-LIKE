package _main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class House extends Elements {

	private int sizeX, sizeY;
	private int xBase, yBase;
	private String map = "";
	private boolean asMap = false;
	
	public House(int x, int y, int sizeX) {
		super(x, y);
		
		this.img = new ImageIcon("resr/Images/"+sizeX+"House.png").getImage();
		this.sizeX = this.img.getWidth(null);
		this.sizeY = this.img.getHeight(null);
		
		this.xBase = x;
		this.yBase = y;
		
		try(FileInputStream fis = new FileInputStream(new File("resr/Maps/"+Ppan.currentMap+"/Houses.txt"))) {
			Scanner sc = new Scanner(fis);
			while(sc.hasNextLine()) {
				String cur = sc.nextLine();
				if(cur.startsWith(this.xBase+""+this.yBase)) {
					String spl[] = cur.split(",");
					this.asMap = true;
					this.map = spl[1];
				}
			}
		} catch(Exception e) {}
		
		this.x = (x*45)-(this.sizeX/2)+30;
		this.y = (y*45)-(this.sizeY/2)-5;
	}

	@Override
	public void col(int xp, int yp) {
		int xCol = this.x;
		int yCol = this.y;
		
		boolean inX = false;
		if((xp+35>=xCol+Ppan.decX&&xp+35<=xCol+this.sizeX+Ppan.decX)||(xp<=xCol+this.sizeX+Ppan.decX&&xp>=xCol+Ppan.decX)) {
			inX = true;
		}
		boolean inY = false;
		if((yp+33>=yCol+Ppan.decY+25&&yp+33<=yCol+this.sizeY+Ppan.decY+25)||(yp+2>=yCol+Ppan.decY-15&&yp+2<=yCol+this.sizeY-15+Ppan.decY)||(yp+2<=yCol-15+Ppan.decY&&yp+33>=yCol+25+this.sizeY+Ppan.decY)) {
			inY = true;
		}
		
		if(xp+33>=xCol+Ppan.decX&&xp+33<=xCol+5+Ppan.decX&&inY) {
			Ppan.p.setRight(false);
		} else if(xp>=xCol+this.sizeX-10+Ppan.decX&&xp<=xCol+this.sizeX+Ppan.decX&&inY) {
			Ppan.p.setLeft(false);
		} else if(yp+35>=yCol+Ppan.decY+25&&yp+35<=yCol+5+Ppan.decY+25&&inX) {
			Ppan.p.setDown(false);
		} else if(yp>=yCol+this.sizeY-20+Ppan.decY&&yp<=yCol+this.sizeY-15+Ppan.decY&&inX) {
			Ppan.p.setUp(false);
		}
		
	}
	
	@Override
	public void particular() {
		Ppan.tp = true;
		Ppan.currentMap = this.map;
		Thread ca = new Thread(new ChangerAnim(this.map, 5, 5));
		ca.start();
	}

	@Override
	public boolean check() {
		boolean check = false;
		
		if(Ppan.p.getX()+22>=this.xBase*45+Ppan.decX&&Ppan.p.getX()+22<=this.xBase*45+45+Ppan.decX&&Ppan.p.getY()+22>=this.yBase*45+45+Ppan.decY&&Ppan.p.getY()+22<=this.yBase*45+90+Ppan.decY) {
			check = true;
		}
		
		return check;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

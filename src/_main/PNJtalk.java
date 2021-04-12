package _main;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class PNJtalk extends Elements{

	private ArrayList<String> chat = new ArrayList<String>();
	private int dir;
	private String name;
	
	public PNJtalk(int x, int y) {
		super(x, y);
		
		this.img = new ImageIcon("resr/Images/backpac.png").getImage();
		
		File current = new File("resr/Maps/"+Ppan.currentMap+"/"+x+""+y+"pnjT.txt");
		int width = 0, heigth = 0;
		ArrayList<String> buffer = new ArrayList<String>();
		
		try(FileInputStream fis = new FileInputStream(current)) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(fis);
			this.name = sc.nextLine();
			while(sc.hasNextLine()) {
				chat.add(sc.nextLine());
			}
		} catch(IOException e) {}
		
	}
	
	@Override
	public boolean check() {
		boolean out = false;
		boolean inY = false, inX = false;
		
		if(Ppan.p.getX()+22>=this.x*45+Ppan.decX&&Ppan.p.getX()+22<=this.x*45+45+Ppan.decX) {
			inX = true;
		}
		if(Ppan.p.getY()+22>=this.y*45+Ppan.decY&&Ppan.p.getY()+22<=this.y*45+45+Ppan.decY) {
			inY = true;
		}
		
		if(Ppan.p.getX()+22>=this.x*45-45+Ppan.decX&&Ppan.p.getX()+22<=this.x*45+Ppan.decX&&inY) {
			out = true;
			dir = 3;
			Ppan.name = this.name;
		} else if(Ppan.p.getX()+22>=this.x*45+45+Ppan.decX&&Ppan.p.getX()+22<=this.x*45+90+Ppan.decX&&inY) {
			out = true;
			dir = 1;
			Ppan.name = this.name;
		} else if(Ppan.p.getY()+22>=this.y*45-45+Ppan.decY&&Ppan.p.getY()+22<=this.y*45+Ppan.decY&&inX) {
			out = true;
			dir = 0;
			Ppan.name = this.name;
		} else if(Ppan.p.getY()+22>=this.y*45+45+Ppan.decY&&Ppan.p.getY()+22<=this.y*45+90+Ppan.decY&&inX) {
			out = true;
			dir = 2;
			Ppan.name = this.name;
		}
		
		return out;
	}

	@Override
	public void particular() {
		Ppan.targetedPnj = this;
		Ppan.chat = true;
	}

	public String getLine(int line) {
		return chat.get(line);
	}
	
	public ArrayList<String> getChat() {
		return chat;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		return name;
	}

}

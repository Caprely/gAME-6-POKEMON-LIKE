package _main;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Pokemon {

	private File data = new File("resr/Pokemon.txt");
	private int id, level, hp, attack, defense, speed, totalHp;
	private String name;
	private Image img;
	private String type;
	private ArrayList<Attack> attList = new ArrayList<Attack>();
	private boolean Dead = false;
	
	public Pokemon(int id, int level) {
		this.id = id;
		this.level = level;
		this.attack = 25;
		
		//TODO get att in pool
		attList.add(new Attack("rue",1,1,"feu",10));
		attList.add(new Attack("gre",1,1,"eau",10));
		attList.add(new Attack("tyu",1,1,"herbe",10));
		attList.add(new Attack("reu2",1,1,"feu",10));
		
		this.img = new ImageIcon("resr/pokemonImages/"+id+".png").getImage();
			
			try(FileInputStream fis = new FileInputStream(data)) {
				int line = id-1;
				Scanner sc = new Scanner(fis);
				for(int i = 0; i <= line; i++) {
					if(i==line) {
						String l = sc.nextLine();
						String[] spl = l.split("-");
						this.name = spl[1];
						this.hp = Integer.valueOf(spl[2])+Integer.valueOf(spl[3])*this.level;
						this.totalHp = this.hp;
					} else {
						sc.nextLine();
					}
				}
			} catch(IOException e) {
				System.out.println(e.getMessage());
			}
	}
	
	public Pokemon(int id, int level, ArrayList<Attack> attList, int att, int def, int speed, int toalhp, String name, int hp) {
		this.id = id;
		this.level = level;
		this.attList = attList;
		this.attack = att;
		this.defense = def;
		this.speed = speed;
		this.totalHp = toalhp;
		this.name = name;
		this.hp = hp;
		
		this.img = new ImageIcon("resr/pokemonImages/"+id+".png").getImage();
	}
	
	public int getLevel() {
		return level;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getName() {
		return name;
	}

	public Image getImg() {
		return img;
	}

	public String getType() {
		return type;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ArrayList<Attack> getAttList() {
		return attList;
	}

	public int getTotalHp() {
		return totalHp;
	}

	public void setTotalHp(int totalHp) {
		this.totalHp = totalHp;
	}

	public boolean isDead() {
		return Dead;
	}

	public void setDead(boolean dead) {
		Dead = dead;
	}
	
}

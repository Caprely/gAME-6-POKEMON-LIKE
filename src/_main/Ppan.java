package _main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Ppan extends JPanel implements KeyListener{

	//NAVIGATION
	public static Elements tab[][];
	public static Integer back[][];
		private Image backGrass = new ImageIcon("resr/Images/backGrass.png").getImage();
		private Image plancher = new ImageIcon("resr/Images/plancher.png").getImage();
	public static int dx = 0, dy = 0;
	public static int sizeY, sizeX;
	public static String currentMap;
	public static boolean tp = false;
	public static int decX = 0, decY = 0;
	
	//LUMINOSITE
	public static int[][] alpha;
	public static int al = 0;
	public static int globalAlpha = 0;
	
	//PNJ
	public static boolean chat = false;
	public ArrayList<String> currentChat = new ArrayList<String>();
	public static int index = 0;
	public static String name = "";
	Image tite2;
	
	//BATTLE
	public static ArrayList<Integer> pokPool = new ArrayList<Integer>();
	public static int battleCool = 250;
	public static Pokemon adv;
	public static int selectedPok = 0;
	
	//GENERAL
	public static boolean battle = false;
	public static int frameCompt = 0;
	
	public static Player p;
	
	public static PNJtalk targetedPnj;
	
	public Ppan(String currentMap) {
		this.currentMap = currentMap;
		
		this.setLayout(null);
		
		mapReader();
		
		Image tite = new ImageIcon("resr/Images/tite.png").getImage();
		tite2 = tite.getScaledInstance(320, 90, Image.SCALE_SMOOTH);
		
		ArrayList<Pokemon> pokk = new ArrayList<Pokemon>();
		
		try(FileInputStream fis = new FileInputStream(new File("resr/PokemonPlayer.txt"))) {
			Scanner sc = new Scanner(fis);
			while(sc.hasNextLine()) {
				String cur = sc.nextLine();
				if(!cur.equals("null")) {
					String[] spl = cur.split("-");
					ArrayList<Attack> att = new ArrayList<Attack>();
					//TODO locate att in att file
					att.add(new Attack(spl[7], 1, 1, "feu",10));
					att.add(new Attack(spl[8], 1, 1, "feu",10));
					att.add(new Attack(spl[9], 1, 1, "feu",10));
					att.add(new Attack(spl[10], 1, 1, "feu",10));
					
					pokk.add(new Pokemon(Integer.valueOf(spl[0]), Integer.valueOf(spl[2]), att, Integer.valueOf(spl[4]), Integer.valueOf(spl[5]), Integer.valueOf(spl[6]), Integer.valueOf(spl[3]), spl[1], Integer.valueOf(spl[11])));
				}
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		p = new Player(4*45,4*45, pokk);
		
		this.setBackground(Color.black);
		this.addKeyListener(this);
		this.addKeyListener(p);
		this.setFocusable(true);
	}
	
	public static void mapReader() {
		File current = new File("resr/Maps/"+currentMap+"/grid.txt");
		int width = 0, heigth = 0;
		ArrayList<String> buffer = new ArrayList<String>();
		
		try(FileInputStream fis = new FileInputStream(current)) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(fis);
			while(sc.hasNextLine()) {
				String str = sc.nextLine();
				String[] spl = str.split("-");
				width = Math.max(width, spl.length);
				buffer.add(str);
				heigth++;
			}
		} catch(IOException e) {}
		
		tab = new Elements[heigth][width];
		back = new Integer[heigth][width];
		alpha = new int[heigth][width];
		sizeY = heigth;
		sizeX = width;
		
		for(int i = 0; i < heigth; i++) {
			String[] spl = buffer.get(i).split("-");
			for(int e = 0; e < spl.length; e++) {
				String spl2[] = spl[e].split(",");
				if(Integer.valueOf(spl2[1])==1) {
					tab[i][e] = new Tree(e,i);
				} else if(Integer.valueOf(spl2[1])==2) {
					tab[i][e] = new Rock(e,i);
				} else if(Integer.valueOf(spl2[1])==3) {
					tab[i][e] = new Grass(e,i);
				} else if(Integer.valueOf(spl2[1])==4) {
					tab[i][e] = new PNJtalk(e,i);
				} else if(Integer.valueOf(spl2[1])==5) {
					tab[i][e] = new Path(e,i);
				} else if(Integer.valueOf(spl2[1])==6) {
					tab[i][e] = new MapChanger(e,i);
				} else if(Integer.valueOf(spl2[1])==7) {
					tab[i][e] = new Lampadaire(e,i);
				} else if(Integer.valueOf(spl2[1])==8) {
					tab[i][e] = new Water(e,i);
				} else if(Integer.valueOf(spl2[1])==9) {
					tab[i][e] = new Sand(e,i);
				} else if(Integer.valueOf(spl2[1])==10) {
					tab[i][e] = new House(e,i,135);
				} else if(Integer.valueOf(spl2[1])==11) {
					tab[i][e] = new Wall(e,i);
				}
				back[i][e] = Integer.valueOf(spl2[0]);
			}
		}
		
		for(int i = 0; i < heigth; i++) {
			for(int e = 0; e < width; e++) {
				if(tab[i][e]!=null) {
					tab[i][e].update();
				}
				alpha[i][e] = al;
			}
		}
		
		current = new File("resr/Maps/"+currentMap+"/pokPool.txt");
		
		try(FileInputStream fis = new FileInputStream(current)) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(fis);
			while(sc.hasNextLine()) {
				String pool = sc.nextLine();
				if(!pool.equals("")) {
					String[] splPool = pool.split("-");
					for(int i = 0; i < splPool.length; i++) {
						pokPool.add(Integer.valueOf(splPool[i]));
					}
				}
			}
		} catch(IOException e) {}
	}
	
	public void resetLum() {
		for(int i = 0; i < sizeY; i++) {
			for(int e = 0; e < sizeX; e++) {
				alpha[i][e] = al;
			}
		}
	}
	
	public static void chat(ArrayList<String> arr) {
		p.setCanMove(false);
		p.stop();
		index = 0;
		chat = true;
	}
	
	public void setCHat() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		ArrayList<Elements> buffer = new ArrayList<Elements>();
		
		for(int i = 0; i < sizeY; i++) {
			for(int e = 0; e < sizeX; e++) {
				if(back[i][e]==1) {
					g2.drawImage(backGrass, e*45+decX, i*45+decY, null);
				} else if(back[i][e]==2) {
					g2.drawImage(plancher, e*45+decX, i*45+decY, null);
				}
			}
		}
		
		for(int i = 0; i < sizeY; i++) {
			for(int e = 0; e < sizeX; e++) {
				if(tab[i][e]!=null) {
					tab[i][e].ligth();
					if(tab[i][e].getClass().getSimpleName().equals("Path")||tab[i][e].getClass().getSimpleName().equals("Sand")
							||tab[i][e].getClass().getSimpleName().equals("Water")) {
						tab[i][e].col(p.getX(), p.getY());
						tab[i][e].particular();
						int decayY = tab[i][e].getImg().getHeight(null)-45;
						g2.drawImage(tab[i][e].getImg(), e*45+decX, i*45-decayY+decY, null);
					}
				}
			}
		}
		
		for(int i = 0; i < sizeY; i++) {
				for(int e = 0; e < sizeX; e++) {
					if(tab[i][e]!=null) {
						if(tab[i][e].getClass().getSimpleName().equals("House")) {
							tab[i][e].col(p.getX(), p.getY());
							g2.drawImage(tab[i][e].getImg(), tab[i][e].getX()+decX, tab[i][e].getY()+decY, null);
						} else if(!tab[i][e].getClass().getSimpleName().equals("Path")&&!tab[i][e].getClass().getSimpleName().equals("Sand")
								&&!tab[i][e].getClass().getSimpleName().equals("Water")){
							tab[i][e].col(p.getX(), p.getY());
							int decayY = tab[i][e].getImg().getHeight(null)-45;
							g2.drawImage(tab[i][e].getImg(), e*45+decX, i*45-decayY+decY, null);
						}
					}
					if(p.getX()+45+22>=e*45+decX&&p.getX()+45+22<=(e+1)*45+decX&&p.getY()+22>=i*45+decY&&p.getY()+22<=(i+1)*45+decY) {
						g2.drawImage(p.getImg(), p.getX()-5, p.getY()-5, null);
					}
				}
		}
		
		for(int i = 0; i < sizeY; i++) {
			for(int e = 0; e < sizeX; e++) {
				g2.setColor(new Color(0,0,0,alpha[i][e]));
				g2.fillRect(e*45+decX, i*45+decY, 45, 45);
			}
		}
		resetLum();
		
		g2.setColor(Color.black);
		Font font1 = new Font("sans-serif",Font.ITALIC + Font.BOLD,13);
		g2.setFont(font1);
		
		if(chat) {
			g2.drawImage(tite2, 15, 15, null);
			g2.drawString(targetedPnj.getName() +":", 28, 38);
			g2.drawString("(->)", 300, 92);
			for(int i = index; i < index+3; i++) {
				try{g2.drawString("   " + targetedPnj.getLine(i), 28, 56+(i-index)*18);}catch(Exception e) {}
			}
		}
		
		if(!tp) {
			p.move();
		}
		if(al>50) {
			p.ligth(false);
		}
			
		if(globalAlpha>5) {
			g2.setColor(new Color(0,0,0,globalAlpha));
			g2.fillRect(0, 0, 1000, 800);
		}
	}

	public static void setBattle(boolean battle) {
		Ppan.battle = battle;
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_ENTER&&chat) {
			index = index+3;
			if(index>=targetedPnj.getChat().size()) {
				chat = false;
				index = 0;
				p.setCanMove(true);
			}
		}
		if(k.getKeyCode()==KeyEvent.VK_A) {
			for(int i = 0; i < sizeY; i++) {
				for(int e = 0; e < sizeX; e++) {
					if(tab[i][e]!=null) {
						if(tab[i][e].check()) {
							tab[i][e].particular();
						}
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}

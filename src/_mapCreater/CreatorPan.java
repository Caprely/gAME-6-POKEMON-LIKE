package _mapCreater;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _main.Elements;
import _main.Grass;
import _main.House;
import _main.Lampadaire;
import _main.MapChanger;
import _main.PNJtalk;
import _main.Path;
import _main.Rock;
import _main.Sand;
import _main.Tree;
import _main.Wall;
import _main.Water;

public class CreatorPan extends JPanel implements KeyListener{

	public static Case[][] cas;
	public static Elements[][] elem;
	public int width, heigth;
	public static int selElem = 4;
	public static int decX = 0, decY = 0;
	JTextField jtf = new JTextField();
	JTextField pokPool = new JTextField();
	public static String folderName = "";
	private boolean crtl = false;
	public static boolean editBack = false;
	
	public CreatorPan(int width, int heigth) {
		this.width = width;
		this.heigth = heigth;
		
		JFrame f = new JFrame("Elements");
		f.setSize(150,350);
		f.setContentPane(new ElemList());
		f.setResizable(false);
		f.setVisible(true);
		
		this.setLayout(null);
		
		jtf.setBounds(0, 0, 100, 20);
		jtf.addKeyListener(this);
		this.add(jtf);
		pokPool.setBounds(0, 20, 100, 20);
		pokPool.addKeyListener(this);
		this.add(pokPool);
		
		cas = new Case[heigth][width];
		elem = new Elements[heigth][width];
		
		for(int i = 0; i < heigth; i++) {
			for(int e = 0; e < width; e++) {
				Case c = new Case(e,i);
				this.addMouseListener(c);
				cas[i][e] = c;
			}
		}
		
		this.addKeyListener(this);
		this.setFocusable(true);
	}
	
	public void mapReader() {
		for(int i = 0; i < heigth; i++) {
			for(int e = 0; e < width; e++) {
				cas[i][e].remove();
			}
		}
		
		File current = new File("resr/Maps/"+folderName+"/grid.txt");
		int widthc = 0, heigthc = 0;
		ArrayList<String> buffer = new ArrayList<String>();
		
		try(FileInputStream fis = new FileInputStream(current)) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(fis);
			while(sc.hasNextLine()) {
				String str = sc.nextLine();
				String[] spl = str.split("-");
				widthc = Math.max(widthc, spl.length);
				buffer.add(str);
				heigthc++;
			}
		} catch(IOException e) {}
		
		elem = new Elements[heigth][width];
		cas = new Case[heigth][width];
		heigth = heigthc;
		width = widthc;
		
		for(int i = 0; i < heigth; i++) {
			for(int e = 0; e < width; e++) {
				Case c = new Case(e,i);
				this.addMouseListener(c);
				cas[i][e] = c;
			}
		}
		
		for(int i = 0; i < heigth; i++) {
			String[] spl = buffer.get(i).split("-");
			for(int e = 0; e < spl.length; e++) {
				String[] spl2 = spl[e].split(",");
				if(Integer.valueOf(spl2[1])==1) {
					elem[i][e] = new Tree(e,i);
				} else if(Integer.valueOf(spl2[1])==2) {
					elem[i][e] = new Rock(e,i);
				} else if(Integer.valueOf(spl2[1])==3) {
					elem[i][e] = new Grass(e,i);
				} else if(Integer.valueOf(spl2[1])==4) {
					elem[i][e] = new PNJtalk(e,i);
				} else if(Integer.valueOf(spl2[1])==5) {
					elem[i][e] = new Path(e,i);
				} else if(Integer.valueOf(spl2[1])==6) {
					elem[i][e] = new MapChanger(e,i);
				} else if(Integer.valueOf(spl2[1])==7) {
					elem[i][e] = new Lampadaire(e,i);
				} else if(Integer.valueOf(spl2[1])==8) {
					elem[i][e] = new Water(e,i);
				} else if(Integer.valueOf(spl2[1])==9) {
					elem[i][e] = new Sand(e,i);
				} else if(Integer.valueOf(spl2[1])==10) {
					elem[i][e] = new House(e,i,135);
				} else if(Integer.valueOf(spl2[1])==11) {
					elem[i][e] = new Wall(e,i);
				}
				cas[i][e].setBackground(Integer.valueOf(spl2[0]));
			}
		}
		
		File pokFile = new File("resr/Maps/"+folderName+"/pokPool.txt");
		
		try(FileInputStream fis = new FileInputStream(pokFile)) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(fis);
			pokPool.setText(sc.nextLine());
		} catch(IOException e) {}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		for(int i = 0; i < heigth; i++) {
			for(int e = 0; e < width; e++) {
				g2.drawRect(e*46+decX, i*46+decY, 45,45);
				if(cas[i][e].getBackground()==1) {
					g2.drawImage(new ImageIcon("resr/Images/backGrass.png").getImage(), e*46+decX, i*46+decY, null);
				} else if(cas[i][e].getBackground()==2) {
					g2.drawImage(new ImageIcon("resr/Images/plancher.png").getImage(), e*46+decX, i*46+decY, null);
				}
			}
		}
		
		for(int i = 0; i < heigth; i++) {
			for(int e = 0; e < width; e++) {
				if(elem[i][e]!=null) {
					if(elem[i][e].getClass().getSimpleName().equals("House")) {
						g2.drawImage(elem[i][e].getImg(), elem[i][e].getX()+decX, elem[i][e].getY()+decY, null);
					} else {
						int decayY = elem[i][e].getImg().getHeight(null)-45;
						g2.drawImage(elem[i][e].getImg(), e*46+decX, i*46-decayY+decY, null);
					}
				}
			}
		}
		
		g2.drawString(""+selElem, 5, 35);
	}

	public void save() {
		File f = new File("resr/Maps/"+folderName);
		
		if(!f.exists()) {
			f.mkdir();
		}
		
		File fGrid = new File("resr/Maps/"+folderName+"/grid.txt");
		
		if(fGrid.exists()) {
			fGrid.delete();
		}
		try {fGrid.createNewFile();} catch (IOException e) {}
		
		ArrayList<String> lines = new ArrayList<String>();
		
		for(int i = 0; i < heigth; i++) {
			String str = "";
			for(int e = 0; e < width; e++ ) {
				int cu = 0;
				if(elem[i][e]!=null) {
					if(elem[i][e].getClass().getSimpleName().equals("Tree")) {
						cu = 1;
					} else if(elem[i][e].getClass().getSimpleName().equals("Rock")) {
						cu = 2;
					} else if(elem[i][e].getClass().getSimpleName().equals("Grass")) {
						cu = 3;
					} else if(elem[i][e].getClass().getSimpleName().equals("PNJtalk")) {
						cu = 4;
					} else if(elem[i][e].getClass().getSimpleName().equals("Path")) {
						cu = 5;
					} else if(elem[i][e].getClass().getSimpleName().equals("MapChanger")) {
						cu = 6;
					} else if(elem[i][e].getClass().getSimpleName().equals("Lampadaire")) {
						cu = 7;
					} else if(elem[i][e].getClass().getSimpleName().equals("Water")) {
						cu = 8;
					} else if(elem[i][e].getClass().getSimpleName().equals("Sand")) {
						cu = 9;
					} else if(elem[i][e].getClass().getSimpleName().equals("House")) {
						cu = 10;
					} else if(elem[i][e].getClass().getSimpleName().equals("Wall")) {
						cu = 11;
					}
				}
				if(str.equals("")) {
					str = cas[i][e].getBackground() + "," + cu;
				} else {
					str = str + "-" + cas[i][e].getBackground() + "," + cu;
				}
			}
			lines.add(str);
		}
		
		for(int i = 0; i < lines.size(); i++) {
			try(PrintWriter print = new PrintWriter(new FileOutputStream(new File("resr/Maps/"+folderName+"/grid.txt"), true))) {
				print.println(lines.get(i));
			} catch (IOException z) {
				z.printStackTrace();
			}
		}
		
		File fPool = new File("resr/Maps/"+folderName+"/pokPool.txt");
		
		if(fPool.exists()) {
			fPool.delete();
		}
		try {fPool.createNewFile();} catch (IOException e) {}
		
		try(PrintWriter print = new PrintWriter(new FileOutputStream(fPool, false))) {
			print.println(pokPool.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_CONTROL) {
			crtl = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_CONTROL) {
			crtl = false;
		}
		
		if(k.getKeyCode()==KeyEvent.VK_RIGHT) {
			this.decX = this.decX + 46;
		} else if(k.getKeyCode()==KeyEvent.VK_LEFT) {
			this.decX = this.decX - 46;
		}
		if(k.getKeyCode()==KeyEvent.VK_UP) {
			this.decY = this.decY - 46;
		} else if(k.getKeyCode()==KeyEvent.VK_DOWN) {
			this.decY = this.decY + 46;
		}
		
		if(k.getKeyCode()==KeyEvent.VK_S&&crtl) {
			folderName = jtf.getText();
			save();
		} else if(k.getKeyCode()==KeyEvent.VK_L&&crtl) {
			folderName = jtf.getText();
			mapReader();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

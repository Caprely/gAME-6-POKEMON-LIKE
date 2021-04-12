package _main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BattlePad extends JPanel implements MouseListener, Runnable{

	public static ArrayList<Pokemon> pok;
	public static Pokemon adv;
	public static int selectedPok = 0;
	static boolean run;
	public static boolean playerTurn = true;
	public static boolean attacking = false;
	Image tite = new ImageIcon("resr/Images/tite.png").getImage();
	Image titePok;
	Image titeInfo;
	
	public static boolean end = false, animEnd = false;
	boolean play = true;
	boolean wild = true;
	
	public static Image overlay1;
	public static Image overlay2;
	
	public static int nbValidPok = 0;
	public static String info = "";
	public static String info2 = "";
	public static int alpha = 0;
	
	int pan = 1;
	
	//Contents
	Image img1;
	ArrayList<String> arr1 = new ArrayList<String>();
	
	Image img2;
	ArrayList<String> arr2 = new ArrayList<String>();
	
	Image img3;
	ArrayList<String> arr3 = new ArrayList<String>();
	
	Image img4;
	ArrayList<String> arr4 = new ArrayList<String>();
	
	Thread bat;
	
	public BattlePad(ArrayList<Pokemon> pok, Pokemon adv, boolean Run, int selectedPok) {
		this.pok = pok;
		this.adv = adv;
		this.run = Run;
		this.selectedPok = selectedPok;
		
		play = true;
		end = false;
		animEnd = false;
		playerTurn = true;
		
		this.img1 = new ImageIcon("resr/Images/backpac.png").getImage();
		this.img2 = new ImageIcon("resr/Images/attIcon.png").getImage();
		this.img3 = new ImageIcon("resr/Images/pokeCube.png").getImage();
		this.img4 = new ImageIcon("resr/Images/runIcon.png").getImage();
		
		overlay1 = new ImageIcon("resr/Images/void.png").getImage();
		overlay2 = new ImageIcon("resr/Images/void.png").getImage();
		
		arr1.add("Bag");
		arr2.add("Attack");
		arr3.add("Pokemon");
		arr4.add("run");
		
		bat = new Thread(this);
		bat.start();
		
		titePok = tite.getScaledInstance(120, 60, Image.SCALE_DEFAULT);
		titeInfo = tite.getScaledInstance(695, 70, Image.SCALE_DEFAULT);
		
		for(int i = 0; i < pok.size(); i++) {
			if(pok.get(i)!=null) {
				nbValidPok++;
			}
		}
		
		info = "A wild " + adv.getName() + " appear.";
		
		this.setFocusable(true);
		this.addMouseListener(this);
	}
	
	public void end(boolean win) {
		if(!animEnd) {
			animEnd = true;
			if(win) {
				info = "You win !";
				info2 = pok.get(selectedPok).getName() + " has gain randomXp !";
			} else {
				info = "You've been defeated...";
				info2 = "You drop randomMoney while running out this figth...";
			}
			Thread endanim = new Thread(new EndAnim(this));
			endanim.start();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		if(pok.get(selectedPok).getHp()<=0) {
			pok.get(selectedPok).setDead(true);
			nbValidPok--;
			if(nbValidPok<=0) {
				end(false);
			}
		}
		if(adv.getHp()<=0) {
			if(wild) {
				end(true);
			}
		}
		
		Font font1 = new Font("sans-serif",Font.BOLD,13);
		g2.setFont(font1);
		int xx = 25, yy = 150;
		if(pok.size()>0) {
			g2.drawImage(pok.get(selectedPok).getImg(), xx, yy, null);
			g2.drawImage(overlay1, xx, yy, null);
			g2.drawImage(titePok, xx+20, yy-70, null);
			g2.drawString(pok.get(selectedPok).getName() + " lvl: "+pok.get(selectedPok).getLevel(), xx+25, yy-52);
			g2.drawString("Hp: "+pok.get(selectedPok).getHp()+"/"+pok.get(selectedPok).getTotalHp(), xx+25, yy-32);
			g2.setColor(Color.black);
			g2.fillRect(xx+25, yy-27, 110, 2);
			g2.setColor(Color.green);
			if(pok.get(selectedPok).getHp()<=pok.get(selectedPok).getTotalHp()/3) {
				g2.setColor(Color.orange);
			}
			if(pok.get(selectedPok).getHp()<=pok.get(selectedPok).getTotalHp()/5) {
				g2.setColor(Color.red);
			}
			
			float test = (float) 110/pok.get(selectedPok).getTotalHp();
			String s = "" + test*pok.get(selectedPok).getHp();
			String out= "";
			String spl[] = s.split("");
			
			for(int i = 0; i < spl.length; i++) {
				if(spl[i].equals(".")) {
					break;
				} else {
					out = out + spl[i];
				}
			}
			
			g2.fillRect(xx+25, yy-27, Integer.valueOf(out), 2);
			g2.setColor(Color.black);
		}
		
		xx=450;
		g2.drawImage(adv.getImg(), xx, yy, null);
		g2.drawImage(overlay2, xx, yy, null);
		g2.drawImage(titePok, xx+20, yy-70, null);
		g2.drawString(adv.getName() + " lvl: "+adv.getLevel(), xx+25, yy-52);
		g2.drawString("Hp: "+adv.getHp()+"/"+adv.getTotalHp(), xx+25, yy-32);
		g2.setColor(Color.black);
		g2.fillRect(xx+25, yy-27, 110, 2);
		g2.setColor(Color.green);
		if(adv.getHp()<=adv.getTotalHp()/3) {
			g2.setColor(Color.orange);
		}
		if(adv.getHp()<=adv.getTotalHp()/5) {
			g2.setColor(Color.red);
		}
		float test = (float) 110/adv.getTotalHp();
		String s = "" + test*adv.getHp();
		String out= "";
		String spl[] = s.split("");
		
		for(int i = 0; i < spl.length; i++) {
			if(spl[i].equals(".")) {
				break;
			} else {
				out = out + spl[i];
			}
		}
		
		g2.fillRect(xx+25, yy-27, Integer.valueOf(out), 2);
		g2.setColor(Color.black);
		
		//GUI
		g2.drawLine(0, 400, 700, 400);
		
		if(!info.equals("")) {
			Font font = new Font("sans-serif",Font.ITALIC,20);
			g2.setFont(font);
			g2.drawImage(titeInfo, 0, 330, null);
			g2.drawString(info, 20, 360);
			g2.drawString(info2, 20, 385);
		}
		
		Font font = new Font("sans-serif",Font.BOLD,22);
		g2.setFont(font);
		
		g2.drawImage(tite, 10, 425, null);
		g2.drawImage(img1, 25, 450, null);
		for(int i = 0; i < arr1.size(); i++) {
			int xp = 125-((arr1.get(i).length()*17)/2)+10;
			g2.drawString(arr1.get(i), xp, 450+i*20);
		}
		
		g2.drawImage(tite, 270, 425, null);
		g2.drawImage(img2, 285, 450, null);
		for(int i = 0; i < arr2.size(); i++) {
			int xp = 375-((arr2.get(i).length()*17)/2)+40;
			g2.drawString(arr2.get(i), xp, 450+i*20);
		}
		
		g2.drawImage(tite, 10, 535, null);
		g2.drawImage(img3, 25, 560, null);
		for(int i = 0; i < arr3.size(); i++) {
			int xp = 125-((arr3.get(i).length()*17)/2)+10;
			g2.drawString(arr3.get(i), xp, 560+i*20);
		}
		
		g2.drawImage(tite, 270, 535, null);
		g2.drawImage(img4, 285, 560, null);
		for(int i = 0; i < arr4.size(); i++) {
			int xp = 375-((arr4.get(i).length()*17)/2)+40;
			g2.drawString(arr4.get(i), xp, 560+i*20);
		}
		
		g2.fillOval(258, 523, 14, 14);
		
		g2.setColor(new Color(0,0,0,alpha));
		g2.fillRect(0, 0, 700, 700);
	}

	public void clear() {
		arr1.clear();
		arr2.clear();
		arr3.clear();
		arr4.clear();
	}
	
	public void menu() {
		clear();
		pan=1;
		this.img1 = new ImageIcon("resr/Images/backpac.png").getImage();
		this.img2 = new ImageIcon("resr/Images/attIcon.png").getImage();
		this.img3 = new ImageIcon("resr/Images/pokeCube.png").getImage();
		this.img4 = new ImageIcon("resr/Images/runIcon.png").getImage();
		
		arr1.add("Bag");
		arr2.add("Attack");
		arr3.add("Pokemon");
		arr4.add("run");
	}
	
	public void process(int but) {
		if(play) {
			if(pan==1&&!attacking&&playerTurn) {
				if(but==1) {
					pan = 2;
					clear();
					this.img1 = new ImageIcon("resr/Images/heal.png").getImage();
					this.img2 = new ImageIcon("resr/Images/2.png").getImage();
					this.img3 = new ImageIcon("resr/Images/callBack.png").getImage();
					this.img4 = new ImageIcon("resr/Images/pokeCube.png").getImage();
					
					arr1.add("Heal");
					arr2.add("CallBack");
					arr3.add("Immune");
					arr4.add("pokeCube");
				} else if(but==2) {
					pan = 3;
					clear();
					this.img1 = new ImageIcon("resr/Images/1.png").getImage();
					this.img2 = new ImageIcon("resr/Images/2.png").getImage();
					this.img3 = new ImageIcon("resr/Images/3.png").getImage();
					this.img4 = new ImageIcon("resr/Images/4.png").getImage();
					
					try{if(!pok.get(selectedPok).getAttList().get(0).getName().equals("null")) {
						arr1.add(pok.get(selectedPok).getAttList().get(0).getName());
						arr1.add(pok.get(selectedPok).getAttList().get(0).getPP()+"/"+pok.get(selectedPok).getAttList().get(0).getTotalPP());
					}}catch(Exception e) {}
					try{if(!pok.get(selectedPok).getAttList().get(1).getName().equals("null")) {
						arr2.add(pok.get(selectedPok).getAttList().get(1).getName());
						arr2.add(pok.get(selectedPok).getAttList().get(1).getPP()+"/"+pok.get(selectedPok).getAttList().get(1).getTotalPP());
					}}catch(Exception e) {}
					try{if(!pok.get(selectedPok).getAttList().get(2).getName().equals("null")) {
						arr3.add(pok.get(selectedPok).getAttList().get(2).getName());
						arr3.add(pok.get(selectedPok).getAttList().get(2).getPP()+"/"+pok.get(selectedPok).getAttList().get(2).getTotalPP());
					}}catch(Exception e) {}
					try{if(!pok.get(selectedPok).getAttList().get(3).getName().equals("null")) {
						arr4.add(pok.get(selectedPok).getAttList().get(3).getName());
						arr4.add(pok.get(selectedPok).getAttList().get(3).getPP()+"/"+pok.get(selectedPok).getAttList().get(3).getTotalPP());
					}}catch(Exception e) {}
				} else if(but==3) {
					pan = 4;
					clear();
					this.img1 = new ImageIcon("resr/Images/void.png").getImage();
					this.img2 = new ImageIcon("resr/Images/void.png").getImage();
					this.img3 = new ImageIcon("resr/Images/void.png").getImage();
					this.img4 = new ImageIcon("resr/Images/void.png").getImage();
					
					if(pok.size()>=1) {
						arr1.add(pok.get(0).getName());
						arr1.add(""+pok.get(0).getHp());
						this.img1 = pok.get(0).getImg();
						this.img1 = this.img1.getScaledInstance(50, 60, Image.SCALE_DEFAULT);
					}
					if(pok.size()>=2) {
						arr2.add(pok.get(1).getName());
						arr2.add(""+pok.get(1).getHp());
						this.img2 = pok.get(1).getImg();
					}
					if(pok.size()>=3) {
						arr3.add(pok.get(2).getName());
						arr3.add(""+pok.get(2).getHp());
						this.img3 = pok.get(2).getImg();
					}
					if(pok.size()>=4) {
						arr4.add(pok.get(3).getName());
						arr4.add(""+pok.get(3).getHp());
						this.img4 = pok.get(3).getImg();
					}
				} else if(but==4){
					if(true) {
						Ppan.battle = false;
						
						Main.f.remove(this);
						Main.f.setContentPane(Main.p);
						Main.p.invalidate();
						Main.p.validate();
						Main.p.requestFocus();
						Main.f.invalidate();
						Main.f.validate();
					}
				}
			} else if(pan==3&&!attacking&&playerTurn) {
				if(but==1) {
					if(!pok.get(selectedPok).getAttList().get(0).getName().equals("null")) {
						atta(0,true);
					}
				} else if(but==2) {
					if(!pok.get(selectedPok).getAttList().get(1).getName().equals("null")) {
						atta(1,true);
					}
				} else if(but==3) {
					if(!pok.get(selectedPok).getAttList().get(2).getName().equals("null")) {
						atta(2,true);
					}
				} else if(but==4) {
					if(!pok.get(selectedPok).getAttList().get(3).getName().equals("null")) {
						atta(3,true);
					}
				}
			}
		}
	}
	
	public void atta(int att, boolean own) {
		attacking = true;
		menu();
		if(own) {
			info = "Your pokemon attack with "+pok.get(selectedPok).getAttList().get(att).getName();
			info2 = "You made " + pok.get(selectedPok).getAttList().get(att).getAtt()*pok.get(selectedPok).getAttack() +" damage.";
			pok.get(selectedPok).getAttList().get(att).attack(own);
		} else {
			info = adv.getName() +" ennemy attack with " + adv.getAttList().get(att).getName();
			info2 = "You take " + adv.getAttack()*adv.getAttList().get(att).getAtt() + " damage.";
			adv.getAttList().get(att).attack(own);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent m) {
		if(m.getX()>=10&&m.getX()<=260) {
			if(m.getY()>=425&&m.getY()<=525) {
				process(1);
			} else if(m.getY()>=535&&m.getY()<=635) {
				process(3);
			}
		} else if(m.getX()>=270&&m.getX()<=520) {
			if(m.getY()>=425&&m.getY()<=525) {
				process(2);
			} else if(m.getY()>=535&&m.getY()<=635) {
				process(4);
			}
		}
		if(m.getX()>=260&&m.getX()<=270&&m.getY()>=525&&m.getY()<=535) {
			menu();
		}
	}

	@Override
	public void run() {
		while(!end) {
			while(playerTurn&&!animEnd) {
				this.repaint();
				try {Thread.sleep(10); } catch(Exception e) {}
			}
			if(!animEnd) {
				try {Thread.sleep(1000);}catch(Exception e) {}
				Random r = new Random();
				int att = r.nextInt(40)/10;
				atta(att,false);
			}
			while(!playerTurn&&!animEnd) {
				this.repaint();
				try {Thread.sleep(10); } catch(Exception e) {}
			}
			while(animEnd) {
				this.repaint();
				try {Thread.sleep(10); } catch(Exception e) {}
			}
		}
	}
	
}

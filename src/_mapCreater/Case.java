package _mapCreater;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import _main.Grass;
import _main.House;
import _main.Lampadaire;
import _main.Main;
import _main.MapChanger;
import _main.PNJtalk;
import _main.Path;
import _main.Rock;
import _main.Sand;
import _main.Tree;
import _main.Wall;
import _main.Water;

public class Case implements MouseListener{

	private int x, y;
	private int background = 1;
	
	public Case(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void remove() {
		Main.pc.removeMouseListener(this);
	}
	
	public int getBackground() {
		return background;
	}

	public void setBackground(int background) {
		this.background = background;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent m) {
		if(!CreatorPan.folderName.equals("")) {
			if(m.getX()>=this.x*46+CreatorPan.decX&&m.getX()<=this.x*46+45+CreatorPan.decX&&m.getY()>=this.y*46+CreatorPan.decY&&m.getY()<=this.y*46+45+CreatorPan.decY) {
				if(CreatorPan.editBack) {
					this.background = CreatorPan.selElem;
					System.out.println(this.background);
				} else {
					if(m.getButton()==1) {
						if(CreatorPan.elem[y][x]!=null) {
							if(CreatorPan.elem[y][x].getClass().getSimpleName().equals("PNJtalk")) {
								JFrame f = new JFrame("Villager chat");
								f.setSize(255, 135);
								f.setLocationRelativeTo(null);
								f.setResizable(false);
								f.setContentPane(new CreateVillagerPan(x,y));
								f.setVisible(true);
							} else if(CreatorPan.elem[y][x].getClass().getSimpleName().equals("MapChanger")) {
								JFrame f = new JFrame("MapChanger param");
								f.setSize(255, 135);
								f.setLocationRelativeTo(null);
								f.setResizable(false);
								f.setContentPane(new CreaterMapCreaterPan(x,y));
								f.setVisible(true);
							} else if(CreatorPan.elem[y][x].getClass().getSimpleName().equals("House")) {
								JFrame f = new JFrame("House targeted map");
								f.setSize(150, 100);
								f.setLocationRelativeTo(null);
								f.setResizable(false);
								f.setContentPane(new HousePan(x,y));
								f.setVisible(true);
							}
						} else {

							if(CreatorPan.selElem==1) {
								CreatorPan.elem[y][x] = new Tree(x,y);
							} else if(CreatorPan.selElem==2) {
								CreatorPan.elem[y][x] = new Rock(x,y);
							} else if(CreatorPan.selElem==3) {
								CreatorPan.elem[y][x] = new Grass(x,y);
							} else if(CreatorPan.selElem==4) {
								CreatorPan.elem[y][x] = new PNJtalk(x,y);
								JFrame f = new JFrame("Villager chat");
								f.setSize(255, 135);
								f.setLocationRelativeTo(null);
								f.setResizable(false);
								f.setContentPane(new CreateVillagerPan(x,y));
								f.setVisible(true);
							} else if(CreatorPan.selElem==5) {
								CreatorPan.elem[y][x] = new Path(x,y);
							} else if(CreatorPan.selElem==6) {
								CreatorPan.elem[y][x] = new MapChanger(x,y);
								JFrame f = new JFrame("MapChanger param");
								f.setSize(255, 135);
								f.setLocationRelativeTo(null);
								f.setResizable(false);
								f.setContentPane(new CreaterMapCreaterPan(x,y));
								f.setVisible(true);
							} else if(CreatorPan.selElem==7) {
								CreatorPan.elem[y][x] = new Lampadaire(x,y);
							} else if(CreatorPan.selElem==8) {
								CreatorPan.elem[y][x] = new Water(x,y);
							} else if(CreatorPan.selElem==9) {
								CreatorPan.elem[y][x] = new Sand(x,y);
							} else if(CreatorPan.selElem==10) {
								CreatorPan.elem[y][x] = new House(x,y,135);
							} else if(CreatorPan.selElem==11) {
								CreatorPan.elem[y][x] = new Wall(x,y);
							}
						
						}
					} else if(m.getButton()==3){
						CreatorPan.elem[y][x] = null;
					}
				}
			}
		}
	}

}

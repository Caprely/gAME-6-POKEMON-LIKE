package _mapCreater;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import _main.Main;

public class ElemList extends JPanel implements MouseListener{
	
	private ArrayList<Image> imgList = new ArrayList<Image>();
	
	public ElemList() {
		initElem(1);
		
		this.addMouseListener(this);
		this.setFocusable(true);
	}
	
	public void initElem(int page) {
		imgList.clear();
		
		if(page==1) {
			imgList.add(new ImageIcon("resr/Images/Tree.png").getImage());
			imgList.add(new ImageIcon("resr/Images/Rock.png").getImage());
			imgList.add(new ImageIcon("resr/Images/grass.png").getImage());
			
			imgList.add(new ImageIcon("resr/Images/Backpac.png").getImage());
			imgList.add(new ImageIcon("resr/Images/Pathve.png").getImage());
			imgList.add(new ImageIcon("resr/Images/changer.png").getImage());
			
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
			imgList.add(new ImageIcon("resr/Images/Water1.png").getImage());
			imgList.add(new ImageIcon("resr/Images/Sand.png").getImage());
			
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
			imgList.add(new ImageIcon("resr/Images/wallUp.png").getImage());
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
		} else if(page==2) {
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
			imgList.add(new ImageIcon("resr/Images/grass.png").getImage());
			
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
			
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
			
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
			imgList.add(new ImageIcon("resr/Images/void.png").getImage());
		}
	}
	
	public void initBack() {
		imgList.clear();
		
		imgList.add(new ImageIcon("resr/Images/backGrass.png").getImage());
		imgList.add(new ImageIcon("resr/Images/plancher.png").getImage());
		imgList.add(new ImageIcon("resr/Images/void.png").getImage());
		
		imgList.add(new ImageIcon("resr/Images/void.png").getImage());
		imgList.add(new ImageIcon("resr/Images/void.png").getImage());
		imgList.add(new ImageIcon("resr/Images/void.png").getImage());
		
		imgList.add(new ImageIcon("resr/Images/void.png").getImage());
		imgList.add(new ImageIcon("resr/Images/void.png").getImage());
		imgList.add(new ImageIcon("resr/Images/void.png").getImage());
		
		imgList.add(new ImageIcon("resr/Images/void.png").getImage());
		imgList.add(new ImageIcon("resr/Images/void.png").getImage());
		imgList.add(new ImageIcon("resr/Images/void.png").getImage());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(imgList.get(0), 0, 0, null);
		g2.drawImage(imgList.get(1), 50, 0, null);
		g2.drawImage(imgList.get(2), 100, 0, null);
		
		g2.drawImage(imgList.get(3), 0, 70, null);
		g2.drawImage(imgList.get(4), 50, 70, null);
		g2.drawImage(imgList.get(5), 100, 70, null);
		
		g2.drawImage(imgList.get(6), 0, 140, null);
		g2.drawImage(imgList.get(7), 50, 140, null);
		g2.drawImage(imgList.get(8), 100, 140, null);
		
		g2.drawImage(imgList.get(9), 0, 210, null);
		g2.drawImage(imgList.get(10), 50, 210, null);
		g2.drawImage(imgList.get(11), 100, 210, null);
		
		if(CreatorPan.editBack) {
			g2.drawRect(0, 294, 100, 20);
			g2.drawString("Edit Font", 20, 309);
		} else {
			g2.drawRect(0, 294, 100, 20);
			g2.drawString("Edit Back", 20, 309);
		}
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
		int mult = 0;
		if(m.getY()>=70&&m.getY()<=115) {
			mult = 1;
		} else if(m.getY()>=140&&m.getY()<=185) {
			mult = 2;
		} else if(m.getY()>=210&&m.getY()<=255) {
			mult = 3;
		}
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = null;
		
		if(m.getX()<=45) {
			CreatorPan.selElem = 1+mult*3;
		} else if(m.getX()>=50&&m.getX()<=95) {
			CreatorPan.selElem = 2+mult*3;
		} else if(m.getX()>=100&&m.getX()<=145) {
			CreatorPan.selElem = 3+mult*3;
		}
		
		if(m.getX()<=100&&m.getY()>=294) {
			if(CreatorPan.editBack) {
				CreatorPan.editBack = false;
				initElem(1);
			} else {
				CreatorPan.editBack = true;
				initBack();
			}
			this.repaint();
		}
		
		Image cursor = imgList.get(CreatorPan.selElem-1);
		
		if(CreatorPan.selElem==7) {
			cursor = new ImageIcon("resr/Images/Lampadaire.png").getImage();
		} else if(CreatorPan.selElem==10) {
			cursor = new ImageIcon("resr/Images/135House.png").getImage();
		}
		
		Cursor c = toolkit.createCustomCursor(cursor , new Point(Main.pc.getX(), Main.pc.getY()), "img");
		Main.pc.setCursor (c);
	}

}

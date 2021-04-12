package _main;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player implements KeyListener {

	private int x, y, speed = 2, sizeX = 35, sizeY = 35;
	private ArrayList<Pokemon> pokemon;
	private boolean up = false, down = false, left = false, right = false, canMove = true;
	Image img = new ImageIcon("resr/Images/player11.png").getImage();
	
	public Player(int x, int y, ArrayList<Pokemon> arr) {
		this.x = x;
		this.y = y;
		this.pokemon = arr;
	}
	
	public void move() {
		if(up) {
			if(getY()-Ppan.decY>0) {
				if(getY()>70) {
					setY(getY()-speed);
				} else {
					Ppan.decY = Ppan.decY + speed;
				}
			}
		} else if(down) {
			if(getY()+45-Ppan.decY<Ppan.sizeY*45) {
				if(getY()<=550) {
					setY(getY()+speed);
				} else {
					Ppan.decY = Ppan.decY - speed;
				}
			}
		}
		if(left) {
			if(getX()-Ppan.decX>0) {
				if(getX()>100) {
					setX(getX()-speed);
				} else {
					Ppan.decX = Ppan.decX + speed;
				}
			}
		} else if(right) {
			if(getX()+45-Ppan.decX<Ppan.sizeX*45) {
				if(getX()<550) {
					setX(getX()+speed);
				} else {
					Ppan.decX = Ppan.decX - speed;
				}
			}
		}
	}
	
	public void stop() {
		this.up = false;
		this.down =false;
		this.left = false;
		this.right = false;
	}
	
	public void block(int ix, int iy) {
		if(ix==-1) {
			left = false;
			setX(getX()+2);
		} else if(ix==1) {
			right = false;
			setX(getX()-2);
		}
		if(iy==-1) {
			up = false;
			setY(getY()+2);
		} else if(iy==1){
			down = false;
			setY(getY()-2);
		}
	}
	
	public void ligth(boolean torche) {
		if(torche) {
			//TODO MORE LUM
		} else {
			int al = Ppan.al;
			int i =  (getY()+22-Ppan.decY)/45;
			int e = (getX()+22-Ppan.decX)/45;
			
			try{Ppan.alpha[i][e] = 0;}catch(Exception z) {}
			try{Ppan.alpha[i+1][e] = al-al/2;}catch(Exception z) {}
			try{Ppan.alpha[i][e+1] = al-al/2;}catch(Exception z) {}
			try{Ppan.alpha[i-1][e] = al-al/2;}catch(Exception z) {}
			try{Ppan.alpha[i][e-1] = al-al/2;}catch(Exception z) {}
			
			try{Ppan.alpha[i-2][e] = al-al/3;}catch(Exception z) {}
			try{Ppan.alpha[i+2][e] = al-al/3;}catch(Exception z) {}
			
			try{Ppan.alpha[i][e-2] = al-al/3;}catch(Exception z) {}
			try{Ppan.alpha[i][e+2] = al-al/3;}catch(Exception z) {}
			
			try{Ppan.alpha[i+1][e+1] = al-al/3;}catch(Exception z) {}
			try{Ppan.alpha[i+1][e-1] = al-al/3;}catch(Exception z) {}
			try{Ppan.alpha[i-1][e+1] = al-al/3;}catch(Exception z) {}
			try{Ppan.alpha[i-1][e-1] = al-al/3;}catch(Exception z) {}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		if(canMove) {
			if(k.getKeyCode()==KeyEvent.VK_UP) {
				up = true;
			} else if(k.getKeyCode()==KeyEvent.VK_DOWN) {
				down = true;
			}
			if(k.getKeyCode()==KeyEvent.VK_LEFT) {
				left = true;
			} else if(k.getKeyCode()==KeyEvent.VK_RIGHT) {
				right = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_UP) {
			up = false;
		} else if(k.getKeyCode()==KeyEvent.VK_DOWN) {
			down = false;
		}
		if(k.getKeyCode()==KeyEvent.VK_LEFT) {
			left = false;
		} else if(k.getKeyCode()==KeyEvent.VK_RIGHT) {
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ArrayList<Pokemon> getPokemon() {
		return pokemon;
	}

	public void setPokemon(ArrayList<Pokemon> pokemon) {
		this.pokemon = pokemon;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public Image getImg() {
		return img;
	}

}

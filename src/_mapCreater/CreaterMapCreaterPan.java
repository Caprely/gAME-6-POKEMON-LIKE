package _mapCreater;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreaterMapCreaterPan extends JPanel implements KeyListener {

	private int x, y;
	private JTextField tarMap = new JTextField();
	private JTextField tarX = new JTextField();
	private JTextField tarY = new JTextField();
	private boolean crtl = false;
			
	public CreaterMapCreaterPan(int x, int y) {
		this.x = x;
		this.y = y;;
		
		this.setLayout(null);
		
		tarMap.addKeyListener(this);
		tarX.addKeyListener(this);
		tarY.addKeyListener(this);
		
		tarMap.setBounds(0, 0, 250, 20);
		tarX.setBounds(0, 21, 124, 20);
		tarY.setBounds(125, 21, 124, 20);
		
		this.add(tarMap);
		this.add(tarX);
		this.add(tarY);
		
		tarMap.setText("Targeted Map");
		tarX.setText("Targeted X");
		tarY.setText("Targeted Y");
		
		File f = new File("resr/Maps/"+CreatorPan.folderName+"/changer.txt");
		
		if(f.exists()) {
			try(FileInputStream fis = new FileInputStream(f)) {
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(fis);
				while(sc.hasNextLine()) {
					String str = sc.nextLine();
					String[] spl = str.split(",");
					
					if(x==Integer.valueOf(spl[0])&&y==Integer.valueOf(spl[1])) {
						x = Integer.valueOf(spl[0]);
						y = Integer.valueOf(spl[1]);
						tarMap.setText(spl[2]);
						tarX.setText(spl[3]);
						tarY.setText(spl[4]);
						break;
					}
				}
			} catch(IOException e) {}
		}
		
		this.addKeyListener(this);
		this.setFocusable(true);
	}
	
	public void save() {

		File fGrid = new File("resr/Maps/"+CreatorPan.folderName+"/changer.txt");
		
		ArrayList<String> stList = new ArrayList<String>();
		
		if(fGrid.exists()) {
			try(FileInputStream fis = new FileInputStream(fGrid)) {
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(fis);
				while(sc.hasNextLine()) {
					String str = sc.nextLine();
					String[] spl = str.split(",");
					
					if(x!=Integer.valueOf(spl[0])||y!=Integer.valueOf(spl[1])) {
						stList.add(str);
					}
				}
			} catch(IOException e) {}
			fGrid.delete();
		}
		
		try {fGrid.createNewFile();} catch (IOException e) {}
		
		try(PrintWriter print = new PrintWriter(new FileOutputStream(fGrid, true))) {
			for(int i = 0; i < stList.size(); i++) {
				print.println(stList.get(i));
			}
			print.println(x+","+y+","+tarMap.getText()+","+tarX.getText()+","+tarY.getText());
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
		if(k.getKeyCode()==KeyEvent.VK_S&&crtl) {
			save();
		}
	}

	@Override
	public void keyTyped(KeyEvent k) {
	}

}

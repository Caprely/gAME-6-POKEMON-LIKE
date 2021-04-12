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

public class HousePan extends JPanel implements KeyListener {

	private int x, y;
	private JTextField tarMap = new JTextField();
	private boolean crtl = false;
			
	public HousePan(int x, int y) {
		this.x = x;
		this.y = y;;
		
		this.setLayout(null);
		
		tarMap.addKeyListener(this);
		
		tarMap.setBounds(0, 0, 250, 50);
		
		this.add(tarMap);
		
		tarMap.setText("Targeted Map");
		
		File f = new File("resr/Maps/"+CreatorPan.folderName+"/Houses.txt");
		
		if(f.exists()) {
			try(FileInputStream fis = new FileInputStream(f)) {
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(fis);
				while(sc.hasNextLine()) {
					String str = sc.nextLine();
					
					if(str.startsWith(this.x+""+this.y)) {
						String[] spl = str.split(",");
						tarMap.setText(spl[1]);
						break;
					}
				}
			} catch(IOException e) {}
		}
		
		this.addKeyListener(this);
		this.setFocusable(true);
	}
	
	public void save() {

		File fGrid = new File("resr/Maps/"+CreatorPan.folderName+"/Houses.txt");
		
		ArrayList<String> stList = new ArrayList<String>();
		
		if(fGrid.exists()) {
			try(FileInputStream fis = new FileInputStream(fGrid)) {
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(fis);
				while(sc.hasNextLine()) {
					String str = sc.nextLine();
					String[] spl = str.split(",");
					
					if(!str.startsWith(this.x+""+this.y)) {
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
			print.println(x+""+y+","+tarMap.getText());
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

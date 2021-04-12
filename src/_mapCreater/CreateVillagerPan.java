package _mapCreater;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CreateVillagerPan extends JPanel implements KeyListener {

	private int x, y;
	private JTextArea jtf = new JTextArea();
	private JScrollPane jsp = new JScrollPane(jtf);
	private JTextArea name = new JTextArea();
	private boolean crtl = false;
			
	public CreateVillagerPan(int x, int y) {
		this.x = x;
		this.y = y;;
		
		this.setLayout(null);
		
		name.addKeyListener(this);
		jtf.addKeyListener(this);
		
		name.setBounds(0, 0, 250, 25);
		name.setText("Villager name");
		
		jsp.setBounds(0, 26, 250, 75);
		jtf.setText("Village text Line by line");
		
		File f = new File("resr/Maps/"+CreatorPan.folderName+"/"+x+""+y+"pnjT.txt");
		
		if(f.exists()) {
			try(FileInputStream fis = new FileInputStream(f)) {
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(fis);
				name.setText(sc.nextLine());
				while(sc.hasNextLine()) {
					jtf.setText(jtf.getText() + "\n" + sc.nextLine());
				}
			} catch(IOException e) {}
		}
		
		this.add(name);
		this.add(jsp);
		
		this.addKeyListener(this);
		this.setFocusable(true);
	}
	
	public void save() {

		File fGrid = new File("resr/Maps/"+CreatorPan.folderName+"/"+x+""+y+"pnjT.txt");
		
		if(fGrid.exists()) {
			fGrid.delete();
		}
		try {fGrid.createNewFile();} catch (IOException e) {}
		
		try(PrintWriter print = new PrintWriter(new FileOutputStream(fGrid, true))) {
			print.println(name.getText());
			print.println(jtf.getText());
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

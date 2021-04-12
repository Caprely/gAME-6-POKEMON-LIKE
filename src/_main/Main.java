package _main;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	public static JFrame f = new JFrame("pok like");
	public static JPanel p = new Ppan("germinal");
	public static JPanel bp = new BattlePad(new ArrayList<Pokemon>(), new Pokemon(0, 0), false, 0);
	public static Thread game = new Thread(new Refresh());
	public static JPanel pc = new _mapCreater.CreatorPan(8, 8);
	public static Thread creater = new Thread(new _mapCreater.RefreshC());
	
	public Main() {
		super();
		
		build();
	}
	
	public void build() {
		f.setSize(1000, 800);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(pc);
		f.setVisible(true);
		//game.start();
		creater.start();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}

}

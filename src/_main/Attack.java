package _main;

public class Attack {

	private String name, type;
	private int att, prec;
	private int PP, totalPP;
	
	public Attack(String name, int att, int prec, String type, int PP) {
		this.name = name;
		this.att = att;
		this.prec = prec;
		this.type = type;
		this.PP = PP;
		this.totalPP = PP;
	}

	public void attack(boolean owner) {
		Thread anim = new Thread(new AnimDamage(owner, this.att));
		anim.start();
		this.PP = this.PP-1;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getAtt() {
		return att;
	}

	public int getPrec() {
		return prec;
	}

	public int getPP() {
		return PP;
	}

	public int getTotalPP() {
		return totalPP;
	}
	
}

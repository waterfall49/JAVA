import java.util.Random;

public class Item {
	private String iName;
	private int attackPoint;
	private int defensePoint;
	private int high_attackPoint;
	private int low_attackPoint;
	private int high_defensePoint;
	private int low_defensePoint;
	private int gold;////////
	
	
	public Item(String iName, int high_attackPoint, int low_attackPoint, int high_defensePoint, int low_defensePoint, int gold){
		this.iName = iName;
		this.high_attackPoint = high_attackPoint;
		this.low_attackPoint = low_attackPoint;
		this.high_defensePoint = high_defensePoint;
		this.low_defensePoint = low_defensePoint;
		this.attackPoint = attackPower(high_attackPoint, low_attackPoint);
		this.defensePoint = defensePower(high_defensePoint, low_defensePoint);
		this.gold = gold;
	}
	
	public Item(){};
	
	public int getAttackPoint() {
		return attackPoint;
	}
	
	/**
	 * 
	 * @param high_attackPoint The Attacks High Point
	 * @param low_attackPoint
	 * @return Int - the attack power
	 */
	private int attackPower(int high_attackPoint, int low_attackPoint){
		Random myRandom = new Random();
		attackPoint = myRandom.nextInt(high_attackPoint-low_attackPoint+1) + low_attackPoint;
		return attackPoint;
	}//end randomGenerate
	
	private int defensePower(int high_defensePoint, int low_defensePoint){
		Random myRandom = new Random();
		defensePoint = myRandom.nextInt(high_defensePoint-low_defensePoint+1) + low_defensePoint;
		return defensePoint;
	}//end randomGenerate

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public int getDefensePoint() {
		return defensePoint;
	}

	public void setDefensePoint(int defensePoint) {
		this.defensePoint = defensePoint;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void setAttackPoint(int attackPoint) {
		this.attackPoint = attackPoint;
	}

	public int getHigh_attackPoint() {
		return high_attackPoint;
	}

	public void setHigh_attackPoint(int high_attackPoint) {
		this.high_attackPoint = high_attackPoint;
	}

	public int getLow_attackPoint() {
		return low_attackPoint;
	}

	public void setLow_attackPoint(int low_attackPoint) {
		this.low_attackPoint = low_attackPoint;
	}

	public int getHigh_defensePoint() {
		return high_defensePoint;
	}

	public void setHigh_defensePoint(int high_defensePoint) {
		this.high_defensePoint = high_defensePoint;
	}

	public int getLow_defensePoint() {
		return low_defensePoint;
	}

	public void setLow_defensePoint(int low_defensePoint) {
		this.low_defensePoint = low_defensePoint;
	}

	public String itemInfo(){
		
		String info = new String();
		info = String.format(" Name: %s \n Attack Point: %d ~ %d\n Defense Point : %d ~ %d\n Gold: %d",
				 this.iName, this.low_attackPoint, this.high_attackPoint, this.low_defensePoint, this.high_defensePoint, this.gold);
		return info;
	}
	
	public String itemRandomInfo(){
		
		String info = new String();
		info = String.format(" Name: %s \n Attack Point: %d\n Defense Point : %d\n Gold: %d",
				 this.iName, this.attackPoint, this.defensePoint, this.gold);
		return info;
	}
	
	
}//end weapon


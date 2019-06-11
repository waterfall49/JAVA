import java.util.ArrayList;

public abstract class Character {

	protected String species; //human or night king
	protected String name;
	protected int healthPoint;
	protected int strength;
	protected int amountGold;
	protected int getDamage;
	protected Item item;  //aggregation relationship 

//	public ArrayList ItemInfoList = ItemArray();
	
	public int getGetDamage() {
		return getDamage;
	}
	
	public void setGetDamage(int getDamage) {
		this.getDamage = getDamage;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealthPoint() {
		if (healthPoint<0)
			healthPoint = 0;
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getAmountGold() {
		if(amountGold<0)
			amountGold = 0;
		return amountGold;
	}

	public void setAmountGold(int amountGold) {
		this.amountGold = amountGold;
	}


	public int calcGold() {
		if(amountGold >= item.getGold())
			return amountGold - item.getGold();
		else
			return -1;
	}

	public String CharacterInfo(){
		
		String info = new String();
		info = String.format(" Name: %s \n Health Point: %d\n Strength : %d\n Amount of Gold: %d",
				 this.name, this.healthPoint, this.strength, this.amountGold);
		return info;
	}
	
	public abstract int attack();
	
	public abstract void defense(int damage);
	
}//end class

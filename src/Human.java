
public class Human extends Character {

	@Override
	public int attack() {
		int damage = 0;
		damage += (item.getAttackPoint() * 0.9) + strength * 1.5 ;
		return damage;
	}//end attack
	
	/**
	 * Gets the defense of the Hooman 
	 */
	@Override
	public void defense(int damage) {
		getDamage = 0;
		getDamage += damage - (item.getDefensePoint() * 0.3) ;
		if(strength < getDamage)
			healthPoint += strength - getDamage;///	
	}
	
	public Human(String name, int healthPoint, int strength, int amountGold){
		this.name = name;
		this.healthPoint = healthPoint;
		this.strength = strength;
		this.amountGold = amountGold;
		this.item = new Item();
		this.species = "Human";
	}//constructor with argument

}//end class

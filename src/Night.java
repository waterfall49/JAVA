
public class Night extends Character {

	@Override
	public int attack() {
		int damage = 0;
		damage += item.getAttackPoint() * 1.5 + strength * 0.9 ;
//		System.out.println(damage);
		return damage;
	}
	
	@Override
	public void defense(int damage) {
		getDamage=0;
		getDamage += damage - (item.getDefensePoint() * 0.2) ;
//		System.out.println(getDamage);
		if(strength <= getDamage)
			healthPoint += strength - getDamage;
//		System.out.println(healthPoint);
	}	
	
	public Night(String name, int healthPoint, int strength, int amountGold){
		this.name = name;
		this.healthPoint = healthPoint;
		this.strength = strength;
		this.amountGold = amountGold;
		this.item = new Item();
		this.species = "Night";
	}//constructor with argument

}

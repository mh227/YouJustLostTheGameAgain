package YouJustLostTheGameAgain.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class WeaponItem extends EquipItem {

	@XmlElement(name = "damage")
	private int damage = 0;
	
	@XmlElement(name = "piercing")
	private int piercing = 0;
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setPiercing(int piercing) {
		this.piercing = piercing;
	}
	
	public int getPiercing() {
		return piercing;
	}
}

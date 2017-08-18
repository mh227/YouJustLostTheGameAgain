package YouJustLostTheGameAgain.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlTransient
public abstract class GameCharacter {
	
	@XmlElement(name = "id")
	private String id = "";
	
	@XmlElement(name = "health")
	private int health = 0;
	
	@XmlElement(name = "baseDamage")
	private int baseDamage = 0;
	
	@XmlElementWrapper(name = "equippedItems")
	@XmlElements({
		@XmlElement(name = "weapon", type = WeaponItem.class),
		@XmlElement(name = "armor", type = ArmorItem.class),
	})
	private List<EquipItem> equippedItems = new ArrayList<EquipItem>();
	
	@XmlElementWrapper(name = "heldItems")
	@XmlElements({
		@XmlElement(name = "healingItem", type = HealingItem.class),
		@XmlElement(name = "useItem", type = UseItem.class),
		@XmlElement(name = "weapon", type = WeaponItem.class),
		@XmlElement(name = "armor", type = ArmorItem.class),
	})
	private List<GameItem> heldItems = new ArrayList<GameItem>();
	
	@XmlElement(name = "positionX")
	private int positionX;
	
	@XmlElement(name = "positionY")
	private int positionY;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	
	public int getBaseDamage() {
		return baseDamage;
	}
	
	public void setEquippedItems(List<EquipItem> equippedItems) {
		this.equippedItems = equippedItems;
	}
	
	public List<EquipItem> getEquippedItems() {
		return equippedItems;
	}
	
	public void setHeldItems(List<GameItem> heldItems) {
		this.heldItems = heldItems;
	}
	
	public List<GameItem> getHeldItems() {
		return heldItems;
	}
	
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	public int getPositionY() {
		return positionY;
	}

}

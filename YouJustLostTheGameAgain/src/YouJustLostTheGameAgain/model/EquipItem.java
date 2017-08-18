package YouJustLostTheGameAgain.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({WeaponItem.class, ArmorItem.class})
@XmlTransient
public abstract class EquipItem extends GameItem {
	public enum EquipSlot {
		HEAD,
		TORSO,
		LEGS,
		HANDS,
		FEET,
		WEAPON
	}
	
	@XmlElement(name = "slot")
	private EquipSlot slot;
	
	public void setSlot(EquipSlot slot) {
		this.slot = slot;
	}
	
	public EquipSlot getSlot() {
		return slot;
	}
}

package YouJustLostTheGameAgain.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
//@XmlSeeAlso({HealingItem.class, ArmorItem.class, WeaponItem.class, UseItem.class})
@XmlTransient
public abstract class GameItem {

	@XmlElement(name = "name")
	protected String name = "";
	
	@XmlElement(name = "shortDescription")
	protected String shortDescription = "";
	
	@XmlElement(name = "longDescription")
	protected String longDescription = "";
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getShortDescription() {
		return shortDescription;
	}
	
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	
	public String getLongDescription() {
		return longDescription;
	}
	
	
}

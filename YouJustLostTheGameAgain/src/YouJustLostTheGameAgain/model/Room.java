package YouJustLostTheGameAgain.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;

@XmlAccessorType(XmlAccessType.FIELD)
public class Room {
	
	@XmlElement(name = "coordX")
	private int coordX;
	
	@XmlElement(name = "coordY")
	private int coordY;
	
	@XmlElementWrapper(name = "items")
	@XmlElements({
		@XmlElement(name = "healingItem", type = HealingItem.class),
		@XmlElement(name = "useItem", type = UseItem.class),
		@XmlElement(name = "weapon", type = WeaponItem.class),
		@XmlElement(name = "armor", type = ArmorItem.class),
	})
	private List<GameItem> items = new ArrayList<GameItem>();
	
	@XmlElement(name = "roamable")
	private boolean roamable = false;
	
	@XmlElement(name = "shortDescription")
	private String shortDescription = "";
	
	@XmlElement(name = "longDescription")
	private String longDescription = "";
	
	@XmlElementWrapper(name = "exits")
	@XmlElement(name = "exit")
	private List<GameExit> exits = new ArrayList<GameExit>();
	
	@XmlElementWrapper(name = "objects")
	@XmlElement(name = "object")
	private List<GameObject> objects = new ArrayList<GameObject>();
	
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	
	public int getCoordX() {
		return coordX;
	}
	
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	public int getCoordY() {
		return coordY;
	}
	
	public void setItems(List<GameItem> items) {
		this.items = items;
	}
	
	public List<GameItem> getItems() {
		return items;
	}
	
	public void setRoamable(boolean roamable) {
		this.roamable = roamable;
	}
	
	public boolean isRoamable() {
		return roamable;
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
	
	public void setExits(List<GameExit> exits) {
		this.exits = exits;
	}
	
	public List<GameExit> getExits() {
		return exits;
	}
	
	public void setObjects(List<GameObject> objects) {
		this.objects = objects;
	}
	
	public List<GameObject> getObjects() {
		return objects;
	}

}

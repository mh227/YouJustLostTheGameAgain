package YouJustLostTheGameAgain.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GameObject {
	
	@XmlElement(name = "name")
	private String name = "";
	
	@XmlElement(name = "id")
	private String id = "";
	
	@XmlElement(name = "description")
	private String description;
	
	@XmlElement(name = "activated")
	private boolean activated = false;
	
	@XmlElement(name = "requiresItem")
	private boolean requiresItem = false;
	
	@XmlElement(name = "activationEvent")
	private GameEvent activationEvent;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public boolean isActivated() {
		return activated;
	}
	
	public void setRequiresItem(boolean requiresItem) {
		this.requiresItem = requiresItem;
	}
	
	public boolean isRequiresItem() {
		return requiresItem;
	}
	
	public void setActivationEvent(GameEvent activationEvent) {
		this.activationEvent = activationEvent;
	}
	
	public GameEvent getActivationEvent() {
		return activationEvent;
	}
}

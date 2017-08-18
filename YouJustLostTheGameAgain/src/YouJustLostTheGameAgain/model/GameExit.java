package YouJustLostTheGameAgain.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GameExit {
	
	public enum ExitPosition {
		TOP,
		BOTTOM,
		LEFT,
		RIGHT
	}
	
	@XmlElement(name = "id")
	private String id = "";
	
	@XmlElement(name = "position")
	private ExitPosition position;
	
	@XmlElement(name = "locked")
	private boolean locked = false;
	
	@XmlElement(name = "blocked")
	private boolean blocked = false;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setPosition(ExitPosition position) {
		this.position = position;
	}
	
	public ExitPosition getPosition() {
		return position;
	}
	
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	public boolean isBlocked() {
		return blocked;
	}

}

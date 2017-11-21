package YouJustLostTheGameAgain.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

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
	
	@XmlElementWrapper(name = "events")
	@XmlElement(name = "event")
	private List<GameEvent> events = new ArrayList<GameEvent>();
	
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
	
	public void setEvents(List<GameEvent> events) {
		this.events = events;
	}
	
	public List<GameEvent> getEvents() {
		return events;
	}

}

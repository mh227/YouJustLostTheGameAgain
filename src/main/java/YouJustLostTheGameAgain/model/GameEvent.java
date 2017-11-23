package YouJustLostTheGameAgain.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;

@XmlAccessorType(XmlAccessType.FIELD)
public class GameEvent {
	
	public enum EventType {
		BLOCK,
		UNBLOCK,
		LOCK,
		UNLOCK,
		DAMAGE,
		END,
		ITEMDROP,
		DIALOGUE
	}
	
	@XmlElement(name = "type")
	private EventType type;
	
	@XmlElement(name = "exitTargetId")
	private String exitTargetId;
	
	@XmlElement(name = "damageTargetId")
	private String damageTargetId;
	
	@XmlElementWrapper(name = "droppedItems")
	@XmlElements({
		@XmlElement(name = "healingItem", type = HealingItem.class),
		@XmlElement(name = "useItem", type = UseItem.class),
		@XmlElement(name = "weapon", type = WeaponItem.class),
		@XmlElement(name = "armor", type = ArmorItem.class),
	})
	private List<GameItem> droppedItems = new ArrayList<GameItem>();
	
	@XmlElement(name = "eventDialogue")
	private DialogueLine eventDialogue;
	
	public void setType(EventType type) {
		this.type = type;
	}
	
	public EventType getType() {
		return type;
	}
	
	public void setExitTargetId(String exitTargetId) {
		this.exitTargetId = exitTargetId;
	}
	
	public String getExitTargetId() {
		return exitTargetId;
	}
	
	public void setDamageTargetId(String damageTargetId) {
		this.damageTargetId = damageTargetId;
	}
	
	public String getDamageTargetId() {
		return damageTargetId;
	}
	
	public void setDroppedItems(List<GameItem> droppedItems) {
		this.droppedItems = droppedItems;
	}
	
	public List<GameItem> getDroppedItems() {
		return droppedItems;
	}
	
	public void setEventDialogue(DialogueLine eventDialogue) {
		this.eventDialogue = eventDialogue;
	}
	
	public DialogueLine getEventDialogue() {
		return eventDialogue;
	}

}

package YouJustLostTheGameAgain.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class GameNPC extends GameCharacter {
	
	@XmlElement(name = "name")
	private String name = "";
	
	@XmlElement(name = "shortDescription")
	private String shortDescription;
	
	@XmlElement(name = "longDescription")
	private String longDescription;

	@XmlElement(name = "hostile")
	private boolean hostile = false;
	
	@XmlElement(name = "baseArmor")
	private int baseArmor = 0;
	
	@XmlElement(name = "roaming")
	private boolean roaming = false;
	
	@XmlElementWrapper(name = "dialogueLines")
	@XmlElement(name = "dialogueLine")
	private List<DialogueLine> dialogueLines = new ArrayList<DialogueLine>();
	
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
	
	public void setHostile(boolean hostile) {
		this.hostile = hostile;
	}
	
	public boolean isHostile() {
		return hostile;
	}
	
	public void setBaseArmor(int baseArmor) {
		this.baseArmor = baseArmor;
	}
	
	public int getBaseArmor() {
		return baseArmor;
	}
	
	public void setRoaming(boolean roaming) {
		this.roaming = roaming;
	}
	
	public boolean isRoaming() {
		return roaming;
	}
	
	public void setDialogueLines(List<DialogueLine> dialogueLines) {
		this.dialogueLines = dialogueLines;
	}
	
	public List<DialogueLine> getDialogueLines() {
		return dialogueLines;
	}
	
}

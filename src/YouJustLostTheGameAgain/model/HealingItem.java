package YouJustLostTheGameAgain.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class HealingItem extends GameItem {
	
	@XmlElement(name = "amountHealed")
	private int amountHealed = 0;
	
	@XmlElement(name = "uses")
	private int uses = 1;
	
	@XmlElement(name = "boost")
	private boolean boost = false;
	
	public void setAmountHealed(int amountHealed) {
		this.amountHealed = amountHealed;
	}
	
	public int getAmountHealed() {
		return amountHealed;
	}
	
	public void setUses(int uses) {
		this.uses = uses;
	}
	
	public int getUses() {
		return uses;
	}
	
	public void setBoost(boolean boost) {
		this.boost = boost;
	}
	
	public boolean isBoost() {
		return boost;
	}
}

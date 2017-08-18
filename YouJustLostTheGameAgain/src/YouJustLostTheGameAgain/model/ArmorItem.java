package YouJustLostTheGameAgain.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ArmorItem extends EquipItem {
	
	@XmlElement(name = "reduction")
	private int reduction = 0;
	
	public void setReduction(int reduction) {
		this.reduction = reduction;
	}
	
	public int getReduction() {
		return reduction;
	}

}

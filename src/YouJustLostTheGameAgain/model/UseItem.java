package YouJustLostTheGameAgain.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class UseItem extends GameItem {
	
	@XmlElement(name = "id")
	private String id = "";
	
	@XmlElementWrapper(name = "matchingIds")
	@XmlElement(name = "matchingId")
	private List<String> matchingIds = new ArrayList<String>();
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setMatchingIds(List<String> matchingIds) {
		this.matchingIds = matchingIds;
	}
	
	public List<String> getMatchingIds() {
		return matchingIds;
	}

}

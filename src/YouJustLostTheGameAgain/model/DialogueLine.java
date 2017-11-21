package YouJustLostTheGameAgain.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DialogueLine {

	public enum LineContext {
		TALK,
		ATTACK,
		KILL,
		DEATH,
		EVENT
	}
	
	@XmlElement(name = "context")
	private LineContext context;
	
	@XmlElement(name = "text")
	private String text = "";
	
	public void setContext(LineContext context) {
		this.context = context;
	}
	
	public LineContext getContext() {
		return context;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}

package YouJustLostTheGameAgain.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerCharacter extends GameCharacter {

	@XmlElement(name = "savedGame")
	private boolean savedGame = false;
	
	public void setSavedGame(boolean savedGame) {
		this.savedGame = savedGame;
	}
	
	public boolean isSavedGame() {
		return savedGame;
	}
}

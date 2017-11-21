package YouJustLostTheGameAgain.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "map")
@XmlType(name = "map")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameMap {
	
	@XmlElement(name = "mapIntro")
	private String mapIntro = "";
	
	@XmlElementWrapper(name = "rooms")
	@XmlElement(name = "room")
	private List<Room> rooms = new ArrayList<Room>();
	
	@XmlElementWrapper(name = "characters")
	@XmlElements({
		@XmlElement(name = "npc", type = GameNPC.class),
		@XmlElement(name = "player", type = PlayerCharacter.class)
	})
	private List<GameCharacter> characters = new ArrayList<GameCharacter>();
	
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
	
	public void setMapIntro(String mapIntro) {
		this.mapIntro = mapIntro;
	}
	
	public String getMapIntro() {
		return mapIntro;
	}
	
	public void setCharacters(List<GameCharacter> characters) {
		this.characters = characters;
	}
	
	public List<GameCharacter> getCharacters() {
		return characters;
	}

}

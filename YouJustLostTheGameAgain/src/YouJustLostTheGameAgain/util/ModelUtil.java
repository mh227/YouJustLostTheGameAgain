package YouJustLostTheGameAgain.util;

import java.util.ArrayList;
import java.util.List;

import YouJustLostTheGameAgain.model.ArmorItem;
import YouJustLostTheGameAgain.model.DialogueLine;
import YouJustLostTheGameAgain.model.DialogueLine.LineContext;
import YouJustLostTheGameAgain.model.EquipItem;
import YouJustLostTheGameAgain.model.EquipItem.EquipSlot;
import YouJustLostTheGameAgain.model.GameCharacter;
import YouJustLostTheGameAgain.model.GameExit;
import YouJustLostTheGameAgain.model.GameExit.ExitPosition;
import YouJustLostTheGameAgain.model.GameItem;
import YouJustLostTheGameAgain.model.GameMap;
import YouJustLostTheGameAgain.model.GameNPC;
import YouJustLostTheGameAgain.model.GameObject;
import YouJustLostTheGameAgain.model.PlayerCharacter;
import YouJustLostTheGameAgain.model.Room;
import YouJustLostTheGameAgain.model.WeaponItem;

public class ModelUtil {

	public static WeaponItem getWeapon(GameCharacter cha) {
		SearchUtil<WeaponItem> util = new SearchUtil<WeaponItem>();
		List<WeaponItem> weapon = util.select(new ArrayList<Object>(cha.getEquippedItems()), equip -> ((EquipItem)equip).getSlot().equals(EquipSlot.WEAPON));
		return weapon.isEmpty() ? null : weapon.get(0);
	}
	
	public static List<ArmorItem> getArmor(GameCharacter cha) {
		SearchUtil<ArmorItem> util = new SearchUtil<ArmorItem>();
		return util.select(new ArrayList<Object>(cha.getEquippedItems()), equip -> ((EquipItem)equip).getSlot().equals(EquipSlot.HEAD)
			|| ((EquipItem)equip).getSlot().equals(EquipSlot.TORSO)
			|| ((EquipItem)equip).getSlot().equals(EquipSlot.LEGS)
			|| ((EquipItem)equip).getSlot().equals(EquipSlot.HANDS)
			|| ((EquipItem)equip).getSlot().equals(EquipSlot.FEET)
		);
	}
	
	public static int getArmorRating(GameCharacter cha) {
		int retval = 0;
		for(ArmorItem armor : getArmor(cha)) {
			retval += armor.getReduction();
		}
		return retval;
	}
	
	public static PlayerCharacter getPlayer(GameMap map) {
		SearchUtil<PlayerCharacter> util = new SearchUtil<PlayerCharacter>();
		return util.select(new ArrayList<Object>(map.getCharacters()), cha -> cha instanceof PlayerCharacter).get(0);
	}
	
	public static List<GameNPC> getNPCsInRoom(GameMap map, int x, int y) {
		SearchUtil<GameNPC> util = new SearchUtil<GameNPC>();
		return util.select(new ArrayList<Object>(map.getCharacters()), cha -> cha instanceof GameNPC
				&& ((GameNPC)cha).getPositionX() == x
				&& ((GameNPC)cha).getPositionY() == y);
	}
	
	public static GameItem getHeldItemByName(GameCharacter cha, String name) {
		SearchUtil<GameItem> util = new SearchUtil<GameItem>();
		List<GameItem> items = util.select(new ArrayList<Object>(cha.getHeldItems()), item -> ((GameItem) item).getName().equalsIgnoreCase(name));
		return items.isEmpty() ? null : items.get(0);
	}
	
	public static GameNPC getNPCByName(List<GameNPC> npcs, String name) {
		SearchUtil<GameNPC> util = new SearchUtil<GameNPC>();
		List<GameNPC> n = util.select(new ArrayList<Object>(npcs), npc -> ((GameNPC) npc).getName().equalsIgnoreCase(name));
		return n.isEmpty() ? null : n.get(0);
	}
	
	public static void setEquipped(GameCharacter cha, EquipItem item) {
		SearchUtil<EquipItem> util = new SearchUtil<EquipItem>();
		List<EquipItem> equips = util.select(new ArrayList<Object>(cha.getEquippedItems()), i -> ((EquipItem) i).getSlot().equals(item.getSlot()));
		EquipItem oldItem = equips.isEmpty() ? null : equips.get(0);
		if(oldItem == null) {
			cha.getEquippedItems().add(item);
			cha.getHeldItems().remove(item);
		} else {
			cha.getEquippedItems().set(cha.getEquippedItems().indexOf(oldItem), item);
			cha.getHeldItems().set(cha.getHeldItems().indexOf(item), oldItem);
		}
	}
	
	public static Room getRoomByCoords(GameMap map, int posX, int posY) {
		SearchUtil<Room> util = new SearchUtil<Room>();
		List<Room> rooms = util.select(new ArrayList<Object>(map.getRooms()), room -> ((Room) room).getCoordX() == posX && ((Room) room).getCoordY() == posY);
		return rooms.isEmpty() ? null : rooms.get(0);
	}
	
	public static GameItem getItemInRoomByName(Room room, String name) {
		SearchUtil<GameItem> util = new SearchUtil<GameItem>();
		List<GameItem> items = util.select(new ArrayList<Object>(room.getItems()), item -> ((GameItem) item).getName().equalsIgnoreCase(name));
		return items.isEmpty() ? null : items.get(0);
	}
	
	public static GameObject getObjectInRoomById(Room room, String id) {
		SearchUtil<GameObject> util = new SearchUtil<GameObject>();
		List<GameObject> objs = util.select(new ArrayList<Object>(room.getObjects()), obj -> ((GameObject) obj).getId().equalsIgnoreCase(id));
		return objs.isEmpty() ? null : objs.get(0);
	}
	
	public static GameExit getExitByDirection(Room room, ExitPosition pos) {
		SearchUtil<GameExit> util = new SearchUtil<GameExit>();
		List<GameExit> exits = util.select(new ArrayList<Object>(room.getExits()), exit -> ((GameExit) exit).getPosition().equals(pos));
		return exits.isEmpty() ? null : exits.get(0);
	}
	
	public static List<DialogueLine> getDialogueLinesByContext(GameNPC npc, LineContext context) {
		SearchUtil<DialogueLine> util = new SearchUtil<DialogueLine>();
		return util.select(new ArrayList<Object>(npc.getDialogueLines()), line -> ((DialogueLine) line).getContext().equals(context));
	}
}

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
import YouJustLostTheGameAgain.model.HealingItem;
import YouJustLostTheGameAgain.model.PlayerCharacter;
import YouJustLostTheGameAgain.model.Room;
import YouJustLostTheGameAgain.model.WeaponItem;

/**
 * @author Mike Henderson
 * 
 * Class that contains methods that simplify accessing or searching the model.
 */
public class ModelUtil {

	/**
	 * @param cha - Any GameCharacter, either player or NPC.
	 * @return - Null if they don't have a weapon equipped, or the equipped WeaponItem if they do.
	 */
	public static WeaponItem getWeapon(GameCharacter cha) {
		SearchUtil<WeaponItem> util = new SearchUtil<WeaponItem>();
		List<WeaponItem> weapon = util.select(new ArrayList<Object>(cha.getEquippedItems()), equip -> ((EquipItem)equip).getSlot().equals(EquipSlot.WEAPON));
		return weapon.isEmpty() ? null : weapon.get(0);
	}
	
	/**
	 * @param cha - Any GameCharacter, either player or NPC.
	 * @return - List of items equipped to armor slots, assumed to be instances of ArmorItem. Empty list if no armor equipped.
	 */
	public static List<ArmorItem> getArmor(GameCharacter cha) {
		SearchUtil<ArmorItem> util = new SearchUtil<ArmorItem>();
		return util.select(new ArrayList<Object>(cha.getEquippedItems()), equip -> ((EquipItem)equip).getSlot().equals(EquipSlot.HEAD)
			|| ((EquipItem)equip).getSlot().equals(EquipSlot.TORSO)
			|| ((EquipItem)equip).getSlot().equals(EquipSlot.LEGS)
			|| ((EquipItem)equip).getSlot().equals(EquipSlot.HANDS)
			|| ((EquipItem)equip).getSlot().equals(EquipSlot.FEET)
		);
	}
	
	/**
	 * @param cha - Any GameCharacter, either player or NPC.
	 * @return - The combined armor rating of all items equipped to armor slots. 0 if nothing equipped.
	 */
	public static int getArmorRating(GameCharacter cha) {
		int retval = 0;
		for(ArmorItem armor : getArmor(cha)) {
			retval += armor.getReduction();
		}
		return retval;
	}
	
	/**
	 * @param map - The GameMap for this story.
	 * @return - The player character. Assumed to exist, since it would be kind of hard to play if it didn't.
	 */
	public static PlayerCharacter getPlayer(GameMap map) {
		SearchUtil<PlayerCharacter> util = new SearchUtil<PlayerCharacter>();
		return util.select(new ArrayList<Object>(map.getCharacters()), cha -> cha instanceof PlayerCharacter).get(0);
	}
	
	/**
	 * @param map - The GameMap for this story.
	 * @param x	- The X coordinate of the room to search.
	 * @param y - The Y coordinate of the room to search.
	 * @return - List of NPCs in that room. Empty if no NPCs in room.
	 */
	public static List<GameNPC> getNPCsInRoom(GameMap map, int x, int y) {
		SearchUtil<GameNPC> util = new SearchUtil<GameNPC>();
		return util.select(new ArrayList<Object>(map.getCharacters()), cha -> cha instanceof GameNPC
				&& ((GameNPC)cha).getPositionX() == x
				&& ((GameNPC)cha).getPositionY() == y);
	}
	
	/**
	 * @param cha - Any GameCharacter, either player or NPC.
	 * @param name - The name of the held item to search for. Case insensitive.
	 * @return - Null if the item cannot be found, otherwise the first item found by that name.
	 */
	public static GameItem getHeldItemByName(GameCharacter cha, String name) {
		SearchUtil<GameItem> util = new SearchUtil<GameItem>();
		List<GameItem> items = util.select(new ArrayList<Object>(cha.getHeldItems()), item -> ((GameItem) item).getName().equalsIgnoreCase(name));
		return items.isEmpty() ? null : items.get(0);
	}
	
	/**
	 * @param npcs - List of NPCs to search.
	 * @param name - Name of the desired NPC. Case insensitive.
	 * @return - Null if the NPC cannot be found, otherwise the first NPC found by that name.
	 */
	public static GameNPC getNPCByName(List<GameNPC> npcs, String name) {
		SearchUtil<GameNPC> util = new SearchUtil<GameNPC>();
		List<GameNPC> n = util.select(new ArrayList<Object>(npcs), npc -> ((GameNPC) npc).getName().equalsIgnoreCase(name));
		return n.isEmpty() ? null : n.get(0);
	}
	
	/**
	 * @param cha - Any GameCharacter, either player or NPC.
	 * @param item - The item to equip. Will replace any item already equipped in that slot.
	 */
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
	
	/**
	 * @param map - The GameMap for this story.
	 * @param posX - The X coordinate of the desired room.
	 * @param posY - The Y coordinate of the desired room.
	 * @return - Null if there is no room at those coordinates, otherwise the first room found with given coordinates. Coordinate pairs are assumed unique.
	 */
	public static Room getRoomByCoords(GameMap map, int posX, int posY) {
		SearchUtil<Room> util = new SearchUtil<Room>();
		List<Room> rooms = util.select(new ArrayList<Object>(map.getRooms()), room -> ((Room) room).getCoordX() == posX && ((Room) room).getCoordY() == posY);
		return rooms.isEmpty() ? null : rooms.get(0);
	}
	
	/**
	 * @param room - The room thought to contain the desired item.
	 * @param name - The item name to search for. Case insensitive.
	 * @return - Null if the item cannot be found, otherwise the first item in the room found by that name.
	 */
	public static GameItem getItemInRoomByName(Room room, String name) {
		SearchUtil<GameItem> util = new SearchUtil<GameItem>();
		List<GameItem> items = util.select(new ArrayList<Object>(room.getItems()), item -> ((GameItem) item).getName().equalsIgnoreCase(name));
		return items.isEmpty() ? null : items.get(0);
	}
	
	/**
	 * @param room - The room thought to contain the desired GameObject.
	 * @param id - The ID of the GameObject to search for. Case insensitive.
	 * @return - Null if the GameObject cannot be found, otherwise the first one found with the given ID.
	 */
	public static GameObject getObjectInRoomById(Room room, String id) {
		SearchUtil<GameObject> util = new SearchUtil<GameObject>();
		List<GameObject> objs = util.select(new ArrayList<Object>(room.getObjects()), obj -> ((GameObject) obj).getId().equalsIgnoreCase(id));
		return objs.isEmpty() ? null : objs.get(0);
	}
	
	/**
	 * @param room - The room thought to contain the desired exit.
	 * @param pos - The direction the desired exit is in.
	 * @return - Null if there is no exit in that direction, otherwise the exit with that ExitPosition. ExitPosition assumed unique within room.
	 */
	public static GameExit getExitByDirection(Room room, ExitPosition pos) {
		SearchUtil<GameExit> util = new SearchUtil<GameExit>();
		List<GameExit> exits = util.select(new ArrayList<Object>(room.getExits()), exit -> ((GameExit) exit).getPosition().equals(pos));
		return exits.isEmpty() ? null : exits.get(0);
	}
	/**
	 * @param npc - The NPC thought to have the desired DialogueLines.
	 * @param context - The LineContext to search for.
	 * @return - All lines for the NPC that have the specified LineContext.
	 */
	public static List<DialogueLine> getDialogueLinesByContext(GameNPC npc, LineContext context) {
		SearchUtil<DialogueLine> util = new SearchUtil<DialogueLine>();
		return util.select(new ArrayList<Object>(npc.getDialogueLines()), line -> ((DialogueLine) line).getContext().equals(context));
	}
	
	/**
	 * @param map - The GameMap for this story.
	 * @param name - Name of the item to pick up. Case insensitive.
	 * @return - True if item was found, false otherwise.
	 */
	public static boolean pickUpItem(GameMap map, String name) {
		PlayerCharacter cha = getPlayer(map);
		Room room = getRoomByCoords(map, cha.getPositionX(), cha.getPositionY());
		GameItem item = getItemInRoomByName(room, name);
		if(item != null) {
			cha.getHeldItems().add(item);
			room.getItems().remove(item);
			return true;
		}
		return false;
	}
	
	/**
	 * @param cha - The player character.
	 * @param item - The healing item to use. Assumed to be in held inventory.
	 */
	public static void useHealingItem(PlayerCharacter cha, HealingItem item) {
		if(cha.getHealth() + item.getAmountHealed() >= cha.getMaxHealth() && !item.isBoost()) {
			cha.setHealth(cha.getMaxHealth());
		} else {
			cha.setHealth(cha.getHealth() + item.getAmountHealed());
		}
		if(item.getUses() == 1) {
			cha.getHeldItems().remove(item);
		} else {
			item.setUses(item.getUses() - 1);
		}
	}
	
	/**
	 * @param map - The GameMap for this story.
	 * @return - All NPCs with more than 0 health and can roam.
	 */
	public static List<GameNPC> getAllLivingRoamingNPCs(GameMap map) {
		SearchUtil<GameNPC> util = new SearchUtil<GameNPC>();
		return util.select(new ArrayList<Object>(map.getCharacters()), cha -> ((GameCharacter) cha).getHealth() > 0 
				&& ((GameCharacter) cha) instanceof GameNPC 
				&& ((GameNPC)cha).isRoaming());
	}
}

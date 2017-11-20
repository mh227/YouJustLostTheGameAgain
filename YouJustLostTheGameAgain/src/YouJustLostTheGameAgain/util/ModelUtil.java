package YouJustLostTheGameAgain.util;

import java.util.ArrayList;
import java.util.List;

import YouJustLostTheGameAgain.model.ArmorItem;
import YouJustLostTheGameAgain.model.EquipItem;
import YouJustLostTheGameAgain.model.EquipItem.EquipSlot;
import YouJustLostTheGameAgain.model.GameCharacter;
import YouJustLostTheGameAgain.model.GameItem;
import YouJustLostTheGameAgain.model.GameMap;
import YouJustLostTheGameAgain.model.GameNPC;
import YouJustLostTheGameAgain.model.PlayerCharacter;
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
}

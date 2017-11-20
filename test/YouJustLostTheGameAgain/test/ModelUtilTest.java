package YouJustLostTheGameAgain.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import YouJustLostTheGameAgain.model.ArmorItem;
import YouJustLostTheGameAgain.model.EquipItem;
import YouJustLostTheGameAgain.model.EquipItem.EquipSlot;
import YouJustLostTheGameAgain.model.GameCharacter;
import YouJustLostTheGameAgain.model.GameItem;
import YouJustLostTheGameAgain.model.GameMap;
import YouJustLostTheGameAgain.model.GameNPC;
import YouJustLostTheGameAgain.model.PlayerCharacter;
import YouJustLostTheGameAgain.model.WeaponItem;
import YouJustLostTheGameAgain.util.ModelUtil;

public class ModelUtilTest {
	
	private WeaponItem weapon;
	private WeaponItem weapon2;
	private ArmorItem helm;
	private ArmorItem torso;
	private PlayerCharacter player;
	private GameNPC goblin;
	private GameNPC grue;
	
	@BeforeEach
	public void setup() {
		weapon = new WeaponItem();
		weapon.setSlot(EquipSlot.WEAPON);
		weapon.setName("Sword");
		weapon2 = new WeaponItem();
		weapon2.setSlot(EquipSlot.WEAPON);
		weapon2.setName("Sword 2: Electric Boogaloo");
		helm = new ArmorItem();
		helm.setSlot(EquipSlot.HEAD);
		helm.setName("Helm of the Ancients");
		helm.setReduction(15);
		torso = new ArmorItem();
		torso.setSlot(EquipSlot.TORSO);
		torso.setName("Steel Plate Armor");
		torso.setReduction(35);
		player = new PlayerCharacter();
		player.setPositionX(0);
		player.setPositionY(0);
		goblin = new GameNPC();
		goblin.setName("Goblin");
		goblin.setPositionX(0);
		goblin.setPositionY(0);
		grue = new GameNPC();
		grue.setName("Grue");
		grue.setPositionX(0);
		grue.setPositionY(0);
	}
	
	@Test
	public void testGetWeapon() {
		player.setEquippedItems(Arrays.asList(weapon, helm, torso));
		Assertions.assertTrue(weapon.equals(ModelUtil.getWeapon(player)));
	}
	
	@Test
	public void testGetNullWeapon() {
		player.setEquippedItems(new ArrayList<EquipItem>(Arrays.asList(helm, torso)));
		Assertions.assertTrue(ModelUtil.getWeapon(player) == null);
	}
	
	@Test
	public void testGetArmor() {
		player.setEquippedItems(new ArrayList<EquipItem>(Arrays.asList(weapon, helm, torso)));
		Assertions.assertTrue(ModelUtil.getArmor(player).containsAll(Arrays.asList(helm, torso)) && !ModelUtil.getArmor(player).contains(weapon));
	}
	
	@Test
	public void testGetNullArmor() {
		player.setEquippedItems(new ArrayList<EquipItem>(Arrays.asList(weapon)));
		Assertions.assertTrue(ModelUtil.getArmor(player).isEmpty());
	}
	
	@Test
	public void testGetArmorRating() {
		player.setEquippedItems(Arrays.asList(weapon, helm, torso));
		Assertions.assertTrue(ModelUtil.getArmorRating(player) == 50);
	}
	
	@Test
	public void testGetNullArmorRating() {
		player.setEquippedItems(new ArrayList<EquipItem>(Arrays.asList(weapon)));
		Assertions.assertTrue(ModelUtil.getArmorRating(player) == 0);
	}
	
	@Test
	public void testGetPlayer() {
		GameMap map = new GameMap();
		map.setCharacters(Arrays.asList(player, grue));
		Assertions.assertTrue(ModelUtil.getPlayer(map).equals(player));
	}
	
	@Test
	public void testGetNPCsInRoom() {
		GameMap map = new GameMap();
		map.setCharacters(Arrays.asList(player, goblin, grue));
		Assertions.assertTrue(ModelUtil.getNPCsInRoom(map, 0, 0).containsAll(Arrays.asList(goblin, grue)) && !ModelUtil.getNPCsInRoom(map, 0, 0).contains(player));
	}
	
	@Test
	public void testGetNullNPCsInRoom() {
		GameMap map = new GameMap();
		map.setCharacters(new ArrayList<GameCharacter>(Arrays.asList(player)));
		Assertions.assertTrue(ModelUtil.getNPCsInRoom(map, 0, 0).isEmpty());
	}
	
	@Test
	public void testGetHeldItemByName() {
		player.setHeldItems(new ArrayList<GameItem>(Arrays.asList(weapon, weapon2)));
		Assertions.assertTrue(ModelUtil.getHeldItemByName(player, "sword").equals(weapon));
	}
	
	@Test
	public void testGetNullHeldItemByName() {
		player.setHeldItems(new ArrayList<GameItem>(Arrays.asList(weapon, weapon2)));
		Assertions.assertTrue(ModelUtil.getHeldItemByName(player, "dagger") == null);
	}
	
	@Test
	public void testGetNPCByName() {
		Assertions.assertTrue(ModelUtil.getNPCByName(Arrays.asList(goblin, grue), "goblin").equals(goblin));
	}
	
	@Test
	public void testGetNullNPCByName() {
		Assertions.assertTrue(ModelUtil.getNPCByName(Arrays.asList(goblin, grue), "darth vader") == null);
	}
	
	@Test
	public void testSetEquippedNew() {
		player.setEquippedItems(new ArrayList<EquipItem>(Arrays.asList(weapon, helm)));
		player.setHeldItems(new ArrayList<GameItem>(Arrays.asList(torso)));
		ModelUtil.setEquipped(player, torso);
		Assertions.assertTrue(player.getEquippedItems().contains(torso) && player.getHeldItems().isEmpty());
	}
	
	@Test
	public void testSetEquippedReplace() {
		player.setEquippedItems(new ArrayList<EquipItem>(Arrays.asList(weapon, helm)));
		player.setHeldItems(new ArrayList<GameItem>(Arrays.asList(weapon2)));
		ModelUtil.setEquipped(player, weapon2);
		Assertions.assertTrue(player.getEquippedItems().contains(weapon2) && player.getHeldItems().contains(weapon));
	}

}

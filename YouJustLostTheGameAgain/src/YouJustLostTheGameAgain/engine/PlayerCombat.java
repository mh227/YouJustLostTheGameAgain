package YouJustLostTheGameAgain.engine;
import java.util.List;
import java.util.Random;

import YouJustLostTheGameAgain.model.ArmorItem;
import YouJustLostTheGameAgain.model.EquipItem;
import YouJustLostTheGameAgain.model.GameCharacter;
import YouJustLostTheGameAgain.model.GameItem;
import YouJustLostTheGameAgain.model.GameMap;
import YouJustLostTheGameAgain.model.GameNPC;
import YouJustLostTheGameAgain.model.HealingItem;
import YouJustLostTheGameAgain.model.PlayerCharacter;
import YouJustLostTheGameAgain.model.UseItem;
import YouJustLostTheGameAgain.model.WeaponItem;
import YouJustLostTheGameAgain.util.ModelUtil;

public class PlayerCombat {

	private PlayerCharacter player;
	private List<GameNPC> enemies;
	private Boolean combatHasEnded;
	private final int ESCAPEFAILUREVALUE = 9;	//This represents a 10% failure to escape chance when running from combat.
	private final int ESCAPECHANCEBOUND = 100;	//Upper exclusive bound to escape chance on range from zero to upper bound.
	
	public PlayerCombat(GameMap map) {
		this.player = ModelUtil.getPlayer(map);
		this.enemies = ModelUtil.getNPCsInRoom(map, player.getPositionX(), player.getPositionY());
		this.combatHasEnded = false;
	}
	
	public String preturnCombatantStates() {
		StringBuilder fightersStatus = new StringBuilder("You take a deep breath and square off against:\n\n");  
		GameNPC currentEnemy;
		for(int enemyIndex = 0; enemyIndex < enemies.size(); enemyIndex++){
			currentEnemy = enemies.get(enemyIndex);
			if(currentEnemy.getHealth() <= 0) {
				fightersStatus.append("\nEnemy ").append(enemyIndex).append(": The corpse of the ").append(currentEnemy.getName())
				.append(" you dispatched litters the battlefield.\n\n");
			}
			else {
			fightersStatus.append("\nEnemy ").append(enemyIndex).append(": "+currentEnemy.getName()).append(" [Health:")
			.append(currentEnemy.getHealth()).append("] = Snarling at you is ").append(currentEnemy.getShortDescription()).append("\n\n");
			}
		}
		fightersStatus.append("What would you like to do?");
		return fightersStatus.toString();
	}
	
	public String playerTakesAction(String playersAction, String playersTarget) {
		//Start off players turn player can do one of three actions attack, run, flee:
		Random rand = new Random();
		switch (playersAction.toLowerCase()) {
		case "attack":
			GameNPC targetedEnemy = ModelUtil.getNPCByName(enemies, playersTarget);
			if(attackConnects(player, targetedEnemy)) {
				int damage = damage(player, targetedEnemy); 
				if(targetedEnemy.getHealth() <= 0) {
					if(allEnemiesAreDead()) {
						combatHasEnded = true;
					}
				}
				return "You bring down your "+ ModelUtil.getWeapon(player).getName() +" on "+ targetedEnemy.getName() +" dealing "+damage+" damage.";
			}
			else{
				return "Your attack swings wide striking only air.";
			}
		
		//Handles using an item by determining which class the item is and how to handle that.
		case "use item":
			GameItem itemToBeUsed = ModelUtil.getHeldItemByName(player, playersTarget);
			
			//Check if Item is healing Consumable and heal to either for items amount up to player's max health and subtract a use on the item.
			if(itemToBeUsed instanceof HealingItem) {
				int healAmount = ((HealingItem) itemToBeUsed).getAmountHealed();
				int playerCurrentHealth = player.getHealth();
				if((playerCurrentHealth + healAmount) > player.getMaxHealth()) {
					healAmount = player.getMaxHealth()-playerCurrentHealth;
				}
				player.setHealth(playerCurrentHealth + healAmount);
				int itemUsesLeft = ((HealingItem) itemToBeUsed).getUses();
				if(itemUsesLeft == 1) {
					player.getHeldItems().remove(playersTarget);
					return "You heal for "+healAmount+" using the last of "+itemToBeUsed.getName()+" you toss away the now useless item";
				}
				((HealingItem) itemToBeUsed).setUses(itemUsesLeft--);
				return "You heal for "+healAmount+" using the "+itemToBeUsed.getName();	
			}
			
			//Swap equipped weapon with one in inventory
			if(itemToBeUsed instanceof WeaponItem) {
				ModelUtil.setEquipped(player, (EquipItem) itemToBeUsed);
				return "You sacrifice this round in favor of pulling out and weilding your "+itemToBeUsed.getName();
			}
			
			//Swap equipped armor with one in inventory
			if(itemToBeUsed instanceof ArmorItem) {
				ModelUtil.setEquipped(player, (EquipItem) itemToBeUsed);
				return "You sacrifice this round in favor of dropping trou and pulling on "+itemToBeUsed.getName()+". You must have been an"
						+ " incredible magician's assistance or exihibitionist in a past life";
			}
			
			//In case a key item is used in combat.
			if(itemToBeUsed instanceof UseItem) {
				return "There is a time and place for everything but this is definitly not it.";
			}
		break;
		
		case "run away":
			int escapeChance = rand.nextInt(ESCAPECHANCEBOUND);//Change to escape is random number on bound of 100.
		 	if(escapeChance > ESCAPEFAILUREVALUE) {
				return "Your cool headed assement is that a tacticle retreat is the better part of valor or to put it another"
									+ " way the enemy can't keep up with you while bowled over laughing at the sight of your chicken headed"
									+ " flight from them. Maybe you should train some more before going back into combat or at least change"
									+ " your pants.";
		 	}
		 	else {
		 		return "You turn to leave but trip over your own feet. You manage to scramble back to your feet just in time to get attacked";
		 	}
		}
		return "Command not understood.";
	}

	public String enemiesTurn() {
		StringBuilder enemyTurn = new StringBuilder("");
		Random rand = new Random();
		int enemyAction = 0;
		for(GameNPC enemy: enemies ) {
			if(enemy.getHealth() <= 0) {
				continue;
			}
			
			//enemies action: simple behavior 60% to attack, 35% to heal, and 5% to flee;
			enemyAction = rand.nextInt(100);
			if(enemyAction <= 60 ) {
				if(attackConnects(enemy, player)) {
					int damage = damage(enemy, player);
					enemyTurn.append(enemy.getName()+" attacks and deals "+damage+" to you.\n\n");
					if(player.getHealth()<=0) {
						enemyTurn.append(enemy.getName()+" rips out your throat and tosses your dieing corpse to the ground");
						combatHasEnded = true;
						return enemyTurn.toString();
					}
				}
				else {
					enemyTurn.append("Dipping left and then right you mannage to dive out of the way of "+enemy.getName()+"'s attack.\n\n");
				}
			}
			else if(enemyAction <= 95 ) {
				int maxHeal = (int) Math.round(enemy.getMaxHealth()*0.15);
				int healing = rand.nextInt(maxHeal+1);
				int currentHealth = enemy.getHealth();
				if((healing+currentHealth)>= enemy.getMaxHealth()) {
					healing = enemy.getMaxHealth()-currentHealth;
				}
				enemy.setHealth(healing+currentHealth);
				enemyTurn.append(enemy.getName()+" licks their wounds and heals for "+healing+".\n\n");
			}
			else
				enemyTurn.append(enemy.getName()+" panics at your sight of your face and flees the scene.\n\n");
				enemies.remove(enemy);
				if(allEnemiesAreDead()) {
					combatHasEnded = true;
				}
		}
		return enemyTurn.toString();
	}
	
	private boolean attackConnects(GameCharacter attacker, GameCharacter defender) {
		Random rand = new Random();
		WeaponItem weapon = ModelUtil.getWeapon(attacker);
		int armorRating = ModelUtil.getArmorRating(defender);
		//if((rand.nextInt(20)+1)+();
		return false;
	}
	
	private int damage(GameCharacter attacker, GameCharacter defender) {
		Random rand = new Random();
		WeaponItem weapon = ModelUtil.getWeapon(attacker);
		int armorRating = ModelUtil.getArmorRating(defender);
		return 0;
	}

	public boolean isCombatOver() {
		return combatHasEnded;
	}
	
	private boolean allEnemiesAreDead() {
		for(GameNPC enemy: enemies) {
			if(enemy.getHealth() > 0) {
				return false;
			}
		}
		return true;
	}
	
	
}

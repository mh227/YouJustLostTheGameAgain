package YouJustLostTheGameAgain.test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import YouJustLostTheGameAgain.engine.CommandParser;

public class CommandParserTest {
	
	@Test
	public void testLookRegex() {
		for(String command : Arrays.asList("look", "look around", "survey", "search")) {
			Assertions.assertTrue(CommandParser.LOOK_REGEX.matcher(command).matches(), String.format("Expected match for %s.", command));
		}
	}
	
	@Test
	public void testLookDirectionRegex() {
		for(String command : Arrays.asList("look", "view")) {
			for(String direction : Arrays.asList("n", "s", "e", "w", "north", "south", "east", "west", "up", "down", "left", "right")) {
				Matcher matcher = CommandParser.LOOK_DIRECTION_REGEX.matcher(command + " " + direction);
				Assertions.assertTrue(matcher.matches() && direction.equals(matcher.group("direction")), String.format("Expected %s %s to return %s, got %s", command, direction, direction, matcher.group("direction")));
			}
		}
	}
	
	@Test
	public void testInspectRegex() {
		for(String command : Arrays.asList("inspect", "examine", "look at", "view")) {
			Matcher matcher = CommandParser.INSPECT_REGEX.matcher(command + " sword");
			Assertions.assertTrue(matcher.matches() && "sword".equals(matcher.group("target")), String.format("Expected %s sword to return sword, got %s.", command, matcher.group("target")));
		}
	}
	
	@Test
	public void testHelpRegex() {
		Matcher matcher = CommandParser.HELP_REGEX.matcher("help inspect");
		Assertions.assertTrue(matcher.matches() && "inspect".equals(matcher.group("command")), String.format("Expected help inspect to return inspect, got %s", matcher.group("command")));
		Assertions.assertTrue(CommandParser.HELP_REGEX.matcher("help").matches());
	}
	
	@Test
	public void testRestartRegex() {
		for(String command : Arrays.asList("restart", "reboot", "reload", "die", "kill self", "kill me", "suicide", "commit suicide")) {
			Assertions.assertTrue(CommandParser.RESTART_REGEX.matcher(command).matches(), String.format("Expected match for %s.", command));
		}
	}
	
	@Test
	public void testMoveRegex() {
		for(String command : Arrays.asList("walk", "run", "go", "move", "head")) {
			for(String direction : Arrays.asList("n", "s", "e", "w", "north", "south", "east", "west", "up", "down", "left", "right")) {
				Matcher matcher = CommandParser.MOVE_REGEX.matcher(command + " " + direction);
				Assertions.assertTrue(matcher.matches() && direction.equals(matcher.group("direction")), String.format("Expected %s %s to return %s, got %s", command, direction, direction, matcher.group("direction")));
			}
		}
	}
	
	@Test
	public void testUseRegex() {
		for(String command : Arrays.asList("use", "equip", "wear", "drink", "eat", "activate", "unlock", "unblock")) {
			Matcher matcher = CommandParser.USE_REGEX.matcher(command + " thing");
			Assertions.assertTrue(matcher.matches() && "thing".equals(matcher.group("target")), String.format("Expected %s thing to return thing, got %s.", command, matcher.group("target")));
		}
	}
	
	@Test
	public void testUseOnRegex() {
		for(String command : Arrays.asList("use thing1 on thing2", "use thing1 with thing2")) {
			Matcher matcher = CommandParser.USE_ON_REGEX.matcher(command);
			Assertions.assertTrue(matcher.matches() && "thing1".equals(matcher.group("item")), String.format("Expected %s to return thing1, got %s.", command, matcher.group("item")));
			Assertions.assertTrue("thing2".equals(matcher.group("target")), String.format("Expected %s to return thing2, got %s.", command, matcher.group("target")));
		}
	}
	
	@Test
	public void testTalkRegex() {
		for(String command1 : Arrays.asList("talk", "speak", "converse", "listen")) {
			for(String command2 : Arrays.asList("with", "to")) {
				Matcher matcher = CommandParser.TALK_REGEX.matcher(command1 + " " + command2 + " thing");
				Assertions.assertTrue(matcher.matches() && "thing".equals(matcher.group("target")), String.format("Expected %s %s thing to return thing, got %s.", command1, command2, matcher.group("target")));
			}
		}
	}
	
	@Test
	public void testTakeRegex() {
		for(String command : Arrays.asList("take", "pick up", "acquire", "grab", "collect")) {
			Matcher matcher = CommandParser.TAKE_REGEX.matcher(command + " thing");
			Assertions.assertTrue(matcher.matches() && "thing".equals(matcher.group("target")), String.format("Expected %s thing to return thing, got %s.", command, matcher.group("target")));
		}
	}
	
	@Test
	public void testDropRegex() {
		for(String command : Arrays.asList("discard", "toss", "trash", "drop")) {
			Matcher matcher = CommandParser.DROP_REGEX.matcher(command + " thing");
			Assertions.assertTrue(matcher.matches() && "thing".equals(matcher.group("target")), String.format("Expected %s thing to return thing, got %s.", command, matcher.group("target")));
		}
	}
	
	@Test
	public void testAttackRegex() {
		for(String command : Arrays.asList("attack", "kill", "murder", "maim", "hurt", "punch", "injure")) {
			Matcher matcher = CommandParser.ATTACK_REGEX.matcher(command + " enemy");
			Assertions.assertTrue(matcher.matches() && "enemy".equals(matcher.group("target")), String.format("Expected %s enemy to return enemy, got %s.", command, matcher.group("target")));
		}
	}
	
	@Test
	public void testSaveRegex() {
		for(String command : Arrays.asList("save", "save me", "saaave meee")) {
			Assertions.assertTrue(CommandParser.SAVE_REGEX.matcher(command).matches(), String.format("Expected match for %s.", command));
		}		
	}

}

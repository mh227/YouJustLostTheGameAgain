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
		Pattern pattern = Pattern.compile(CommandParser.LOOK_REGEX);
		for(String command : Arrays.asList("look", "look around", "survey", "search")) {
			Assertions.assertTrue(pattern.matcher(command).matches(), String.format("No match on %s.", command));
		}
	}
	
	@Test
	public void testLookDirectionRegex() {
		Pattern pattern = Pattern.compile(CommandParser.LOOK_DIRECTION_REGEX);
		for(String command : Arrays.asList("look", "view")) {
			for(String direction : Arrays.asList("n", "s", "e", "w", "north", "south", "east", "west", "up", "down", "left", "right")) {
				Matcher matcher = pattern.matcher(command + " " + direction);
				Assertions.assertTrue(matcher.matches() && direction.equals(matcher.group("direction")), String.format("Expected %s %s to return %s, got %s", command, direction, direction, matcher.group("direction")));
			}
		}
	}
	
	@Test
	public void testInspectRegex() {
		Pattern pattern = Pattern.compile(CommandParser.INSPECT_REGEX);
		for(String command : Arrays.asList("inspect", "examine", "look at", "view")) {
			Matcher matcher = pattern.matcher(command + " sword");
			Assertions.assertTrue(matcher.matches() && "sword".equals(matcher.group("target")), String.format("Expected %s sword to return sword, got %s.", command, matcher.group("target")));
		}
	}
	
	@Test
	public void testHelpRegex() {
		Pattern pattern = Pattern.compile(CommandParser.HELP_REGEX);
		Matcher matcher = pattern.matcher("help inspect");
		Assertions.assertTrue(matcher.matches() && "inspect".equals(matcher.group("command")), String.format("Expected help inspect to return inspect, got %s", matcher.group("command")));
		Assertions.assertTrue(pattern.matcher("help").matches());
	}
	
	@Test
	public void testRestartRegex() {
		Pattern pattern = Pattern.compile(CommandParser.RESTART_REGEX);
		for(String command : Arrays.asList("restart", "reboot", "reload", "die", "kill self", "kill me", "suicide", "commit suicide")) {
			Assertions.assertTrue(pattern.matcher(command).matches(), String.format("Expected match for %s.", command));
		}
	}
	
	@Test
	public void testMoveRegex() {
		Pattern pattern = Pattern.compile(CommandParser.MOVE_REGEX);
		for(String command : Arrays.asList("walk", "run", "go", "move", "head")) {
			for(String direction : Arrays.asList("n", "s", "e", "w", "north", "south", "east", "west", "up", "down", "left", "right")) {
				Matcher matcher = pattern.matcher(command + " " + direction);
				Assertions.assertTrue(matcher.matches() && direction.equals(matcher.group("direction")), String.format("Expected %s %s to return %s, got %s", command, direction, direction, matcher.group("direction")));
			}
		}
	}
	
	@Test
	public void testUseRegex() {
		Pattern pattern = Pattern.compile(CommandParser.USE_REGEX);
		for(String command : Arrays.asList("use", "equip", "wear", "drink", "eat", "activate", "unlock", "unblock")) {
			Matcher matcher = pattern.matcher(command + " thing");
			Assertions.assertTrue(matcher.matches() && "thing".equals(matcher.group("target")), String.format("Expected %s thing to return thing, got %s.", command, matcher.group("target")));
		}
	}
	
	@Test
	public void testUseOnRegex() {
		Pattern pattern = Pattern.compile(CommandParser.USE_ON_REGEX);
		for(String command : Arrays.asList("use thing1 on thing2", "use thing1 with thing2")) {
			Matcher matcher = pattern.matcher(command);
			Assertions.assertTrue(matcher.matches() && "thing1".equals(matcher.group("item")), String.format("Expected %s to return thing1, got %s.", command, matcher.group("item")));
			Assertions.assertTrue("thing2".equals(matcher.group("target")), String.format("Expected %s to return thing2, got %s.", command, matcher.group("target")));
		}
	}
	
	@Test
	public void testTalkRegex() {
		Pattern pattern = Pattern.compile(CommandParser.TALK_REGEX);
		for(String command1 : Arrays.asList("talk", "speak", "converse", "listen")) {
			for(String command2 : Arrays.asList("with", "to")) {
				Matcher matcher = pattern.matcher(command1 + " " + command2 + " thing");
				Assertions.assertTrue(matcher.matches() && "thing".equals(matcher.group("target")), String.format("Expected %s %s thing to return thing, got %s.", command1, command2, matcher.group("target")));
			}
		}
	}
	
	@Test
	public void testTakeRegex() {
		Pattern pattern = Pattern.compile(CommandParser.TAKE_REGEX);
		for(String command : Arrays.asList("take", "pick up", "acquire", "grab", "collect")) {
			Matcher matcher = pattern.matcher(command + " thing");
			Assertions.assertTrue(matcher.matches() && "thing".equals(matcher.group("target")), String.format("Expected %s thing to return thing, got %s.", command, matcher.group("target")));
		}
	}
	
	@Test
	public void testDropRegex() {
		Pattern pattern = Pattern.compile(CommandParser.DROP_REGEX);
		for(String command : Arrays.asList("discard", "toss", "trash", "drop")) {
			Matcher matcher = pattern.matcher(command + " thing");
			Assertions.assertTrue(matcher.matches() && "thing".equals(matcher.group("target")), String.format("Expected %s thing to return thing, got %s.", command, matcher.group("target")));
		}
	}
	
	@Test
	public void testAttackRegex() {
		Pattern pattern = Pattern.compile(CommandParser.ATTACK_REGEX);
		for(String command : Arrays.asList("attack", "kill", "murder", "maim", "hurt", "punch", "injure")) {
			Matcher matcher = pattern.matcher(command + " enemy");
			Assertions.assertTrue(matcher.matches() && "enemy".equals(matcher.group("target")), String.format("Expected %s enemy to return enemy, got %s.", command, matcher.group("target")));
		}
	}
	
	@Test
	public void testSaveRegex() {
		Pattern pattern = Pattern.compile(CommandParser.SAVE_REGEX);
		for(String command : Arrays.asList("save", "save me", "saaave meee")) {
			Assertions.assertTrue(pattern.matcher(command).matches(), String.format("Expected match for %s.", command));
		}		
	}

}

package YouJustLostTheGameAgain.engine;

import java.util.regex.Pattern;

public class CommandParser {
	
	public static final Pattern LOOK_REGEX = Pattern.compile("(?i)look( around)?|survey|search");
	public static final Pattern LOOK_DIRECTION_REGEX = Pattern.compile("(?i)(?:(look |view ))(?<direction>n(orth)?|s(outh)?|e(ast)?|w(est)?|up|down|left|right)");
	public static final Pattern INSPECT_REGEX = Pattern.compile("(?i)(?:(inspect |examine |look at |view ))(?<target>.+)");
	public static final Pattern HELP_REGEX = Pattern.compile("(?i)(?:help ?)(?<command>.*)");
	public static final Pattern RESTART_REGEX = Pattern.compile("(?i)restart|reboot|reload|die|kill (self|me)|(?:commit )?suicide");
	public static final Pattern MOVE_REGEX = Pattern.compile("(?i)(?:walk |move |run |go |head )(?<direction>n(orth)?|s(outh)?|e(ast)?|w(est)?|up|down|left|right)");
	public static final Pattern USE_REGEX = Pattern.compile("(?i)(?:use |equip |wear |drink |eat |activate |unlock |unblock )(?<target>.+)");
	public static final Pattern USE_ON_REGEX = Pattern.compile("(?i)(?:use )(?<item>.+)(?: on | with )(?<target>.+)");
	public static final Pattern TALK_REGEX = Pattern.compile("(?i)(?:talk |speak |converse |listen )(?:with |to )(?<target>.+)");
	public static final Pattern TAKE_REGEX = Pattern.compile("(?i)(?:take |pick up |acquire |grab |collect )(?<target>.+)");
	public static final Pattern DROP_REGEX = Pattern.compile("(?i)(?:discard |toss |trash |drop )(?<target>.+)");
	public static final Pattern ATTACK_REGEX = Pattern.compile("(?i)(?:attack |kill |murder |maim |hurt |punch |injure )(?<target>.+)");
	public static final Pattern SAVE_REGEX = Pattern.compile("(?i)sa+ve( me+)?");
	
	//TODO: Hook into engine when possible.
	public static void parseInput() {
		
	}

}

package YouJustLostTheGameAgain.engine;

public class CommandParser {
	
	public static final String LOOK_REGEX = "(?i)look( around)?|survey|search";
	public static final String LOOK_DIRECTION_REGEX = "(?i)(?:(look |view ))(?<direction>n(orth)?|s(outh)?|e(ast)?|w(est)?|up|down|left|right)";
	public static final String INSPECT_REGEX = "(?i)(?:(inspect |examine |look at |view ))(?<target>.+)";
	public static final String HELP_REGEX = "(?i)(?:help ?)(?<command>.*)";
	public static final String RESTART_REGEX = "(?i)restart|reboot|reload|die|kill (self|me)|(?:commit )?suicide";
	public static final String MOVE_REGEX = "(?i)(?:walk |move |run |go |head )(?<direction>n(orth)?|s(outh)?|e(ast)?|w(est)?|up|down|left|right)";
	public static final String USE_REGEX = "(?i)(?:use |equip |wear |drink |eat |activate |unlock |unblock )(?<target>.+)";
	public static final String USE_ON_REGEX = "(?i)(?:use )(?<item>.+)(?: on | with )(?<target>.+)";
	public static final String TALK_REGEX = "(?i)(?:talk |speak |converse |listen )(?:with |to )(?<target>.+)";
	public static final String TAKE_REGEX = "(?i)(?:take |pick up |acquire |grab |collect )(?<target>.+)";
	public static final String DROP_REGEX = "(?i)(?:discard |toss |trash |drop )(?<target>.+)";
	public static final String ATTACK_REGEX = "(?i)(?:attack |kill |murder |maim |hurt |punch |injure )(?<target>.+)";
	public static final String SAVE_REGEX = "(?i)sa+ve( me+)?";
	
	//TODO: Hook into engine when possible.
	public static void parseInput() {
		
	}

}

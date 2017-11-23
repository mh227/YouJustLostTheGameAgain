package YouJustLostTheGameAgain.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import YouJustLostTheGameAgain.model.GameMap;
import YouJustLostTheGameAgain.xmlparse.XMLTransformer;

public class XMLTransformerTest {
	
	private GameMap map;
	
	@BeforeEach
	public void setup() {
		map = XMLTransformer.transformAndReturn("src/test/resources/testStory.xml");
	}
	
	@Test
	public void testMapNotNull() {
		Assertions.assertTrue(map != null);
	}
	
	@Test
	public void testMapNotEmpty() {
		Assertions.assertFalse(map.equals(new GameMap()));
	}

}

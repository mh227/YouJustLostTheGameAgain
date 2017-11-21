package YouJustLostTheGameAgain.test;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import YouJustLostTheGameAgain.model.GameMap;
import YouJustLostTheGameAgain.xmlparse.XMLTransformer;

public class XMLTransformerTest {
	
	private GameMap map;
	
	@BeforeEach
	public void setup() {
		map = XMLTransformer.transformAndReturn("YouJustLostTheGameAgain/resources/testStory.xml");
	}
	
	@Test
	public void testMapNotNull() {
		Assertions.assertTrue(map != null);
	}

}

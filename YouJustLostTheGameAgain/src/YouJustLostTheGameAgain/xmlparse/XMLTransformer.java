package YouJustLostTheGameAgain.xmlparse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import YouJustLostTheGameAgain.model.GameMap;
import YouJustLostTheGameAgain.model.WeaponItem;

public class XMLTransformer {
	
	protected static Logger log = Logger.getLogger("YouJustLostTheGame");
	
	public XMLTransformer() {}
	
	public static void main(String [] args) {
		GameMap map = new XMLTransformer().transformAndReturn("/story.xml");
		System.out.println(map.getMapIntro());
		System.out.println(((WeaponItem)map.getRooms().get(0).getItems().get(0)).getDamage());
		//new XMLTransformer().generateSchema();
	}
	
	private static String chooseFile(){
		JFileChooser chooser = new JFileChooser();
	    chooser.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       return chooser.getSelectedFile().getAbsolutePath();
	    }
	    else{
	    	JOptionPane.showMessageDialog(null, "You did not choose a file. Reverting to default.");
	    	return "/story.xml";
	    }
	}
	
	public GameMap transformAndReturn(String filepath) {
		GameMap response = null;
		InputStream bufferedReader = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(GameMap.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			SAXParserFactory spf = SAXParserFactory.newInstance();
	        spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
	        spf.setFeature("http://xml.org/sax/features/validation", false);

	        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
	        InputSource inputSource = new InputSource(ClassLoader.class.getResource(filepath).getFile());
	        SAXSource source = new SAXSource(xmlReader, inputSource);
			response = (GameMap) unmarshaller.unmarshal(source, GameMap.class).getValue();
		} catch(Exception e) {
			log.log(Level.SEVERE, "Error:", e);
			System.exit(1);
		} finally {
			try {
				if(bufferedReader != null) {
					bufferedReader.close();
				}
			} catch(IOException e) {
				log.log(Level.SEVERE, "Error:", e);
				System.exit(1);
			}
		}
		return response;
	}
	
	public void generateSchema() {
		try {
			JAXBContext context = JAXBContext.newInstance(GameMap.class);
			SchemaOutputResolver sor = new MySchemaOutputResolver();
			context.generateSchema(sor);
		} catch(JAXBException e) {
			log.log(Level.SEVERE, "Error:", e);
			System.exit(1);
		} catch(IOException e) {
			log.log(Level.SEVERE, "Error:", e);
			System.exit(1);
		}
	}
	
	private InputStream loadXmlFromPath(String filepath) {
		InputStream stream = ClassLoader.class.getResourceAsStream(filepath);
		return stream;
	}

	public class MySchemaOutputResolver extends SchemaOutputResolver {

	    public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
	        File file = new File("resources/story.xsd");
	        StreamResult result = new StreamResult(file);
	        result.setSystemId(file.toURI().toURL().toString().replaceAll("%20", " "));
	        return result;
	    }

	}
}

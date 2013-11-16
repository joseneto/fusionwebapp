/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scaffold.generate;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author neto
 */
public class TemplateUtil {
    
    public static String PACKAGE = "{pkg}";
    public static String UPPER_MODEL= "{umodel}";
    public static String DOWN_MODEL= "{dmodel}";
    public static String PLURAL_DOWN_MODEL= "{pdmodel}";
    public static String GENERATE_TABLE= "{gentable}";
    public static String GENERATE_FORM= "{genform}";
    public static String MODEL_CONVERTES= "{mconverts}";
    public static String REPORT_HEADER1= "{header1}";
    public static String REPORT_HEADER2= "{header2}";
    public static String REPORT_ATTR1= "{attr1}";
    public static String REPORT_ATTR2= "{attr2}";
    public static String TABLE_NAME_PKG= "{tblpackage}";
    public static String TABLE_NAME= "{tblname}";
    

   public void readProperties(String propertFile){
                
      String props = System.getProperty("user.dir") + File.separator + "src"+ File.separator +"main"+ File.separator +"resources" + File.separator + propertFile;
      
      final Properties propsFromFile = new Properties();
      try
      {
         propsFromFile.load(new FileInputStream(props));
      }
      catch (final IOException e)
      {
          e.printStackTrace();
      }
      for (String prop : propsFromFile.stringPropertyNames())
      {
         if (System.getProperty(prop) == null)
         {
             System.setProperty(prop, propsFromFile.getProperty(prop));
         }
      }
    }
   
   public String readSpringXML() throws ParserConfigurationException, SAXException, IOException{
       
       String springPath = System.getProperty("user.dir") + File.separator + "src"+ File.separator +"main"+ File.separator +"webapp"+ File.separator +"WEB-INF"+ File.separator +"spring-mvc-servlet.xml";
       File fXmlFile = new File(springPath);
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
       Document doc = dBuilder.parse(fXmlFile);
       
       NodeList nList = doc.getElementsByTagName("bean");
       
       for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
                        
                        if (eElement.getAttribute("id").equals("propertyConfigurer")){
                           
                           return eElement.getTextContent().trim().replace("classpath:", ""); 
                        }
 		}
	}
       
       return null;
       
   }
   
   
   public String capitalize(String value){
       return Character.toUpperCase(value.charAt(0)) + value.substring(1);
   }
}

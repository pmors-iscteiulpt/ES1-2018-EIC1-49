package ES1_2018_EIC1_49.EIC1_49;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import facebook_api.facebookAPI;


public class CreateXML {
	private File newf = new File("C:\\Users\\Pedro\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\ES1_2018_EIC1_49\\config.xml");
	private App a = new App();
	private facebookAPI fAPI = new facebookAPI();
	public void write(User prob) {
		try {
			
			String filename = prob.getUsername() +".xml";
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();// dBuilder.parse(inputFile);
			Element root = doc.createElement("File");
			doc.appendChild(root);
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();

			XPathExpression expr1 = xpath.compile("/XML/User/@*");
			NodeList n2 = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);

			Element newElement1 = doc.createElement("User");
			System.out.println("element - " + newElement1);
			newElement1.setAttribute("Name", prob.getUsername());
			root.appendChild(newElement1);

			Element newElement2 = doc.createElement("User");
			
			newElement2.setAttribute("Username", prob.getUsername());
			newElement2.setAttribute("Password", prob.getPassword());
			newElement2.setAttribute("Curso", prob.getCurso());
			root.appendChild(newElement2);
			
			/*Element newElement3 = doc.createElement("AccessTokens");
			System.out.println("element - " + newElement3);
			System.out.println(fAPI.getAccessToken());
			System.out.println(a.getAccessToken());
			System.out.println(a.getOAuthConsumerKey());
			
			newElement3.setAttribute("AcessToken facebook",fAPI.getAccessToken() );
			newElement3.setAttribute("AcessToken Twitter", a.getAccessToken());
			newElement3.setAttribute("AcessTokenSecret Twitter", a.getAccessTokenSecret());
			newElement3.setAttribute("AOAuthConsumerKey Twitter", a.getOAuthConsumerKey());
			newElement3.setAttribute("OAuthConsumerSecret Twitter", a.getOAuthConsumerSecret());
			root.appendChild(newElement3);*/
			
			
			System.out.println("\nSave XML document.");
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
			StreamResult result = new StreamResult(newf);
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Node read(String filename) {
		File inputFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("File");
			Node n = nList.item(0);
			;
			return n;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ficheiro nao existe");
			e.printStackTrace();
		}
		return null;
	}


}

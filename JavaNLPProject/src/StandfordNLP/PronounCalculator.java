package StandfordNLP;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * 
 * @author Pradipta
 *
 */
public class PronounCalculator {

	public static void main(String[] args) throws Exception {

		String[] pdfNames = { "luke", "ted", "david", "matthew", "jake", "rick", "josh", "tony", "aaron", "michael",
				"nick", "george", "john", "judith", "tia", "meg", "vicky", "eva", "julie", "rita", "leah", "caroline",
				"cinthia", "ariel", "macy", "lynn", "rebecca", "cinthia", "mara", "amy", "michelle" };

		HashMap<String, Integer> pdfHMap = new HashMap<String, Integer>();

		for (int i = 0; i < pdfNames.length; i++) {
			pdfHMap.put(pdfNames[i], 0);
		}

		File fXmlFile = new File("Mar11_GroupB.xml");    // Mar11_GroupB.xml for
														// testing other
														// conversation
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		// System.out.println("Root element :" +
		// doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("tokens");

		System.out.println("-----------Males and Female Pronoun Calculator based on Names-----------------");

		// System.out.println(nList.getLength());
		HashMap<String, Integer> MAP = new HashMap<String, Integer>();
		for (int i = 0; i < nList.getLength(); i++) // tokens
		{
			Node nNode = nList.item(i);
			NodeList childNodeList = nNode.getChildNodes();
			int count = 0;
			String allNames = null;
			for (int j = 0; j < childNodeList.getLength(); j++) { // token
				Node n1 = childNodeList.item(j);
				if (n1.getNodeName().equals("token")) {
					Element eElement = (Element) n1;
					if (pdfHMap.containsKey(eElement.getElementsByTagName("word").item(0).getTextContent()))
						allNames = eElement.getElementsByTagName("word").item(0).getTextContent();
					String POS = eElement.getElementsByTagName("POS").item(0).getTextContent();
					if (POS.equals("PRP") || POS.equals("PRP$"))
						count++;

				}
			}
			if (allNames != null) {
				int temp = pdfHMap.get(allNames) + count;
				pdfHMap.put(allNames, temp);
			}

		}
		System.out.println(pdfHMap);
	}

}

package main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Options {
	
	private String trialNumber;
	private String trialType;
	private String letterType;
	
	public Options() {
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File("settings.xml"));
			final Element root = document.getDocumentElement();
			
			this.trialNumber = root.getElementsByTagName("round").item(0).getTextContent();
			this.trialType = root.getElementsByTagName("type").item(0).getTextContent();
			this.letterType = root.getElementsByTagName("case").item(0).getTextContent();
			
		} catch (final ParserConfigurationException e) { e.printStackTrace();
		} catch (final SAXException e) { e.printStackTrace();
		} catch (final IOException e) { e.printStackTrace();
		}
	}
	
	public String getTrialNumber() {
		return this.trialNumber;
	}
	
	public String getTrialType() {
		return this.trialType;
	}
	
	public String getLetterType() {
		return this.letterType;
	}
}
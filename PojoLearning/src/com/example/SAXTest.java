package com.example;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SAXTest {
	public static void main(String[] args) {
		String res = GetFileList("");
		System.out.println(res);
		System.out.println("Done");
	}
	
	public static String GetFileList(String fileresponse) {
	    String xml = "/Volumes/Work/TRANSFERs/CodeExamples/TestXml.xml";
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = null;
	    InputSource is = new InputSource();
	    String textToShow = "";
	    StringBuilder resultsofList = new StringBuilder();

	    try {
	        db = dbf.newDocumentBuilder();
	        try {
	            Document doc = db.parse(new File(xml));

	            NodeList replies = doc.getElementsByTagName("Replies");
	            for (int i = 0; i < replies.getLength(); i++) {
	                Element element = (Element) replies.item(i);

	                NodeList inkstatus = element.getElementsByTagName("FileList");
	                for (int i2 = 0; i2 < inkstatus.getLength(); i2++) {
	                    Element element2 = (Element) inkstatus.item(i2);

	                    NodeList inklevel = element2.getElementsByTagName("File");
	                    for (int i4 = 0; i4 < inklevel.getLength(); i4++) {
	                        Element element4 = (Element) inklevel.item(i4);

	                        Element line = (Element) inklevel.item(i4);
	                        if (line == null) {
	                            inklevel = element4.getElementsByTagName("File");
	                            line = (Element) inklevel.item(0);
	                        }

	                        textToShow = line.getTextContent();
	                        resultsofList.append(textToShow+",");
	                    }
	                }
	            }
	        } catch (SAXException e) {
	            // handle SAXException
	        } catch (IOException e) {
	            // handle IOException
	        }
	    } catch (ParserConfigurationException e1) {
	        // handle ParserConfigurationException
	    }
	    return String.valueOf(resultsofList);
	}
}

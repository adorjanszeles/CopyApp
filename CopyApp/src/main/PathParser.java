package main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class PathParser {
    private String source;
    private String destination;

    public void parseXML() throws Exception{
        File xmlFile = new File("config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        Element rootElement = doc.getDocumentElement();
        source = rootElement.getAttribute("source");
        destination = rootElement.getAttribute("destination");
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}

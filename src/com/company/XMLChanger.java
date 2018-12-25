package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class XMLChanger {
    private static String tag = "BIC";
    private static String value = "044030001";

    public static void changeXml(String path) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        String fileFormat = getFileExtension(path);
        if (Objects.requireNonNull(fileFormat).equals(".xml")) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(path);
            NodeList parcel = doc.getElementsByTagName(tag);
            for (int i = 0; i < parcel.getLength(); i++) {
                Node element = parcel.item(i);
                if (element.getNodeName().equals(tag)) {
                    element.getFirstChild().setNodeValue(value);
                }
            }
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        }
    }

    private static String getFileExtension(String file) {
        int index = file.indexOf('.');
        return index == -1 ? null : file.substring(index);
    }
}

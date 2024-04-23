package be.ugent.objprog.ugentopoly.data.readers;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;

public interface XmlReader {
    default Document parseXml(InputStream xmlInputStream) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        return builder.build(xmlInputStream);
    }

    default Element getRootElement(Document document) {
        return document.getRootElement();
    }

    default Element getChildElement(Element parentElement, String childName) {
        return parentElement.getChild(childName);
    }
}

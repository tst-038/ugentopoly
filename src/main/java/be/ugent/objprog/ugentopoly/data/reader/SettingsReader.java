package be.ugent.objprog.ugentopoly.data.reader;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.exception.data.SettingReadException;
import be.ugent.objprog.ugentopoly.model.Settings;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.InputStream;

public class SettingsReader implements XmlReader{
    public Settings readSettings(PropertyReader propertyReader) {
        try (InputStream xmlInputStream = ResourceLoader.loadResource("ugentopoly.xml")) {
            Document document = parseXml(xmlInputStream);
            Element root = getRootElement(document);
            Element settingsElement = getChildElement(root, "settings");

            return parseSettings(settingsElement, propertyReader);
        } catch (IOException | JDOMException e) {
            throw new SettingReadException("Failed to read settings information from XML file", e);
        }
    }

    private Settings parseSettings(Element settingsElement , PropertyReader propertyReader) {
        int balance = Integer.parseInt(settingsElement.getAttributeValue("balance"));
        int start = Integer.parseInt(settingsElement.getAttributeValue("start"));
        Settings settings = new Settings(propertyReader);
        settings.initialize(balance, start);
        return settings;
    }
}
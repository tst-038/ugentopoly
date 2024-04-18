package be.ugent.objprog.ugentopoly.data.readers;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.exceptions.data.SettingReadException;
import be.ugent.objprog.ugentopoly.model.Settings;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SettingsReader {

    private SettingsReader() {
        throw new IllegalStateException("Utility class");
    }

    public static void readSettings() {
        try (InputStream xmlInputStream = ResourceLoader.loadResource("ugentopoly.xml")) {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(xmlInputStream);
            Element root = document.getRootElement();
            Element settingsElement = root.getChild("settings");

            int balance = Integer.parseInt(settingsElement.getAttributeValue("balance"));
            int start = Integer.parseInt(settingsElement.getAttributeValue("start"));

            Settings.getInstance().initialize(balance, start);
        } catch (IOException | JDOMException e) {
            throw new SettingReadException("Failed to read settings information from XML file", e);
        }
    }
}

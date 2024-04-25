package be.ugent.objprog.ugentopoly.data.reader;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.exception.data.PropertyReadException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyReader {

    private final Properties properties;

    public PropertyReader(String resourceName) {
        try (InputStream input = ResourceLoader.loadResource(resourceName)) {
            properties = new Properties();
            properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        }catch (IOException e) {
            throw new PropertyReadException("Failed to read properties file", e);
        }
    }

    public String getTileName(String id) {
        return properties.getProperty("tile." + id);
    }

    public String getTileDescription(String id) {
        return properties.getProperty("tile." + id + ".description");
    }

    public String get(String id) {
        return properties.getProperty(id);
    }
}
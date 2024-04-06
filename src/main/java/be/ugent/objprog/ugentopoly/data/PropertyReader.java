package be.ugent.objprog.ugentopoly.data;

import be.ugent.objprog.ugentopoly.exceptions.data.PropertyReadException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyReader {

    private static final PropertyReader instance;
    private final Properties properties;

    static {
        try {
            InputStream input = ResourceLoader.loadResource("ugentopoly.properties");
            instance = new PropertyReader(input);
        } catch (IOException e) {
            throw new PropertyReadException("Failed to read properties file", e);
        }
    }

    private PropertyReader(InputStream input) throws IOException {
        properties = new Properties();
        properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
    }

    public static PropertyReader getInstance() {
        return instance;
    }

    public String getTileName(String id) {
        return properties.getProperty("tile." + id);
    }

    public String getTileDescription(String id){
        return properties.getProperty("tile." + id + ".description");
    }
    public String get(String id) { return properties.getProperty(id);}
}
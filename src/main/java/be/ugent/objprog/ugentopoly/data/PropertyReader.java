package be.ugent.objprog.ugentopoly.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private final Properties properties;

    public PropertyReader(InputStream input) throws IOException {
        properties = new Properties();
        properties.load(input);
    }

    public String getTileName(String id) {
        return properties.getProperty("tile." + id);
    }
}

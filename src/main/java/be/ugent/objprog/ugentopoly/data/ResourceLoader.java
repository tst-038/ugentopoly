package be.ugent.objprog.ugentopoly.data;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import javafx.scene.image.Image;

import java.io.InputStream;

public class ResourceLoader {

    private ResourceLoader() {
        throw new IllegalStateException("Utility class");
    }

    public static InputStream loadResource(String resourcePath) {
        InputStream resourceStream = Ugentopoly.class.getResourceAsStream(resourcePath);
        if (resourceStream == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }
        return resourceStream;
    }

    public static Image loadImage(String imagePath) {
        InputStream imageStream = loadResource(imagePath);
        return new Image(imageStream);
    }
}
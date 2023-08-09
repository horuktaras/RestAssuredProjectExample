package helpers;

import constants.Property;

import java.util.Objects;

public class PropertyReader {
    public static String get(Property property) {
        String name = System.getProperty(property.getName());
        return Objects.isNull(name) ? property.getDefaultValue() : name;
    }
}
package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties properties;
    static final String FILE_PATH;

    static {
        FILE_PATH = "src/main/resources/food_delivery.properties";
        FileInputStream inputStream;


        try {
            inputStream = new FileInputStream(FILE_PATH);
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static String getPropertiesValue(String key) {
        return properties.getProperty(key);
    }
}

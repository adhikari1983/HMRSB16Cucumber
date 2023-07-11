package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties prop;

    public static Properties readProperties(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this will return the config.properties whole file as a single object
        return prop;
    }

    public static String getPropertyValue(String key) {
        //after getting L19 the config.properties whole file as a single object
        //now we are extracting the key individually, so key -> username/password/url/browser
        return prop.getProperty(key);
    }
}

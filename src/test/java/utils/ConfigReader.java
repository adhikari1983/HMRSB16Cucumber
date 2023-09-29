package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    /**
     The "Properties" class is like a tool in Java that helps you store information. This information is stored as
     pairs, like a word and its definition in a dictionary. Each pair has a name (called a "key") and a description
     (called a "value"), and both the name and description are just words or pieces of text
     */
    static Properties prop;
// readProperties reads the data from config.properties in java form
    public static Properties readProperties(String filePath) {
        try {
            //in order to locate the file path, passing the filepath as input for FileInputStream
            FileInputStream fis = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this will return the config.properties whole file as a single object
        return prop;
    }
/**
    With the help of Properties class, readProperties method reads the properties from config.properties file & returns
    those properties in a single property object(e.g. prop). After that we use getPropertyValue method from that
    property object to extract the single key from config.properties file to get its value*/
    public static String getPropertyValue(String key) {
        //after getting L19 the config.properties whole file as a single object
        //now we are extracting the key individually, so key -> username/password/url/browser
        return prop.getProperty(key);
    }
}
/**
 The purpose of using the Properties class and these methods to read properties from a config.properties file is to
 manage configuration settings for a Java application in an organized and flexible way. Here's a breakdown of the
 purpose:
 1. Centralized Configuration: Storing configuration settings, like database connection details or application
    settings, in a separate properties file (in this case, config.properties) allows you to keep all these settings
    in one place. This makes it easier to manage and update them without changing the code of your Java application.

 2. Flexibility: By using a properties file, you can modify configuration settings without recompiling your Java code.
    This is especially useful when you need to adapt your application to different environments (e.g., development,
    testing, production) or when you want to allow users to customize certain aspects of the application.

 3. Readability: Properties files are simple and human-readable. Anyone, including non-developers, can open the
    config.properties file and understand or modify the settings. This makes it easier to collaborate with others
    and maintain the application.

 4. Consistency: Using a standard class like Properties to read and manage properties ensures consistent handling of
    configuration data across your application. It provides methods to retrieve values based on keys, and it handles
    any data type conversions if necessary.

 In summary, the purpose of using the Properties class and related methods is to create a system for storing and
 retrieving configuration settings in a structured, flexible, and easily maintainable way for Java applications.
 It promotes separation of configuration from code, making your application more adaptable and maintainable.
 Here it defines the true meaning of the framework.
 */
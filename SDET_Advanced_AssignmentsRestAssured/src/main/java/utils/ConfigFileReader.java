package utils;



import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    Properties properties;
    static Logger logger = Logger.getLogger(ConfigFileReader.class);
    static ConfigFileReader configFileReaderInstance = null;
    public static synchronized ConfigFileReader getInstance() {
        if (configFileReaderInstance == null)
            configFileReaderInstance = new ConfigFileReader();

        return configFileReaderInstance;
    }

    public ConfigFileReader() {
        BufferedReader reader;
        String propertyFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\configs\\config.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                logger.info("Properties File Loaded");
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getLog4jPath() {
        String log4jPath = properties.getProperty("log4jPath");
        if (log4jPath != null)
            return log4jPath;
        else throw new RuntimeException("log4jPath not specified in the Configuration.properties file.");
    }
    public String getProperty(String propertyKey) {
        String propertyValue = null;
        try {
            propertyValue = properties.getProperty(propertyKey);
            if (propertyValue != null)
                return propertyValue;
            else
                logger.info("Key not found" + propertyKey);
        }catch(Exception e){
            logger.info("Key not found" + propertyKey);
        }
        return propertyValue;
    }
}


package utils;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    Properties properties;
    String propertyFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\configs\\config.properties";
    private static ConfigFileReader configFileReaderInstance = null;

    public static synchronized ConfigFileReader getInstance() {
        if (configFileReaderInstance == null)
            configFileReaderInstance = new ConfigFileReader();

        return configFileReaderInstance;
    }

    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                Log.info("Properties File Loaded");
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getProperty(String propertyName) {
        String property = properties.getProperty(propertyName);
        if (property != null)
            return property;
        else throw new RuntimeException("property not specified in the config.properties file." +  propertyName);
    }
}
package com.company;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private final String pathToProperties;
    private static Logger logger = LogManager.getLogger(PropertiesReader.class);

    public PropertiesReader(String pathToProperties) {
        this.pathToProperties = pathToProperties;
    }

    public String getProperties(String propertiesKey) {
        FileInputStream fileInputStream;
        Properties prop = new Properties();

        try {
            logger.debug("Load properties");
            fileInputStream = new FileInputStream(pathToProperties);
            prop.load(fileInputStream);
        } catch (IOException e) {
            logger.error("File " + pathToProperties + " not found");
            e.printStackTrace();
        }

        return prop.getProperty(propertiesKey);
    }
}

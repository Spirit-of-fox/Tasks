package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private final String pathToProperties;

    public PropertiesReader(String pathToProperties) {
        this.pathToProperties = pathToProperties;
    }

    public String getProperties(String propertiesKey) {
        FileInputStream fileInputStream;
        Properties prop = new Properties();

        try {
            fileInputStream = new FileInputStream(pathToProperties);
            prop.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Ошибка: файл " + pathToProperties + " не обнаружено");
            e.printStackTrace();
        }

        return prop.getProperty(propertiesKey);
    }
}

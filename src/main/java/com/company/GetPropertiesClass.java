package com.company;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetPropertiesClass {

    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    public static String searchWord;
    public static String numRes;
    public static String browser;

    public static void getPropertie() {
        FileInputStream fileInputStream;
        Properties prop = new Properties();

        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);

            searchWord = prop.getProperty("searchWord");
            numRes = prop.getProperty("NumRes");
            browser = prop.getProperty("browser");

        } catch (IOException e) {
            System.out.println("Ошибка: файл " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }
    }
}

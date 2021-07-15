package com.company;

import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class DPClass {
    private static String searchWord;
    private static String NumRes;


    public DPClass() throws IOException {
        FileReader fr = new FileReader("properties.txt");
        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()) {
            searchWord = scan.nextLine();
            NumRes = scan.nextLine();
        }
        fr.close();
    }

    @DataProvider(name = "test-data")
    public static Object[][] dataProvFunc(Method m) throws IOException {
        DPClass p = new DPClass();
        System.out.println(m.getName());
        switch (m.getName()) {
            case "testOne":
                return new Object[][] {
                        {searchWord}
                };
            case "testTwo":
                return new Object[][] {{searchWord, NumRes}};
        }
        return null;
    }


}

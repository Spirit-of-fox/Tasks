package com.company;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input a number of files: ");
        int numberOfFiles = Integer.parseInt(reader.readLine());
        System.out.print("Input the path to the files: ");
        String userPath  = reader.readLine();
        System.out.print("Input the extension of the files you are looking for: ");
        String fileExtension  = reader.readLine();

        try {
            File dir = new File(userPath);
            File[] lst2 = dir.listFiles(new Filter(fileExtension));

            for (int i = 1; i < lst2.length; i++) {
                Path parentOfFileBefore = Path.of(lst2[i-1].getPath());
                Path parentOfFile = Path.of(lst2[i].getPath());
                BasicFileAttributes attrsOfFileOne = Files.readAttributes(parentOfFileBefore, BasicFileAttributes.class);
                BasicFileAttributes attrsOfFileTwo = Files.readAttributes(parentOfFile, BasicFileAttributes.class);
                if (attrsOfFileTwo.creationTime().toMillis() > attrsOfFileOne.creationTime().toMillis()) {
                    File buf = lst2[i];
                    lst2[i] = lst2[i-1];
                    lst2[i-1] = buf;
                }
            }

            if (numberOfFiles <= lst2.length) {
                System.out.println("Last created file: " + lst2[0]);
                System.out.println("Files with the " + fileExtension
                        + " extension, created within 10 seconds of the last: ");
                for (int i = 1; i < lst2.length; i++) {
                    Path parentOfNewFile = Path.of(lst2[0].getPath());
                    Path parentOfFile = Path.of(lst2[i].getPath());
                    BasicFileAttributes attrsOfFileOne = Files.readAttributes(parentOfNewFile, BasicFileAttributes.class);
                    BasicFileAttributes attrsOfFileTwo = Files.readAttributes(parentOfFile, BasicFileAttributes.class);
                    if ((attrsOfFileOne.creationTime().toMillis() - attrsOfFileTwo.creationTime().toMillis()) <= 10000){
                        System.out.println(lst2[i]);
                    }
                }
            } else {
                System.out.println("The number is too large! There are only " + lst2.length
                                + " files along this path ");
                for (int i = 0; i < lst2.length; i++) {
                    System.out.println(lst2[i]);
                }
            }
        }
        catch (NullPointerException e) {
            System.out.println("Failed to access directory");
        }
    }
}

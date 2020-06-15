package dev.astamur.geekbrains.lessons.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFile {

    public static void main(String[] args) {
        read();
        readWithResources();
    }

    private static void read() {
        InputStream inputStream;
        BufferedReader reader = null;
        try {
            inputStream = ReadFile.class.getClassLoader().getResourceAsStream("text.txt");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                // Do nothing
            }
        }
    }

    private static void readWithResources() {
        try (InputStream inputStream = ReadFile.class.getClassLoader().getResourceAsStream("text.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

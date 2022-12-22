package main;

import java.io.File;

import need.*;

public class Main {
    public static void main(String[] args) {
        Function f = new Function();
        try {
            File file1 = new File("cc.txt");
            File file2 = new File("lala1.txt");
            file2.delete();
            // f.write(file1, file2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

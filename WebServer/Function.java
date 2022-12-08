package need;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Function {
    public String getUrl(String s) {
        if (s.contains("GET") && s.contains("HTTP") && s.contains("favicon.ico") == false) {
            System.out.println(s + "loplkp");
            String valiny[] = s.split("/");
            if (s.split("/")[1].strip().split(" ").length == 2) {
                // System.out.println(s.split("/")[1].strip().split(" ").length + "neineuin");
                return s.split("/")[1].strip().split(" ")[0];
            }
            return "";

        }
        return null;
    }

    public boolean verifyFicher(File f, String fichier) {
        String[] files = new String[f.list().length];
        for (int i = 0; i < f.list().length; i++) {
            String file = "/" + f.list()[i];
            if (file.equalsIgnoreCase(fichier) == true) {
                return true;
            }
        }
        return false;
    }

    public String getUrlClient(ArrayList array, File file) {
        String value = "";
        String myval = "";
        for (int i = 0; i < array.size(); i++) {
            value = (String) array.get(i);
            if (value.contains("GET /") && value.contains("favicon.ico") == false) {
                myval = value.split(" ")[1];
                System.out.println(verifyFicher(file, myval.strip()) + "url");
                if (verifyFicher(file, myval.strip()) == true) {

                    return myval;
                }
            }
        }
        return null;
    }

    public String getHtmlText(File myFile) throws Exception {
        Scanner lire = new Scanner(myFile);
        String line = new String();
        while (lire.hasNextLine()) {
            line = line + lire.nextLine() + "\n";
        }
        return line;
    }
}

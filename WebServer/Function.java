package need;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public boolean verifyFichier(File f, String fichier) {
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
                System.out.println(verifyFichier(file, myval) + "url");
                if (verifyFichier(file, myval.strip()) == true) {

                    return "www" + myval;
                }
                if (myval.equalsIgnoreCase("/")) {
                    System.out.println("okay eh");
                    return "/";
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

    public String getAllFile(File file) {
        String valiny = "<table><tr><td>Voici Tous les fichiers/Dossiers</td><tr> \n";
        for (int i = 0; i < file.list().length; i++) {
            valiny = valiny + "<tr><td><a href=www" + file.list()[i] + ">" + file.list()[i] + "</a></td><tr> \n";
        }
        valiny = valiny + "</table>";
        return valiny;
    }

    public boolean getIfFolder(String directorie) {
        File file = new File("www" + directorie);
        if (file.isDirectory() == true) {
            return true;
        }
        return false;
    }

    public boolean getIfFichier(String directorie) {
        File file = new File("www" + directorie);
        if (file.isDirectory() == true) {
            return true;
        }
        return false;
    }

    public String getHtmlTOPhp(String path) throws Exception {
        Runtime run = Runtime.getRuntime();
        // run.exec("cd php-5.3.25");
        Process process = run.exec("php " + path);

        InputStream stream = process.getInputStream();
        InputStreamReader in = new InputStreamReader(stream);
        BufferedReader buffered = new BufferedReader(in);
        String answer = "";
        String myread = "";
        while ((myread = buffered.readLine()) != null) {
            answer = answer + myread + "\n";
        }
        stream.close();
        in.close();
        buffered.close();
        return answer;
    }

    public boolean getExtension(String url) {
        if (url.contains("php")) {
            return true;
        }
        return false;
    }
}

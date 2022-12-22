package need;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Function {
   

    public boolean verifyFichier(File f, String fichier) {
        String[] files = new String[f.list().length];
        for (int i = 0; i < f.list().length; i++) {
            String file = f.list()[i];
            if (file.equalsIgnoreCase(fichier) == true) {
                return true;
            }
        }
        return false;
    }

    public String getUrlClient(ArrayList lists) {
        String url = "";
        for (int i = 0; i < lists.size(); i++) {
            String valuable = (String) lists.get(i);
            if (valuable.contains("GET") || valuable.contains("POST"))
                url = valuable;
            return url;
        }
        return "";
    }

    public String getvaleurPost(BufferedReader in) throws Exception {
        String str = "";
        String answer = "";
        int length = 0;
        while ((str = in.readLine()) != null) {
            if (str.contains("Content-Length")) {
                length = Integer.valueOf(str.split(":")[1].trim());
            }
            if (str.isEmpty()) {
                break;
            }
        }
        if (length > 0) {
            char[] myValue = new char[length];
            in.read(myValue, 0, length);
            answer = new String(myValue);
        }
        return answer;
    }

    public String getUrlEnd(ArrayList array) {
        String url = this.getUrlClient(array);
        if (url.equals("") == false) {
            String urlClient = "www" + url.split(" ")[1];
            if (urlClient.contains("?")) {
                System.out.println(urlClient.split("\\?")[0] + "url get");
                return urlClient.split("\\?")[0];

            } else {
                return urlClient;
            }
        } else {
            return "";
        }
    }

    public String getVariable(String url, BufferedReader in) throws Exception {
        String answer = "";
        if (url.equals("") == false) {
            // String urlClient = "www" + url.split(" ")[1];
            if (url.contains("?")) {
                answer = url.split("\\?")[1];
            }
        }
        return answer;
    }

    public String getHtmlText(File myFile) throws Exception {
        Scanner lire = new Scanner(myFile);
        String line = new String();
        while (lire.hasNextLine()) {
            line = line + lire.nextLine() + "\n";
        }
        return line;
    }

    public String getPort() throws Exception {
        File file = new File("Config.ini");
        Scanner scan = new Scanner(file);
        String line = new String();
        while (scan.hasNextLine()) {
            line = line + scan.nextLine();
        }
        String port = line.split(",")[0];
        return port.split(":")[1];
    }

    public String getAllFile(File file) {
        String valiny = "<table><tr><td>Voici Tous les fichiers/Dossiers</td><tr> \n";
        for (int i = 0; i < file.list().length; i++) {
            valiny = valiny + "<tr><td><a href=" + file.list()[i] + ">" + file.list()[i] + "</a></td><tr> \n";
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

    public void write(File file1, File file2, File file3) throws Exception { // file1 vers file2
        String myString = getHtmlText(file1);
        myString = myString + getHtmlText(file2);
        PrintWriter print = new PrintWriter(file3);
        print.write(myString);
        print.close();
    }

    public String getScriptAndFile(String f) throws Exception {
        File variable = new File("Variable.php");
        File myFile = new File(f);
        File temp = new File("FileTemp.php");
        write(variable, myFile, temp);
        return temp.getPath();
    }

    public String getHtmlTOPhp(String path, String variable) throws Exception {
        // System.out.println(variable + "variable");
        Runtime run = Runtime.getRuntime();
        String pathIlaina = getScriptAndFile(path);
        // run.exec("cd php-5.3.25");
        System.out.println("php-cgi " + path + " " + variable + "variable ok");
        Process process;
        if (variable != null) {
            process = run.exec("php-cgi " + pathIlaina + " " + variable);
        } else {
            process = run.exec("php-cgi " + path);
        }
        InputStream stream = process.getInputStream();
        InputStreamReader in = new InputStreamReader(stream);
        BufferedReader buffered = new BufferedReader(in);
        String answer = "";
        String myread = "";
        while ((myread = buffered.readLine()) != null) {
            if (myread.equals("")) {
                while ((myread = buffered.readLine()) != null) {
                    answer = answer + myread + "\n";
                }
            }
        }
        File file = new File(pathIlaina);
        file.delete();
        stream.close();
        in.close();
        buffered.close();
        return answer;
    }

    public boolean getExtensionPhp(String url) {
        if (url.contains("php")) {
            return true;
        }
        return false;
    }
}

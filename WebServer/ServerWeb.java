package webserver;

import java.net.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.lang.*;
import java.io.*;
import java.util.*;
import need.*;

public class ServerWeb {
    public static void main(String[] args) throws Exception {

        // création de la socket
        // System.err.println("Serveur lancé sur le port : " + port);
        Socket clientSocket = new Socket();
        String s = "";
        String a = "";
        String ok = "";

        Function f = new Function();

        BufferedReader in = null;

        PrintWriter out = null;

        try {
            int port = Integer.valueOf(f.getPort());
            ServerSocket serverSocket = new ServerSocket(port);
            System.err.println("Serveur lancé sur le port : " + port);
            // repeatedly wait for connections, and proces
            while (true) {

                // on reste bloqué sur l'attente d'une demande client
                clientSocket = serverSocket.accept();
                System.err.println("Nouveau client connecté");

                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(clientSocket.getOutputStream())));

                // on ouvre un flux de converation

                ArrayList list = new ArrayList<>();
                String varPost = "";
                int length = 0;
                System.out.println(s);

                while ((s = in.readLine()) != null) { // maka ny requete avy any am client
                    list.add(s); // requete ampisirina ao anaty arrayList
                    if (s.contains("Content-Length")) {
                        length = Integer.valueOf(s.split(":")[1].trim());
                    }
                    if (s.isEmpty()) {
                        break;
                    }
                }
                if (length > 0) {
                    char[] myValue = new char[length]; // maka ilay variable
                    in.read(myValue, 0, length);
                    varPost = new String(myValue);
                }

                String status = "HTTP/1.0 200 OK\r\n";
                String date = "Date: " + String.valueOf(LocalDateTime.now())
                        + "\r\n";
                String line = new String();
                String contentLength = "";
                String contentType = "";
                String lastModified = "Last-modified:";

                String path = f.getUrlClient(list);
                String variable = "";
                String url = "";

                if (path.equals("") == false) {
                    if (path.contains("POST")) {
                        System.out.println("oui");
                        if (varPost.equals("") == false) {
                            url = f.getUrlEnd(list);
                            path = path + "?" + varPost + " HTTP/1.1";
                            System.out.println(path + "okok");
                        }
                    }
                    if (path.contains("GET")) {
                        url = f.getUrlEnd(list);
                    }
                }

                variable = f.getVariable(path, in);
                File f1 = new File("www");
                String c = f.getAllFile(f1);
                System.out.println(url + "url");

                if (url.contains("favicon.ico") == false) {
                    if (url.equals("www/") == false) {
                        if (f.verifyFichier(f1, url.split("/")[1]) == true) {
                            File fileConcerned = new File("www/" + url.split("/")[1]);
                            lastModified = lastModified + String.valueOf(fileConcerned.lastModified()) + "\r\n";
                            System.out.println("www/" + url.split("/")[1] + "last");
                            if (f.getExtensionPhp(url) == true) {// raha php
                                line = f.getHtmlTOPhp(url.split("\\?")[0], variable);
                                contentLength = "Content-Length:" + String.valueOf(line.length()) + "\r\n";
                                contentType = "Content-Type:text/html\r\n";
                            } else { // raja html
                                File myFile = new File(url);
                                line = f.getHtmlText(myFile);
                                contentLength = "Content-Length:" + String.valueOf(line.length()) + "\r\n";
                                contentType = "Content-Type:text/html\r\n";
                            }

                        } else {
                            line = "Fichier ou dossier introuvable \n";
                            line = line + "Error 401";
                            status = "HTTP/1.0 401 Error\r\n";
                            contentLength = "Content-Length" + String.valueOf(0);
                            contentType = "Content-Type:None\r\n";
                            lastModified = lastModified + "None\r\n";
                        }
                    } else {
                        line = f.getAllFile(f1);
                        contentLength = "Content-Length:" + String.valueOf(line.length()) + "\r\n";
                        contentType = "Content-Type:None\r\n";
                        File fileConcerned = new File("www");
                        lastModified = lastModified + String.valueOf(fileConcerned.lastModified()) + "\r\n";
                    }
                }

                clientSocket.shutdownInput();
                out.write(status);
                out.write(date);
                out.write("Server: Apache/0.8.4\r\n");
                out.write(contentType);
                out.write(contentLength);
                out.write("Expires:None\r\n");
                out.write(lastModified);
                out.write("\r\n");
                out.write(line);
                out.flush();
                clientSocket.shutdownOutput();

            }

        } catch (

        Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            out.close();
            in.close();
            clientSocket.close();
        }

        // Thread.sleep(60);
    }
}

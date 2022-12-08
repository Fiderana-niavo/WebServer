package webserver;

import java.net.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import need.*;

public class ServerWeb {
    public static void main(String[] args) throws Exception {

        // création de la socket
        int port = 1989;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Serveur lancé sur le port : " + port);
        Socket clientSocket = new Socket();
        try {
            // repeatedly wait for connections, and proces
            while (true) {

                // on reste bloqué sur l'attente d'une demande client
                clientSocket = serverSocket.accept();
                System.err.println("Nouveau client connecté");

                // on ouvre un flux de converation

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(clientSocket.getOutputStream())));

                String s = "";
                String a = "";
                String ok = "";
                Function f = new Function();
                ArrayList list = new ArrayList<>();
                while ((s = in.readLine()) != null) {
                    list.add(s);
                    if (s.equalsIgnoreCase("")) {
                        break;
                    }
                }
                File f1 = new File("www");
                String line = new String();
                System.out.println(f.getUrlClient(list, f1) + "urlato");
                File myFile = new File("www", f.getUrlClient(list, f1));
                if (f.getUrlClient(list, myFile) == null) {
                    line = "Fichier ou dossier introuvable";
                } else {
                    line = f.getHtmlText(myFile);
                }

                out.write("HTTP/1.0 200 OK\r\n");
                out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
                out.write("Server: Apache/0.8.4\r\n");
                out.write("Content-Type: text/html\r\n");
                out.write("Content-Length: 59\r\n");
                out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
                out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
                out.write("\r\n");
                out.write(line);
                out.flush();
            }
            // clientSocket.shutdownOutput();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            clientSocket.close();
        }

        // Thread.sleep(60);
    }
}

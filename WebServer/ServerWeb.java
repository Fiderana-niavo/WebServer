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

        // repeatedly wait for connections, and process
        while (true) {

            // on reste bloqué sur l'attente d'une demande client
            Socket clientSocket = serverSocket.accept();
            System.err.println("Nouveau client connecté");

            // on ouvre un flux de converation

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(clientSocket.getOutputStream())),
                    true);
            // out.write("ojo");

            // PrintStream out = new PrintStream(clientSocket.getOutputStream());
            // chaque fois qu'une donnée est lue sur le réseau on la renvoi sur le flux
            // d'écriture.
            // la donnée lue est donc retournée exactement au même client.
            String s = "";
            String a = "";
            String ok = "";
            /*
             * while ((s = in.readLine()) != null) {
             * Function f = new Function();
             * a = f.getUrl(s);
             * System.out.println(f.getUrl(s) + "url");
             */
            // break;
            /*
             * System.out.println(a + "");
             * File myFile = new File("E:/L1/java/Exo/L2/Mr Naina/WebServer/www", a);
             * BufferedReader lire = new BufferedReader(new InputStreamReader(new
             * FileInputStream(myFile)));
             * while (lire.readLine() != null) {
             * String valiny = lire.readLine();
             * System.out.println(valiny);
             * out.write(valiny);
             * }
             */
            /*
             * System.out.println();
             * break;
             * 
             * }
             */
            // System.out.println(a + "okok");
            File myFile = new File("E:/L1/java/Exo/L2/Mr Naina/WebServer/www", "MyExemple.txt");
            FileInputStream input = new FileInputStream(myFile);
            BufferedInputStream buffer = new BufferedInputStream(input);
            DataInputStream data = new DataInputStream(buffer);
            BufferedReader lire = new BufferedReader(new InputStreamReader(new FileInputStream(myFile)));

            out.write("HTTP/1.0 200 OK\r\n");
            out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
            out.write("Server: Apache/0.8.4\r\n");
            out.write("Content-Type: text/html\r\n");
            out.write("Content-Length: 59\r\n");
            out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
            out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
            out.write("\r\n");
            while (lire.readLine() != null) {
                System.out.println(lire.readLine());
                out.write(lire.readLine());
            }
            /*
             * out.write("<TITLE>Exemple</TITLE>");
             * out.write("<P>Ceci est une page d'exemple.</P>");
             * out.write("<P>Foko efa lasany any eh</P>");
             * 
             * out.write("HTTP/1.0 200 OK\r\n");
             * // Header...
             * out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
             * out.write("<TITLE>Hello!</TITLE>");
             */
            /*
             * BufferedReader inp = new BufferedReader(
             * new InputStreamReader(clientSocket.getInputStream(), "8859_1"));
             * String request = in.readLine();
             * 
             * try (Socket server = serverSocket.accept()) {
             * 
             * String httpResponse2 = "HTTP/1.1 200 OK\r\n\r\n";
             * if (a != null) {
             * if (a.equals("") == false) {
             * ok = a;
             * httpResponse2 = httpResponse2 + "ok";
             * System.out.println(httpResponse2 + "ok");
             * server.getOutputStream().write(httpResponse2.getBytes("UTF-8"));
             * break;
             * }
             * }
             */
            /*
             * System.out.println(request);
             * File fi = new File("www");
             * Date today = new Date();
             * String[] files = new String[fi.list().length];
             * for (int i = 0; i < fi.list().length; i++) {
             * files[i] = fi.list()[i];
             * }
             * String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + fi.list()[0];
             * for (int j = 1; j < fi.list().length; j++) {
             * if (files[j].equalsIgnoreCase(a)) {
             * httpResponse2 = httpResponse2 + a;
             * } else {
             * System.out.println("non");
             * }
             * httpResponse = httpResponse + "\n" + files[j];
             * }
             */
            // out.write("<P>Foko efa lasany any eh</P>");

            // on ferme les flux.
            // System.err.println("Connexion avec le client terminée");
            out.flush();
            data.close();
            out.close();
            in.close();
            clientSocket.close();
        }
    }
}
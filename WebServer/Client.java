package tests;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Test_client
 */
public class Client {
    /*
     * > GET / HTTP/1.1
     * > Host: localhost:8090
     * > User-Agent: insomnia/2022.4.2
     * > x-random: hellooooo world
     * > Accept: *
     */
    public String getHtml(String path) throws UnknownHostException, IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8080);
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        out.println("GET / HTTP/1.1");
        out.println("Host: localhost:8080 ");
        out.println("User-Agent: wagadougou/2022.0.0");
        out.println();
        out.flush();
        clientSocket.shutdownOutput();
        BufferedReader azo = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String retour = "";
        for (String object : azo.lines().toList()) {
            retour = retour + object;
        }
        clientSocket.close();
        return retour;
    }
}

package retoSexante;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 12345);
            PrintWriter out = new PrintWriter(client.getOutputStream(), false);
            Scanner in = new Scanner(client.getInputStream());

            out.write(client.getLocalPort() + "\r\n");
            out.flush();
            int newport = Integer.parseInt(in.nextLine());

            Socket newclient = new Socket("localhost", newport);
            PrintWriter out2 = new PrintWriter(newclient.getOutputStream(), false);
            Scanner in2 = new Scanner(newclient.getInputStream());

            out2.write(in.nextLine() + "\r\n");
            out2.flush();

            String messageMe = "Nigger", messageServer;
            do {
                // Enviamos mensaje por el cliente 1
                out.write(messageMe + "\r\n");
                out.flush();

                // Recibimos por cliente 2
                messageServer = in2.nextLine();

                if (messageMe.equals(messageServer)) { // Mensajes iguales
                    out.write("200\r\n"); // Enviamos '200' por cliente 1
                    out.flush();
                    break;
                } else { // Mensajes no iguales
                    out.write("400\r\n"); // Enviamos '400' por cliente 1
                    out.flush();
                    messageMe = messageServer; // Cambia mensaje de cliente por el del server
                }
            } while (true);

            // Cerrar todo
            out.close();
            in.close();
            client.close();
            out2.close();
            in2.close();
            newclient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package practica5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8080);
            System.out.println("SERVER SOCKET: IP= " + ss.getInetAddress() + " PUERTO = " + ss.getLocalPort());
            while (true) {
                Socket s = ss.accept();
                System.out.println("- Client connected");
                Scanner sc = new Scanner(s.getInputStream());
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                pw.println("HTTP/1.0 200 OK\r\n" +
                        "Content-Type: text/plain\r\n\r\n ");
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (line.isEmpty()) break;
                    pw.println(line + "\r\n");
                    pw.flush();
                } System.out.println("- HTTP REQUEST RESENT\n- Client exiting");
                sc.close();
                pw.close();
                s.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
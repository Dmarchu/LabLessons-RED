package practica5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(7777);
            System.out.println("SERVER SOCKET: IP= 0.0.0.0 PUERTO = " + ss.getLocalPort());
            while (true) {
                Socket s = ss.accept();
                System.out.println("- Client connected");
                System.out.printf("SOCKET: IP= %s PORT= %d%n", s.getInetAddress(), s.getPort());
                Scanner sc = new Scanner(s.getInputStream());
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                pw.println("HTTP/1.0 200 OK\r\n" +
                        "Content-Type: text/plain\r\n\r\n ");
                pw.printf(sc.nextLine() + "\r\n");
                pw.flush();
                s.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
package practica5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(7777);
            while (true) {
                Socket s = ss.accept();
                System.out.println("Se ha conectado un cliente al servidor");
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

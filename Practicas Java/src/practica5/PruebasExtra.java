package practica5;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class PruebasExtra {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8080);
            System.out.println("SERVER SOCKET: IP= " + ss.getInetAddress() + " PUERTO = " + ss.getLocalPort());
            while (true) {
                Socket s = ss.accept();
                System.out.println("- Client connected");
                Scanner sc = new Scanner(s.getInputStream());
                Scanner web = new Scanner(new File("C:\\Users\\david\\Desktop\\Poli[Archivos]\\2º IINF (2024-2025)\\1er Cuatrimestre\\REDES\\src\\practica5\\index.html"));
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                pw.println("HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Connection: close\r\n\r\n");
                pw.flush();
                while (web.hasNextLine()) {
                    String line = web.nextLine();
                    pw.println(line);
                    pw.flush();
                } System.out.println("- Client exiting");
                sc.close();
                pw.close();
                s.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
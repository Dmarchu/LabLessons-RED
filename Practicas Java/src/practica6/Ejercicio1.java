package practica6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {
        int i = 0;
        ServerSocket ss = new ServerSocket(7777);
        while (true) {
            Socket s = ss.accept();
            Hilos hilo = new Hilos(s);
            hilo.setName("Hilo " + ++i);
            hilo.start();
        }
    }
}

class Hilos extends Thread {
    public Socket cliente;

    public Hilos(Socket cliente) {
        this.cliente = cliente;
    }

    public void run() {
        try {
            System.out.println(getName());
            Scanner sc = new Scanner(cliente.getInputStream());
            PrintWriter pw = new PrintWriter(cliente.getOutputStream());
            while (sc.hasNextLine()) {
                String eco = sc.nextLine();
                if (eco.equalsIgnoreCase("FIN")) {
                    System.out.println("CLIENTE" + getName().substring(4) + " EXITING");
                    cliente.close();
                    break;
                } else {
                    pw.printf(eco + "\r\n");
                    pw.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

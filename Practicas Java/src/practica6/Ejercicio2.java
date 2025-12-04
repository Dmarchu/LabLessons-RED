package practica6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Chat {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("zoltar.redes.upv.es", 7780);
        HiloChat server = new HiloChat(s);
        HiloTu tu = new HiloTu(s);
        server.start();
        tu.start();
    }
}

class HiloTu extends Thread {
    public Socket cliente;

    public HiloTu(Socket cliente) {
        this.cliente = cliente;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(cliente.getOutputStream());
            Scanner kbd = new Scanner(System.in);
            while (kbd.hasNextLine()) {
                out.print(kbd.nextLine() + "\r\n");
                out.flush();
            } cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class HiloChat extends Thread {
    public Socket cliente;

    public HiloChat(Socket cliente) {
        this.cliente = cliente;
    }

    public void run() {
        try {
            Scanner in = new Scanner(cliente.getInputStream());
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


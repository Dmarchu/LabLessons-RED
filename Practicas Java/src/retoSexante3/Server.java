package retoSexante3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {
    public static ArrayList<String> mem = new ArrayList<>();

    public static void main(String[] args) {
        int i = 0;
        try {
            Scanner filesc = new Scanner(new File("C:\\Users\\david\\Desktop\\Poli[Archivos]\\2º IINF (2024-2025)\\1er Cuatrimestre\\RED\\src\\retoSexante3\\refranero.txt"));
            while (filesc.hasNextLine()) {
                String temp = filesc.nextLine();
                mem.add(temp);
            }
            filesc.close();

            ServerSocket ss = new ServerSocket(5000);
            while (true) {
                Socket s = ss.accept();
                Hilo handling = new Hilo(s);
                handling.setName("Cliente " + ++i);
                handling.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Hilo extends Thread {
        Socket s;

        public Hilo(Socket s) {
            this.s = s;
        }

        public void run() {
            try {
                System.out.println(getName() + " Conectado.");
                Scanner in = new Scanner(s.getInputStream());
                PrintWriter out = new PrintWriter(s.getOutputStream());

                out.write("Bienvenido: Indique el número de refrán: \r\n");
                out.flush();

                int sel = in.nextInt();
                System.out.println(sel);

                if (sel < 0 || sel > mem.size()) out.write("Número fuera de rango \r\n");
                else out.write(mem.get(sel) + " \r\n");
                System.out.println(mem.get(sel));

                out.flush();
                out.flush();

                in.close();
                out.close();
                s.close();
            } catch (NoSuchElementException | IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(getName() + " Terminado.");
        }
    }
}

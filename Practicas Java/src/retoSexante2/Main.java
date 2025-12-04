package retoSexante2;

import java.io.IOException;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            int i = 0;
            while (true) {
                Socket socket = serverSocket.accept();
                Hilo hilo = new Hilo(socket);
                hilo.setName("Cliente " + (++i));
                hilo.start();
                System.out.println(hilo.getName() + ": Connection settled.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Hilo extends Thread {
        public Socket socket;

        public Hilo(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                Scanner input = new Scanner(socket.getInputStream());
                int UDPport = input.nextInt();
                InetAddress ip = InetAddress.getByName("localhost");
                System.out.println(this.getName() + ": UDP Socket bound to port " + UDPport);
                DatagramSocket ds = new DatagramSocket(UDPport);
                while (true) {
                    String msg = input.nextLine();
                    System.out.println(this.getName() + ": Sending message [" + msg + "]");
                    ds.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length, ip, UDPport));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchElementException e) {
                System.out.println(this.getName() + ": Connection closed.");
            }
        }
    }
}

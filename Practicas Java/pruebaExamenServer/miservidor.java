import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class miservidor {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(12345);
            int clientes = 0;
            while (true) {
                Socket cliente = server.accept();
                Hilos hilo = new Hilos(cliente);
                hilo.setName("Hilo " + ++clientes);
                System.out.println("Cliente " + clientes + " conectado.");
                hilo.start();
            }
        } catch (IOException ioe) {
            System.out.println("Hubo un problema en la gestión de clientes.");
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
            Scanner receive = new Scanner(cliente.getInputStream());
            PrintWriter send = new PrintWriter(cliente.getOutputStream(), false);

            int port = Integer.parseInt(receive.nextLine());
            System.out.println("Gestionando puerto " + port + " del cliente " + getName() + ".");

            DatagramSocket newClient = new DatagramSocket(port);
            while (receive.hasNext()) {
                String message = receive.nextLine();
                DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
                try {
                    dp.setData(message.getBytes());
                    dp.setLength(message.getBytes().length);
                    newClient.send(dp);
                } catch (SocketTimeoutException ste) {
                    System.out.println("Timed out");
                }
            }

            receive.close();
            send.close();
            newClient.close();
        } catch (IOException ioe) {
            System.out.println("Hubo un problema en un hilo de ejecución: " + ioe.getMessage());
        } catch (NoSuchElementException nse) {
            System.out.println("Cliente \"" + getName() + "\" cerrado.");
        }
    }
}

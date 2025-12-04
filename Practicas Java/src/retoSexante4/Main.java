package retoSexante4;

import java.io.IOException;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        try {
            int port = 54321;
            InetAddress ip = InetAddress.getByName("localhost");
            DatagramSocket ds = new DatagramSocket(port); //Declaración
            while (true) {
                DatagramPacket dp = new DatagramPacket(new byte[1000], 1000);
                ds.receive(dp); // Recibimos el hash
                System.out.println("+OK Receiving hash");
                port = dp.getPort();

                System.out.println("- Echoing hash" + dp.getAddress() + ":" + dp.getPort());
                ds.send(dp); // Devolvemos el hash

                byte[] DNI = "21798680Q\r\n".getBytes();
                System.out.println("- Sending DNI");
                DatagramPacket dp2 = new DatagramPacket(DNI, DNI.length, ip, port);
                ds.send(dp2); // Mandamos el DNI

                byte[] name = "David Martínez Alonso\r\n\n".getBytes();
                System.out.println("- Sending name");
                DatagramPacket dp3 = new DatagramPacket(name, name.length, ip, port);
                ds.send(dp3); // Mandamos nombre y apellidos

                System.out.println("+OK Finishing"); // Info y separadores
                System.out.println("======================================");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
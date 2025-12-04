package practica7;

import java.io.IOException;
import java.net.*;

public class Ejercicio3 {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        int port = 7777;
        String name = "David Martínez Alonso";
        DatagramPacket dp = new DatagramPacket(name.getBytes(), name.getBytes().length, ip, port);
        ds.send(dp);
        ds.close();
    }
}

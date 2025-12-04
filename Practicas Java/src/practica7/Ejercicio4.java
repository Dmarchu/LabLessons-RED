package practica7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Ejercicio4 {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        int port = 7777;
        String name = "Nigga moo";
        DatagramPacket dp = new DatagramPacket(name.getBytes(), name.getBytes().length, ip, port);
        ds.send(dp);
        ds.receive(dp);
        System.out.println(new String(dp.getData(), 0, dp.getLength()));
        ds.close();
    }
}

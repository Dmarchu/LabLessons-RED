package practica7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Ejercicio5 {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(7777);
        while (true) {
            DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
            ds.receive(dp);
            String date = new Date().toString() + "\r\n";
            dp.setData(date.getBytes());
            dp.setLength((date.getBytes()).length);
            ds.send(dp);
        }
    }
}

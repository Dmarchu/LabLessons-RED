package practica7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.util.Date;

public class Ejercicio6 {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(7777);
        ds.setSoTimeout(15000);
        while (true) {
            DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
            try {
                ds.receive(dp);
                String date = new Date() + "\r\n";
                dp.setData(date.getBytes());
                dp.setLength((date.getBytes()).length);
                ds.send(dp);
            } catch (SocketTimeoutException e) {
                System.out.println("Tiempo de espera agotado");
            }
        }
    }
}

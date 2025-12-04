 
package practica7;

import java.io.IOException;
import java.net.*;

public class Ejercicio3 {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        String name = args[0] + "\n";
        DatagramPacket dp = new DatagramPacket(name.getBytes(), name.getBytes().length, ip, 7777);
        ds.send(dp);
        ds.close();
    }
}

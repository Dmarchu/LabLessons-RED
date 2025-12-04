package practica7;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Ejercicio2 {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        DatagramSocket d = new DatagramSocket();
        System.out.println(d.getLocalPort());
    }
}

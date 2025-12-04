package practica7;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ejercicio1 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress dir = InetAddress.getByName("www.upv.es");
        System.out.println(dir.toString());
    }
}

package practica1;

import java.io.IOException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP9 {
    public static void main(String[] args) throws IOException {
        try {
            Socket UPV = new Socket("www.upv.es", 80);
            System.out.println("Conexión establecida!");
            System.out.println(UPV.getPort());
            System.out.println(UPV.getInetAddress());
            System.out.println(UPV.getLocalPort()); //Cambia localPort (no está fijado, lo elige el OS)
            System.out.println(UPV.getLocalAddress());
            UPV.close();
        } catch (UnknownHostException e) {
            System.out.println("Nombre de servidor desconocido!\n" + e);
        } catch (NoRouteToHostException e) {
            System.out.println("No es posible realizar conexión!\n" + e);
        }
    }
}
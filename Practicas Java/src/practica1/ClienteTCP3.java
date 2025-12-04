package practica1;

import java.io.IOException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP3 {
    public static void main(String[] args) throws IOException {
        try {
            Socket UPV = new Socket("www.upv.es", 81);
            System.out.println("Conexión establecida!");
            UPV.close();
        } catch (UnknownHostException e) {
            System.out.println("Nombre de servidor desconocido!\n" + e);
        } catch (NoRouteToHostException e) {
            System.out.println("No es posible realizar conexión!\n" + e);
        }
    }
}
package practica1;

import java.io.IOException;
import java.net.Socket;

public class ClienteTCP1 {
    public static void main(String[] args) throws IOException {
        Socket UPV = new Socket("www.upv.es", 80);
        UPV.close();
    }
}
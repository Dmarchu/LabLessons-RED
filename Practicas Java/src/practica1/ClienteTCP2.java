package practica1;

import java.io.IOException;
import java.net.Socket;

public class ClienteTCP2 {
    public static void main(String[] args) throws IOException {
        Socket UPV = new Socket("www.upv.es", 81);
        UPV.close();
    }
}
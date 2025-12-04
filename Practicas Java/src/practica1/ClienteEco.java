package practica1;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEco {
    public static void main(String[] args) {
        try {
            Socket upv = new Socket("zoltar.redes.upv.es", 7);
            PrintWriter pw = new PrintWriter(upv.getOutputStream(), false); //Si true printea el mensaje enviado
            Scanner sc = new Scanner(upv.getInputStream());
            pw.println("¡¡Hola, Mundo!!");
            System.out.println(sc.nextLine());
            sc.close();
            pw.close();
            upv.close();
        } catch (Exception e) {
            System.out.println("Algo salió mal!\n" + e);
        }
    }
}

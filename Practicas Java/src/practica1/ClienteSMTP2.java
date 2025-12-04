package practica1;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSMTP2 {
    public static void main(String[] args) {
        try {
            Socket con = new Socket("smtp.upv.es", 25);
            Scanner sc = new Scanner(con.getInputStream());
            System.out.print(sc.nextLine() + "\r\n");
            PrintWriter pw = new PrintWriter(con.getOutputStream(), true);
            pw.print("HELLO rdcXX.redes.upv.es\r\n");
            System.out.print(sc.nextLine() + "\r\n");
            sc.close();
            pw.close();
            con.close();
        } catch (Exception e) {
            System.out.print("Algo salió mal!\r\n" + e + "\r\n");
        }
    }
}

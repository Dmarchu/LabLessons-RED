package practica1;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSMTP1 {
    public static void main(String[] args) {
        try {
            Socket con = new Socket("smtp.upv.es", 25);
            Scanner sc = new Scanner(con.getInputStream());
            System.out.println(sc.nextLine());
            PrintWriter pw = new PrintWriter(con.getOutputStream(), true);
            pw.println("HELLO rdcXX.redes.upv.es");
            System.out.println(sc.nextLine());
            sc.close();
            pw.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Algo salió mal!\n" + e);
        }
    }
}

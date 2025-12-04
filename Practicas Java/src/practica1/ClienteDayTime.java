import java.net.Socket;
import java.util.Scanner;

public class ClienteDayTime {
    public static void main(String[] args) {
        try {
            Socket upv = new Socket("zoltar.redes.upv.es", 13);
            Scanner sc = new Scanner(upv.getInputStream());
            System.out.println(sc.nextLine());
            sc.close();
            upv.close();
        } catch (Exception e) {
            System.out.println("Algo salió mal!");
        }
    }
}

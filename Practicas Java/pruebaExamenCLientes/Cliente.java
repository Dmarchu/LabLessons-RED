import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket clt1 = new Socket("localhost", 12345);
            PrintWriter snd1 = new PrintWriter(clt1.getOutputStream(), false);
            Scanner rcv1 = new Scanner(clt1.getInputStream());

            snd1.write(clt1.getLocalPort() + "\r\n");
            snd1.flush();

            int newPort = Integer.parseInt(rcv1.nextLine());
            Socket clt2 = new Socket("localhost", newPort);
            PrintWriter snd2 = new PrintWriter(clt2.getOutputStream(), false);
            Scanner rcv2 = new Scanner(clt2.getInputStream());

            snd2.write(rcv1.nextLine() + "\r\n");
            snd2.flush();

            String mclt = "", msrv = "I hate black people";
            while (!mclt.equals(msrv)) {
                mclt = msrv;
                snd1.write(mclt + "\r\n");
                snd1.flush();

                msrv = rcv2.nextLine();
                if (mclt.equals(msrv)) {
                    snd1.write("200\r\n");
                    snd1.flush();
                } else {
                    snd1.write("400\r\n");
                    snd1.flush();
                }
            }

            rcv1.close();
            snd1.close();
            clt1.close();
            rcv2.close();
            snd2.close();
            clt2.close();
        } catch (IOException ioe) {
            System.out.println("Hubo un problema.");
        }
    }
}

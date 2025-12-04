import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        if (args.length < 2) System.out.println("Too few arguments");
        else if (args.length > 2) System.out.println("Too much arguments");
        else {
            System.out.println("Crating socket conection to " + args[0] + ":" + args[1] + "\n");
            try {
                Socket client = new Socket(args[0], Integer.parseInt(args[1]));
                Scanner in = new Scanner(client.getInputStream());
                PrintWriter out = new PrintWriter(client.getOutputStream(), false);
                System.out.println("\rDone, disconnecting.");
                in.close();
                out.close();
                client.close();
            } catch (IOException ioe) {
                System.out.println("\rI/O ERROR.");
            }

        }
    }
}

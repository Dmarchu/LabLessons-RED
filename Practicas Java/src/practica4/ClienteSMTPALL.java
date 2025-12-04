import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSMTPALL {

    static void error(String cadena) {
        System.out.println("SEGGS" + cadena);
        System.exit(0);
    }

    public static void main(String args[]) {
        String to;
        for (int j = 0; j < 10; j++) {
            for (int i = 1; i <= 24; i++) {
                try{
                    if (i < 10) to = "redes0" + i + "@redes.upv.es";
                    else to = "redes" + i + "@redes.upv.es";

                    Socket s=new Socket("serveis-rdc.redes.upv.es", 25);
                    System.out.println("Conectado al servidor SMTP de serveis-rdc");
                    PrintWriter salida = new PrintWriter(s.getOutputStream());
                    Scanner entrada=new Scanner(s.getInputStream());
                    String respuesta = entrada.nextLine();
                    System.out.println(respuesta);
                    if (!respuesta.startsWith("220")) {error(respuesta);}

                    salida.print("HELO [158.42.180.6]\r\n");
                    salida.flush();
                    respuesta = entrada.nextLine();
                    System.out.println(respuesta);
                    if (!respuesta.startsWith("250")) {error(respuesta);}

                    salida.print("MAIL FROM:<redes11@redes.upv.es>\r\n");
                    salida.flush();
                    respuesta = entrada.nextLine();
                    System.out.println(respuesta);
                    if (!respuesta.startsWith("250")) {error(respuesta);}

                    salida.print("RCPT TO:<" + to + ">\r\n");
                    salida.flush();
                    respuesta = entrada.nextLine();
                    System.out.println(respuesta);
                    if (!respuesta.startsWith("250")) {error(respuesta);}

                    salida.print("DATA\r\n");
                    salida.flush();
                    respuesta = entrada.nextLine();
                    System.out.println(respuesta);
                    if (!respuesta.startsWith("354")) {error(respuesta);}

                    // **completar**
                    // Aqui van varios print con todo el correo
                    // electronico incluidas las cabeceras

                    salida.print("From: redes11@redes.upv.es\r\n");
                    salida.print("To: " + to + "\r\n");
                    salida.print("Subject: Gelito el incapaz\r\n\r\n");
                    salida.print("Me llaman fer **** a mi padre por placer\n");
                    salida.print(".\r\n");

                    salida.flush();
                    respuesta = entrada.nextLine();
                    System.out.println(respuesta);
                    if (!respuesta.startsWith("250")) {error(respuesta);}

                    salida.print("QUIT\r\n");
                    salida.flush();
                    respuesta = entrada.nextLine();
                    System.out.println(respuesta);
                    if (!respuesta.startsWith("221")) {error(respuesta);}

                    s.close();
                    System.out.println("Desconectado");
                } catch (UnknownHostException e) {
                    System.out.println("Host desconocido");
                    System.out.println(e);
                } catch (IOException e) {
                    System.out.println("No se puede conectar");
                    System.out.println(e);
                }
            }
        }
    }
}

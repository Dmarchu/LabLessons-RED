import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClienteSMTP {

    static void error(String cadena) {
        System.out.println("SEGGS" + cadena);
        System.exit(0);
    }

    public static void main(String args[]) {
        try{
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

            salida.print("MAIL FROM:<redes06@redes.upv.es>\r\n");
            salida.flush();
            respuesta = entrada.nextLine();
            System.out.println(respuesta);
            if (!respuesta.startsWith("250")) {error(respuesta);}

            salida.print("RCPT TO:<redes06@redes.upv.es>\r\n");
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

            salida.print("From: redes06@redes.upv.es\r\n");
            salida.print("To: redes06@redes.upv.es\r\n");
            salida.print("Subject: si\r\n\r\n");
            salida.print("si\n");
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

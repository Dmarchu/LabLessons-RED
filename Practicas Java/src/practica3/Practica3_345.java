import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Practica3_345 {
    private static Scanner escaner = new Scanner(System.in); // Lectura desde teclado
    private static String host, objeto ; // host, ruta y objeto de la pÃ¡gina actual
    private static Socket socketCliente; // Socket cliente para interactuar con el servidor
    private static PrintWriter transmision;
    private static Scanner recepcion;

    public static void main(String[] args) throws Exception {  // expulsamos excepciones sin tratarlas
        Scanner sc = new Scanner(System.in);
        while (!validarURL(sc.nextLine().trim())) {
            System.out.println("Intentelo de nuevo");
        } // http://www.redes.upv.es/index.html
        System.out.println("Host: " + host + "\nObjeto: " + objeto + "\n");
        String peticion = construyePeticion();
        System.out.println("\n Petición a enviar: \n ----------------------");
        System.out.println(peticion + "\n ----------------------");

        if (!enviarPeticion(peticion)) {
            System.out.println("No se ha conseguido enviar la petición. Prueba otra vez:");
        } else {
            System.out.println("Se ha enviado la petición.");
            String respuestaHTML = obtenerPagina();
            System.out.println("\n---- Contenido de la Página ----");
            System.out.println(respuestaHTML);
            System.out.println("---- Fin del Contenido ----\n");
            System.out.println("---- Listas de Enlaces ----\n");
            extraerEnlaces (respuestaHTML);
        }
        socketCliente.close();
        escaner.close();
    }

    private static boolean validarURL(String URL) {
        try {
            InetAddress.getByName(URL);
            host = URL;
            objeto = "/index.html";
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }

    private static String construyePeticion() {
        String solicitud = "GET " + objeto + " HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "Connection: close\r\n\r\n";
        System.out.println(solicitud);
        return solicitud;
    }

    private static boolean enviarPeticion(String peticion) {
        try {
            socketCliente = new Socket(host, 80);
            recepcion = new Scanner(socketCliente.getInputStream());
            transmision = new PrintWriter(socketCliente.getOutputStream());
            transmision.print(peticion);
            transmision.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String obtenerPagina() {
        String contenido = recepcion.nextLine() + "\r\n";
        while (recepcion.hasNextLine()) {
            contenido += (recepcion.nextLine() + "\r\n");
        }
        if (contenido.contains("200 OK")) System.out.println("Página obtenida exitosamente\n");
        else System.out.println("Error al obtener la página\n");
        return contenido;
    }

    private static void extraerEnlaces(String contenidoHtml) {
        String patron = "<a href=\"";
        int indice = 0;
        int i = 0;
        while ((indice = contenidoHtml.indexOf(patron, indice)) != -1) {
            int inicio = indice + patron.length();
            int fin = contenidoHtml.indexOf("\"", inicio);
            if (fin != -1) {
                String enlace = contenidoHtml.substring(inicio, fin);
                if (enlace.startsWith("https")) System.out.println(++i + "- " + enlace);
            }
            indice = fin + 1;
        }
    }


}
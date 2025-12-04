import java.io.*; // Usamos FileOutputStream, IOException, InputStream, OutputStream, UnknownHostException
import java.net.*; // Usamos Socket
import java.util.*; // Usamos Scanner, ArrayList, List

public class Practica3_12 {
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
        System.out.println("\n PeticiÃ³n a enviar: \n ----------------------");
        System.out.println(peticion + "\n ----------------------");

        if (!enviarPeticion(peticion)) {
            System.out.println("No se ha conseguido enviar la peticiÃ³n. Prueba otra vez:");
        } else {
            // Recogemos la respuesta
            String respuestaHTML = obtenerPagina();
            // Imprimimos la respuesta
            System.out.println("\n---- Contenido de la PÃ¡gina ----");
            System.out.println(respuestaHTML);
            System.out.println("---- Fin del Contenido ----\n");
            // Analizamos la respuesta
            extraerEnlaces (respuestaHTML); // VersiÃ³n 0: No se implementa
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

    private static boolean enviarPeticion(String peticion) throws Exception{
        boolean respuesta = false;
        // expulsamos excepciones sin tratarlas
        socketCliente = new Socket( host , 80 );
        recepcion = new Scanner( socketCliente.getInputStream());
        transmision = new PrintWriter(socketCliente.getOutputStream());
        transmision.print(peticion);
        transmision.flush();
        respuesta = true;

        return respuesta;
    }

    private static String obtenerPagina() throws Exception {
        // expulsamos excepciones sin tratarlas
        String contenido = "";
        contenido = recepcion.nextLine()+"\r\n";
        while (recepcion.hasNextLine())
        {
            contenido = contenido + recepcion.nextLine() + "\r\n";
        }

        return contenido;
    }

    private static void extraerEnlaces(String contenidoHtml) {
        // Pendiente de implementar

    }


}
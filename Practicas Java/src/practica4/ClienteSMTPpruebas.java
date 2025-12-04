import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.Normalizer;
import java.util.Scanner;

public class ClienteSMTPpruebas {

    public static final String red = "\u001B[31m";
    public static final String reset = "\u001B[0m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String purple = "\u001B[35m";

    public static PrintWriter salida;
    public static Scanner entrada;
    public static Socket s;

    static void error(String cadena) {
        System.out.println(red + "ERROR: " + cadena + reset);
        System.exit(0);
    }

    static void newQ(String message, int wanted) {
        salida.print(message + "\r\n");
        salida.flush();
        String respuesta = entrada.nextLine();
        if (!respuesta.startsWith(String.valueOf(wanted))) {
            error(respuesta);
        } else {
            System.out.println(green + "+OK: " + reset + respuesta);
        }
    }

    public static void Monitoring(String from, String to, String subject, String bodytext) throws UnknownHostException, IOException {
        newQ("HELO [158.42.180.6]", 250);
        newQ("MAIL FROM:<" + from + ">", 250);
        newQ("RCPT TO:<" + to + ">", 250);
        newQ("DATA", 354);

        newQ("From: " + from + "\r\n" +
                "To: " + to + "\r\n" +
                "Subject: " + subject + "\r\n" +
                "MIME-Version: 1.0\r\n" +
                "Content-Type: text/plain; charset=UTF-8\r\n" +
                "Content-Transfer-Encoding: 8bit\r\n\r\n" +
                bodytext + "\n.", 250);
    }

    static String resolveName(String name) {
        String mail = name.substring(0, 1);
        int pos = 1;
        while (name.charAt(pos) != ' ') pos++;
        mail += name.substring(++pos, pos + 3);
        while (name.charAt(pos) != ' ') pos++;
        mail += name.substring(++pos, pos + 3);
        String normalized = Normalizer.normalize(mail.trim().toLowerCase(), Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "");
    }

    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        System.out.println(yellow + "--- " + purple + "DOMINIO SMTP" + yellow + " ---\n" + reset + "1- Use " + purple + "smtp.upv.es" + reset + "\n" +
                "2- Use " + purple + "serveis-rdc.redes.upv.es" + reset + "\n3- Use " + purple + "otro\n" + yellow + "--------------------" + reset);
        System.out.print("Enter an option: ");
        int selection = kbd.nextInt();
        String domain;
        switch (selection) {
            case 1:
                domain = "smtp.upv.es";
                System.out.println(green + "Domain assigned to: smtp.upv.es" + reset);
                break;
            case 2:
                domain = "serveis-rdc.redes.upv.es";
                System.out.println(green + "Domain assigned to: serveis-rdc.redes.upv.es" + reset);
                break;
            case 3:
                kbd.nextLine();
                domain = kbd.nextLine();
                System.out.println(green + "Domain assigned to: " + domain + reset);
                break;
            default:
                domain = "smtp.upv.es";
                System.out.println(red + "Domain assigned to: smtp.upv.es because " + selection + " is an invalid input!" + reset);
                break;
        }
        try {
            s = new Socket(domain, 25);
            salida = new PrintWriter(s.getOutputStream());
            entrada = new Scanner(s.getInputStream());
            String respuesta = entrada.nextLine();
            System.out.println(yellow + respuesta + reset);
            if (!respuesta.startsWith("220")) {
                error(respuesta);
            }
        } catch (IOException e) {
            System.out.println(red + "Error with the SMTP domain" + reset);
            System.exit(1);
        }
        System.out.println(yellow + "------- " + purple + "INFO" + yellow + " -------" + reset);
        System.out.print("Do you want to enter a name or an adress? (N/A): ");
        String sel, to, from, subject, bodytext = "";
        try {
            kbd.nextLine();
            sel = kbd.nextLine().trim();
            while (!sel.equalsIgnoreCase("N") && !sel.equalsIgnoreCase("A")) {
                System.out.print("Character " + sel + " not valid enter N or A: ");
                sel = kbd.nextLine().trim();
            }
            if (sel.equalsIgnoreCase("N")) {
                System.out.print("Enter the complete name of the " + green + "receiver" + reset + " with surnames and spaces between them: ");
                to = resolveName(kbd.nextLine().trim());
                System.out.print("Enter the @ of the " + green + "receiver" + reset + ": ");
                to += kbd.nextLine().trim();
                System.out.print("Enter the complete name of the " + yellow + "sender" + reset + " with surnames and spaces between them: ");
                from = resolveName(kbd.nextLine().trim());
                System.out.print("Enter the @ of the " + yellow + "sender" + reset + ": ");
                from += kbd.nextLine().trim();
            } else {
                System.out.print("Enter the adress of the " + green + "receiver" + reset + ": ");
                to = kbd.nextLine().trim();
                System.out.print("Enter the adress of the " + yellow + "sender" + reset + ": ");
                from = kbd.nextLine().trim();
            }
            System.out.print("Enter the " + purple + "subject" + reset + " of the " + red + "mail" + reset + ": ");
            subject = kbd.nextLine().trim();
            System.out.println("Enter the " + purple + "body" + reset + " of the " + red + "mail" + reset + " (Double enter to END): ");
            String temp;
            while (true) {
                temp = kbd.nextLine().trim();
                if (temp.isEmpty()) break;
                bodytext += temp + "\n";
            }
            System.out.print(yellow + "------- " + purple + "MODE" + yellow + " -------" + reset
                    + "\n1- " + purple + "Single mail" + reset + "\n" +
                    "2- " + red + "MAIL BOMBER" + reset
                    + "\n3- " + yellow + "MultiMail [Not implemented]\n" + yellow + "--------------------"
                    + "\n Enter an option: " + reset);
            selection = kbd.nextInt();
            switch (selection) {
                case 1: System.out.println(yellow + "---- " + purple + "MONITORING" + yellow + " ----" + reset);
                    Monitoring(from, to, subject, bodytext);
                    newQ("QUIT", 221);
                    break;
                case 2: kbd.nextLine();
                    System.out.print(yellow + " Enter the number of messages to send: " + reset);
                    int i = kbd.nextInt();
                    System.out.println(yellow + "\n---- " + purple + "MONITORING" + yellow + " ----" + reset);
                    for (int j = 1; j <= i; j++) {
                        try {
                            System.out.println(yellow + "MESSAGE " + j + ": " + reset);
                            Monitoring(from, to, subject, bodytext);
                        } catch (Exception e) {
                            System.out.println(red + "ERROR SENDING MESSAGE: " + j + reset);
                        }
                    } System.out.println(green + "FINISHED SENDING " + i + " MESSAGES" + reset);
                    newQ("QUIT", 221);
                    break;
                default: System.out.println(red + "Function not implemented" + reset);
                    break;
            } kbd.close();
            s.close();
            System.out.println(green + "Desconectado" + reset);
            System.out.println(yellow + "--------------------" + reset);
        } catch (UnknownHostException e) {
            System.out.println(red + "Host desconocido" + reset);
        } catch (IOException e) {
            System.out.println(red + "No se puede conectar" + reset);
        } catch (Exception e) {
            System.out.println(red + "Something went wrong during the targeting process!" + reset);
        }
    }
}
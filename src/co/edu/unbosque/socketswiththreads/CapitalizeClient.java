package co.edu.unbosque.socketswiththreads;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Example from: https://cs.lmu.edu/~ray/notes/javanetexamples/
 *
 * A command line client for consuming the capitalize server.
 */
public class CapitalizeClient {

    static String IP = "127.0.0.1";
    static int PORT = 59897;

    public static void main(String[] args) throws Exception {

        // a Socket is instantiated and a request for pairing with the server is sent
        // listening port on the server must be the same
        try (var socket = new Socket(IP, PORT)) {

            System.out.println("Connected to capitalize server " + socket);
            System.out.println();

            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Enter the message to be capitalized:");

            var scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
                System.out.println("Capitalized message: " + in.nextLine());

                System.out.println();
                System.out.println("Enter the message to be capitalized:");
            }

        }

    }

}
package co.edu.unbosque.socketswiththreads;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Example based on: https://cs.lmu.edu/~ray/notes/javanetexamples/
 *
 * A command line client for consuming the capitalize server.
 */
public class CapitalizeClient {

    static String IP = "127.0.0.1";
    static int PORT = 59897;

    public static void main(String[] args) throws Exception {
        // A socket is instantiated and a request for pairing with the server is sent
        // Listening port on the server must be the same
        try (var socket = new Socket(IP, PORT)) {
            System.out.println("Connected to capitalize server " + socket);
            System.out.println();

            // Objects for sending and receiving messages
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Enter the message to be capitalized:");

            // Reading message to be capitalized from terminal
            var scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                // Sending the message to server
                out.println(scanner.nextLine());
                System.out.println("Capitalized message: " + in.nextLine());

                System.out.println();
                System.out.println("Enter the message to be capitalized:");
            }
        }
    }
}
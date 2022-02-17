package co.edu.unbosque.socketswiththreads;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Capitalizer implements Runnable {

    private Socket socket;

    public Capitalizer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        safePrintln("Connected to client " + socket);
        safePrintln("");

        try {
            // Objects for sending and receiving messages
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);

            // Each time a new message is received
            while (in.hasNextLine()) {
                // Reads the message
                var message = in.nextLine();
                safePrintln("Message received: " + message);

                // Process the message and sends the response
                var newMessage = message.toUpperCase();
                out.println(newMessage);
                safePrintln("Message returned: " + newMessage);
                safePrintln("");
            }
        } catch (Exception e) {
            safePrintln("Error:" + socket);
            safePrintln("");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {}
            safePrintln("Socket closed: " + socket);
            safePrintln("");
        }
    }

    // Method synchronization
    // Variation of System.out for printing to command line inside a Thread
    // https://www.baeldung.com/java-synchronized
    public void safePrintln(String s) {
        synchronized (System.out) {
            System.out.println(s);
        }
    }
}
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

        safePrintln("Connected: " + socket);

        try {

            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);

            while (in.hasNextLine()) {
                var message = in.nextLine();
                safePrintln("The message received is: " + message);
                var newMessage = message.toUpperCase();
                safePrintln("The message to be returned is: " + newMessage);
                out.println(newMessage);
            }

        } catch (Exception e) {
            safePrintln("Error:" + socket);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
            safePrintln("Closed: " + socket);
        }
    }

    public void safePrintln(String s) {
        synchronized (System.out) {
            System.out.println(s);
        }
    }

}
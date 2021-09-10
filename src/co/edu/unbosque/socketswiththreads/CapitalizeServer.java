package co.edu.unbosque.socketswiththreads;

import java.net.ServerSocket;
import java.util.concurrent.Executors;

/**
 * Example from: https://cs.lmu.edu/~ray/notes/javanetexamples/
 *
 * A server program which accepts requests from clients to capitalize strings.
 * When a client connects, a new thread is started to handle it. Receiving
 * client data, capitalizing it, and sending the response back is all done on
 * the thread, allowing much greater throughput because more clients can be
 * handled concurrently.
 */
public class CapitalizeServer {

    static int PORT = 59897;

    public static void main(String[] args) throws Exception {

        // the socket is automatically closed at the end of the block
        // a ServerSocket is created for listening on the specified port
        try (var listener = new ServerSocket(PORT)) {

            System.out.format("The capitalization server is running and listening on port %d...", PORT);
            System.out.println("\n");

            // nThreads limits the number of clients server is able to attend
            // this technique is recommended in scenarios with a very limited quantity of machine resources
            var pool = Executors.newFixedThreadPool(3);

            while (true) {
                // when server accepts a client, a new Socket for handling communication with that specific client is created
                // the new Socket is passed to the executor pool assigning it a new thread for execution
                pool.execute(new Capitalizer(listener.accept()));
            }

        }

    }

}

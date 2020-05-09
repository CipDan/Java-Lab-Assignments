import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * An implementation of a TCP Server.
 */
public class GameServer {

    public static final int PORT = 8100;

    /**
     * Runs the server application, which creates a new thread for each new player.
     *
     * @throws IOException if an I/O error occurs.
     */
    public GameServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                // Execute the client's request in a new thread
                new ClientThread(socket, serverSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

    /**
     * The `main` method.
     *
     * @param args command line arguments.
     * @throws IOException if an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();
    }

}

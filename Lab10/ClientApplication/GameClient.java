import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * An implementation of a TCP Client.
 */
public class GameClient {

    /**
     * Shows an animated introductory announcement.
     */
    public static void announcement() {
        try {
            int i;
            String text = "Hello there.\n\n";
            for (i = 0; i < text.length(); ++i) {
                System.out.printf("%c", text.charAt(i));
                Thread.sleep(200);
            }
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            text = "\n\nGLaDOS here. Today I will be your supervisor.\n";
            for (i = 0; i < text.length(); ++i) {
                System.out.printf("%c", text.charAt(i));
                Thread.sleep(200);
            }
            Thread.sleep(1000);
            text = "\nWhat's that you say?\nTo put it simply, I will be monitoring your progress during the " +
                    "compulsory Aperture Science Experience Enhancement Test Suite.\n";
            for (i = 0; i < text.length(); ++i) {
                System.out.printf("%c", text.charAt(i));
                Thread.sleep(200);
            }
            Thread.sleep(3000);
            text = "\nYou... are a slow one...\nYou are wondering what these tests are about, aren't you?\nYou " +
                    "will simply be required to enter different commands into the terminal.\nBased on the said commands, " +
                    "we will try to generate an appropriate cognitive response.\n";
            for (i = 0; i < text.length(); ++i) {
                System.out.printf("%c", text.charAt(i));
                Thread.sleep(200);
            }
            Thread.sleep(500);
            text = "\nBut enough talk. Let's get to the TESTS!\n";
            for (i = 0; i < text.length(); ++i) {
                System.out.printf("%c", text.charAt(i));
                Thread.sleep(200);
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Process interrupted... " + e);
        }
    }

    /**
     * Snds to the server a request to start a new game.
     *
     * @param out     the socket's write end.
     * @param scanner a <code>Scanner</code> for reading user input.
     * @throws InterruptedException if any thread has interrupted the current thread. The interrupted status of the current thread is cleared when this exception is thrown.
     */
    public static void createGame(PrintWriter out, Scanner scanner) throws InterruptedException {
        String msg = "\nWhat kind of game board should I set up? You can choose between 'big' and 'small'.\n";
        int it;
        for (it = 0; it < msg.length(); ++it) {
            System.out.printf("%c", msg.charAt(it));
            Thread.sleep(200);
        }
        String choice = scanner.nextLine();
        while (!choice.equals("big") && !choice.equals("small")) {
            msg = "\nSorry \uD83D\uDE14. Option not recognized. " +
                    "Perhaps you wanted to say 'big' or 'small'?\n";
            for (it = 0; it < msg.length(); ++it) {
                System.out.printf("%c", msg.charAt(it));
                Thread.sleep(200);
            }
            choice = scanner.nextLine();
        }
        out.println(choice);
    }

    /**
     * Asks client for necessary data to create a new player and then sends the data to the server.
     *
     * @param out     the socket's write end.
     * @param scanner a <code>Scanner</code> for reading user input.
     * @throws InterruptedException if any thread has interrupted the current thread. The interrupted status of the current thread is cleared when this exception is thrown.
     */
    public static void createPlayer(PrintWriter out, Scanner scanner) throws InterruptedException {
        int it;
        String msg = "\nPlease enter a name: ";
        for (it = 0; it < msg.length(); ++it) {
            System.out.printf("%c", msg.charAt(it));
            Thread.sleep(200);
        }
        String playerName = scanner.nextLine();
        out.println(playerName);
    }

    /**
     * Reads message received from server and displays it.
     *
     * @param in the socket's read end.
     * @throws IOException          if an I/O error occurs.
     * @throws InterruptedException if any thread has interrupted the current thread. The interrupted status of the current thread is cleared when this exception is thrown.
     */
    public static void displayServerMsg(BufferedReader in) throws IOException, InterruptedException {
        String msg = "\n" + in.readLine() + "\n";
        int it;
        for (it = 0; it < msg.length(); ++it) {
            System.out.printf("%c", msg.charAt(it));
            Thread.sleep(200);
        }
    }

    /**
     * Runs the client side of the application.
     *
     * @param args command line arguments.
     * @throws IOException if an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
            // Send a request to the server
            Scanner scanner = new Scanner(System.in);
            String request, state, message;
            //announcement();
            int it;
            while (true) {
                message = "\nPlease state your request: ";
                for (it = 0; it < message.length(); ++it) {
                    System.out.printf("%c", message.charAt(it));
                    Thread.sleep(200);
                }
                request = scanner.nextLine();
                switch (request) {
                    case "exit":
                        out.println(request);
                        message = "\nClient closed!\n";
                        for (it = 0; it < message.length(); ++it) {
                            System.out.printf("%c", message.charAt(it));
                            Thread.sleep(200);
                        }
                        return;
                    case "create game":
                        out.println(request);
                        state = in.readLine();
                        if (state.compareTo("true") == 0) {
                            createGame(out, scanner);
                            createPlayer(out, scanner);
                        }
                        displayServerMsg(in);
                        break;
                    case "join game":
                        out.println(request);
                        state = in.readLine();
                        if (state.compareTo("true") == 0) {
                            createPlayer(out, scanner);
                        }
                        displayServerMsg(in);
                        break;
                    case "make move":
                        break;
                    case "choose color":

                    default:
                        out.println(request);
                        displayServerMsg(in);
                        break;
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

import GameElements.Game;
import GameElements.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A implementation of a thread which handles client requests.
 */
public class ClientThread extends Thread {

    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private static Game currentGame = null;
    private boolean hasJoinedAGame = false;
    private boolean isRunning = false;

    /**
     * Initializes the thread.
     *
     * @param socket       the client's socket.
     * @param serverSocket the server socket.
     */
    public ClientThread(Socket socket, ServerSocket serverSocket) {
        this.socket = socket;
        this.serverSocket = serverSocket;
    }

    /**
     * Runs the thread.
     */
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String command;
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String answer;
            while (true) {
                command = in.readLine();
                switch (command) {
                    case "stop":
                        answer = "Server stopped!";
                        out.println(answer);
                        try {
                            socket.close(); // or use try-with-resources
                            serverSocket.close();
                        } catch (IOException e) {
                            System.err.println(e);
                        }
                        return;
                    case "create game":
                        if (currentGame == null) {
                            out.println("true");
                            String option = in.readLine();
                            if (option.equals("big")) {
                                currentGame = new Game(19);
                            } else {
                                currentGame = new Game(15);
                            }
                            String playerName = in.readLine();
                            currentGame.addPlayer(new Player(playerName));
                            answer = "Game set up! Welcome, " + playerName + "!";
                            hasJoinedAGame = true;
                        } else {
                            out.println("false");
                            answer = "A game is already set up! Perhaps you want to join it?";
                        }
                        out.println(answer);
                        break;
                    case "join game":
                        if (currentGame != null) {
                            if (!hasJoinedAGame) {
                                if (currentGame.getPlayers().size() < 2) {
                                    out.println("true");
                                    String playerName = in.readLine();
                                    currentGame.addPlayer(new Player(playerName));
                                    answer = "Game joined! Welcome, " + playerName;
                                    hasJoinedAGame = true;
                                } else {
                                    out.println("false");
                                    answer = "Cannot join! Game already in progress!";
                                }
                            } else {
                                out.println("false");
                                answer = "You have already joined a game!";
                            }
                        } else {
                            out.println("false");
                            answer = "There is no game created. Perhaps you want to create one?";
                        }
                        out.println(answer);
                        break;
                    case "choose color":
                    case "make move":

                    case "exit":
                        try {
                            socket.close();
                        } catch (IOException e) {
                            System.err.println(e);
                        }
                        return;
                    default:
                        answer = "Server received the request...";
                        out.println(answer);
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        }
    }
}

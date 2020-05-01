package Lab8;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.in.read();
        Database database = Database.getInstance();
        database.closeConnection();
    }
}

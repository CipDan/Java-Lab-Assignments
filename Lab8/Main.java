package Lab8;

public class Main {

    public static void main(String[] main) {
        Database database = Database.getInstance();
        database.closeConnection();
    }
}

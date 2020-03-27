package Lab5;

import java.io.*;

/**
 * A working space for all the assignments.
 */
public class Main {

    /**
     * Tests whether problems arise when creating and saving of a file.
     */
    private void testCreateSave() {
        Catalog catalog = new Catalog("Java Resources", "./catalog.xml");
        Document doc1 = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc1.addTag("type", "Slides");
        catalog.add(doc1);
        try {
            Manager.save(catalog);
        } catch (IOException e) {
            System.out.println("Unexpected error when saving the file!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Tests whether problems arise when loading and viewing the content of a file.
     */
    private void testLoadView() {
        try {
            Catalog catalog = Manager.load("./catalog.xml");
            Document doc1 = catalog.findById("java1");
            Manager.view(doc1);
        } catch (IOException e) {
            System.out.println("Unexpected error when loading the file!");
            e.printStackTrace();
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            System.out.println("Unexpected error when loading class!");
            e.printStackTrace();
            System.exit(-1);
        } catch (InvalidCatalogException e) {
            System.out.println("Invalid catalog file!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Main function.
     *
     * @param args A list of user-given arguments.
     */
    public static void main(String[] args) {
        MyShell.executeShell();
        /*Main app = new Main();
        app.testCreateSave();
        app.testLoadView();*/
    }
}

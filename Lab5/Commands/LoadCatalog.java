package Lab5.Commands;

import Lab5.Catalog;
import Lab5.InvalidCatalogException;
import Lab5.Manager;

import java.io.IOException;
import java.util.List;

public class LoadCatalog implements Command {

    @Override
    public void execute(List<Catalog> catalogs, String[] params) {
        if (params.length == 1) {
            try {
                boolean found = false;
                Catalog auxCatalog = Manager.load(params[0]);
                for (Catalog catalog : catalogs) {
                    if (catalog.getName().equals(auxCatalog.getName()) &&
                            catalog.getPath().equals(auxCatalog.getPath())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    catalogs.add(auxCatalog);
                    System.out.println("Catalog loaded successfully!");
                } else {
                    System.out.println("Catalog is already loaded!");
                }
            } catch (IOException e) {
                System.out.println("Unexpected error when loading the catalog!");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Unexpected error when loading class!");
                e.printStackTrace();
            } catch (InvalidCatalogException e) {
                System.out.println("Invalid catalog file");
                e.printStackTrace();
            }
        } else {
            System.out.println("Wrong number of arguments \uD83D\uDE21! You must have exactly 1 parameter for this command!");
        }
    }
}

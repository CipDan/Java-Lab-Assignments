package Lab5.Commands;

import Lab5.Catalog;
import Lab5.Manager;

import java.io.IOException;
import java.util.List;

/**
 * Implementation of 'save-catalog' command.
 */
public class SaveCatalog implements Command {

    @Override
    public void execute(List<Catalog> catalogs, String[] params) {
        if (params.length == 1) {
            int index;
            for (index = 0; index < catalogs.size(); index++) {
                if (catalogs.get(index).getName().equals(params[0]))
                    break;
            }
            if (index == catalogs.size()) {
                System.out.println("Catalog with name " + params[0] + " not found!");
            } else {
                try {
                    Manager.save(catalogs.get(index));
                    System.out.println("Catalog saved successfully!");
                } catch (IOException e) {
                    System.out.println("Unexpected error when saving the catalog!");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Wrong number of arguments \uD83D\uDE21! You must have exactly 1 parameter for this command!");
        }
    }
}

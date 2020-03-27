package Lab5.Commands;

import Lab5.Catalog;

import java.util.List;

public class CreateCatalog implements Command {
    @Override
    public void execute(List<Catalog> catalogs, String[] params) {
        if (params.length == 2) {
            catalogs.add(new Catalog(params[0], params[1]));
            System.out.println("Catalog created successfully!");
        } else {
            System.out.println("Wrong number of arguments \uD83D\uDE21! You must have exactly 2 parameters for this command!");
        }
    }
}

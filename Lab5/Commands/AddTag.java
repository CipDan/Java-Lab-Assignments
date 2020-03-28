package Lab5.Commands;

import Lab5.Catalog;

import java.util.List;

/**
 * Implementation of 'add-tag' command.
 */
public class AddTag implements Command {
    @Override
    public void execute(List<Catalog> catalogs, String[] params) {
        if (params.length == 4) {
            int index;
            for (index = 0; index < catalogs.size(); index++) {
                if (catalogs.get(index).getName().equals(params[0]))
                    break;
            }
            if (index == catalogs.size()) {
                System.out.println("Catalog with name " + params[0] + " not found!");
            } else {
                catalogs.get(index).findById(params[1]).addTag(params[2], params[3]);
                System.out.println("Tag added successfully!");
            }
        } else {
            System.out.println("Wrong number of arguments \uD83D\uDE21! You must have exactly 2 parameters for this command!");
        }
    }
}

package Lab5.Commands;

import Lab5.Catalog;
import Lab5.Document;
import Lab5.Manager;

import java.util.List;

public class ViewDocument implements Command {

    @Override
    public void execute(List<Catalog> catalogs, String[] params) {
        if (params.length == 2) {
            int index;
            for (index = 0; index < catalogs.size(); index++) {
                if (catalogs.get(index).getName().equals(params[0]))
                    break;
            }
            if (index == catalogs.size()) {
                System.out.println("Catalog with name " + params[0] + " not found!");
            } else {
                Document doc = catalogs.get(index).findById(params[1]);
                Manager.view(doc);
                System.out.println("Document viewed successfully!");
            }
        } else {
            System.out.println("Wrong number of arguments \uD83D\uDE21! You must have exactly 2 parameters for this command!");
        }
    }
}

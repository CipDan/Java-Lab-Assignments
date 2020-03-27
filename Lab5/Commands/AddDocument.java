package Lab5.Commands;

import Lab5.Catalog;
import Lab5.Document;

import java.util.List;

public class AddDocument implements Command {
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
            }
            Document document = new Document(params[1], params[2], params[3]);
            catalogs.get(index).add(document);
            System.out.println("Document added successfully!");
        } else {
            System.out.println("Wrong number of arguments \uD83D\uDE21! You must have exactly 4 parameters for this command!");
        }
    }
}

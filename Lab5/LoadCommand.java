package Lab5;

import Lab5.Commands.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadCommand {
    private Map<String, Command> commands = new HashMap<>();

    public LoadCommand() {
        commands.put("create-catalog", new CreateCatalog());
        commands.put("add-document", new AddDocument());
        commands.put("add-tag", new AddTag());
        commands.put("save-catalog", new SaveCatalog());
        commands.put("load-catalog", new LoadCatalog());
        commands.put("view-document", new ViewDocument());
        commands.put("info", new Info());
        commands.put("report", new Report());
    }

    public void load(String commandName, List<Catalog> catalogs, String... params) {
        commands.get(commandName).execute(catalogs, params);
    }
}

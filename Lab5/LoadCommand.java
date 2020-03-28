package Lab5;

import Lab5.Commands.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class for storing and loading supported commands.
 */
public class LoadCommand {
    private Map<String, Command> commands = new HashMap<>();

    /**
     * Creates a <code>Map</code> with all the supported commands.
     */
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

    /**
     * Loads a command based on its name.
     *
     * @param commandName the name of the command.
     * @param catalogs    the list of catalogs; all the listed commands make use of it.
     * @param params      A <code>String[]</code> representing the command's parameters.
     */
    public void load(String commandName, List<Catalog> catalogs, String... params) {
        commands.get(commandName).execute(catalogs, params);
    }
}

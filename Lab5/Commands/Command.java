package Lab5.Commands;

import Lab5.Catalog;

import java.util.List;

/**
 * An Interface for grouping commands.
 */
public interface Command {
    void execute(List<Catalog> catalogs, String[] params);
}

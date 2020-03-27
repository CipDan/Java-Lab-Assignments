package Lab5.Commands;

import Lab5.Catalog;

import java.util.List;

public interface Command {
    void execute(List<Catalog> catalogs, String[] params);
}

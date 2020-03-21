package Lab5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A named container which stores data about different documents.
 */
public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    @Deprecated
    public Catalog() {
    }

    /**
     * Creates a new catalog.
     * @param name name of the catalog.
     * @param path the location on the local machine where it can be found.
     */
    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    /**
     * Adds a document to the list in the catalog.
     * @param doc the document to be added.
     */
    public void add(Document doc) {
        documents.add(doc);
    }

    @Deprecated
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the catalog.
     * @return a <code>String</code> representing the catalog's name.
     */
    public String getName() {
        return name;
    }

    @Deprecated
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Returns the location of the catalog on the local machine.
     * @return a <code>String</code> representing the location of the catalog.
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns the list of documents in the catalog.
     * @return a list of all the documents stored in the catalog.
     */
    public List<Document> getDocuments() {
        return documents;
    }

    @Deprecated
    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    /**
     * Searches for a document based on its id.
     * @param id the id of a document in the catalog.
     * @return the found document or <code>null</code> if there are none with the specified id.
     */
    public Document findById(String id) {
        return documents.stream().filter(document -> document.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Converts a catalog to a <code>String</code>.
     *
     * @return the newly obtained <code>String</code>.
     */
    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder("Catalog:\n\n" +
                "Name: " + name + '\n' +
                "Path: '" + path + '\n' +
                "Documents:\n ");
        int index = 1;
        for (Document document : documents) {
            aux.append("\tNo: ").append(index).append('\n').append(document);
        }
        return aux.toString();
    }
}

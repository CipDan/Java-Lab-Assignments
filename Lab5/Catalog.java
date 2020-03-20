package Lab5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    @Deprecated
    public Catalog() {
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void add(Document doc) {
        documents.add(doc);
    }

    @Deprecated
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Deprecated
    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    @Deprecated
    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Document findById(String id) {
        return documents.stream().filter(document -> document.getId().equals(id)).findFirst().orElse(null);
    }

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

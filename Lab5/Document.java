package Lab5;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A representation of a document that can be stored in a catalog.
 */
public class Document implements Serializable {
    private String id;
    private String name;
    private String location;
    private Map<String, Object> tags = new HashMap<>();

    @Deprecated
    public Document() {
    }

    /**
     * Creates a new document.
     *
     * @param id       the document's id.
     * @param name     the document's name.
     * @param location the document's location, which can be a web address or a file path.
     */
    public Document(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    /**
     * Adds tags to the document.
     *
     * @param key used for identifying a tag.
     * @param obj the tag.
     */
    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    @Deprecated
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the document's id.
     *
     * @return a <code>String</code> representing the document's id.
     */
    public String getId() {
        return id;
    }

    @Deprecated
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the document's name.
     *
     * @return a <code>String</code> representing the document's name.
     */
    public String getName() {
        return name;
    }

    @Deprecated
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the document's location.
     *
     * @return a <code>String</code> representing the document's location.
     */
    public String getLocation() {
        return location;
    }

    @Deprecated
    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    /**
     * returns the document's tags.
     *
     * @return a <code>Map</code> of all the tags.
     */
    public Map<String, Object> getTags() {
        return tags;
    }

    /**
     * Converts a document to a <code>String</code>.
     *
     * @return the newly obtained <code>String</code>.
     */
    @Override
    public String toString() {
        return "\tId: " + id + '\n' +
                "\tName: " + name + '\n' +
                "\tLocation: " + location + '\n' +
                "\tTags: " + tags + "\n\n";
    }
}

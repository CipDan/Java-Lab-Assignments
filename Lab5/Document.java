package Lab5;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private String id;
    private String name;
    private String location;
    private Map<String, Object> tags = new HashMap<>();

    @Deprecated
    public Document() {
    }

    public Document(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    @Deprecated
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Deprecated
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Deprecated
    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Deprecated
    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "\tId: " + id + '\n' +
                "\tName: " + name + '\n' +
                "\tLocation: " + location + '\n' +
                "\tTags: " + tags + "\n\n";
    }
}

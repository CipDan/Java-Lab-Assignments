package Lab3;

public class Book implements Item {
    private String name;
    private int pageNumber;
    private int value;

    public Book(String name, int pageNumber, int value) {
        this.name = name;
        this.pageNumber = pageNumber;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int getWeight() {
        return pageNumber / 100;
    }
}

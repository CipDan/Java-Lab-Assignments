package GameElements;

public class Player {
    private String name;
    private String color = null;

    public Player(String name) {
        this.name = name;
    }

    public void assignColor(String color){
        this.color = color;
    }

    public boolean checkIfColorWasAssigned(){
        return color == null;
    }

    public String getName() {
        return name;
    }
}

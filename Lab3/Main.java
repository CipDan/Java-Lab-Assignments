package Lab3;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int generateKnapsackCapacity() {
        int cap = (int) (Math.random() * 21);
        while (cap < 10)
            cap = (int) (Math.random() * 21);
        return cap;
    }

    public static int generateNumOfItems() {
        int nbItems = (int) (Math.random() * 9);
        while (nbItems <= 1)
            nbItems = (int) (Math.random() * 9);
        return nbItems;
    }

    public static int generateNumOfPages() {
        int nbPages = (int) (Math.random() * 600);
        while (nbPages % 100 != 0 && nbPages < 100)
            nbPages = (int) (Math.random() * 600);
        return nbPages;
    }

    public static int generateWeight() {
        int weight = (int) (Math.random() * 11);
        while (weight < 1)
            weight = (int) (Math.random() * 11);
        return weight;
    }

    public static int generateValue() {
        int value = (int) (Math.random() * 11);
        while (value < 1)
            value = (int) (Math.random() * 11);
        return value;
    }

    public static String generateName() {
        StringBuilder name = new StringBuilder();
        int wordLength = (int) (Math.random() * 15);
        while (wordLength < 2)
            wordLength = (int) (Math.random() * 15);
        int index = (int) (Math.random() * 91);
        while (index < 65)
            index = (int) (Math.random() * 91);
        name.append((char) index);
        for (int i = 1; i < wordLength; ++i) {
            index = (int) (Math.random() * 123);
            while (index < 97)
                index = (int) (Math.random() * 123);
            name.append((char) index);
        }
        return name.toString();
    }

    public static WeaponType generateWeaponType() {
        int index = (int) (Math.random() * 6);
        switch (index) {
            case 0:
                return WeaponType.Pistol;
            case 1:
                return WeaponType.Semiautomatic;
            case 2:
                return WeaponType.Automatic;
            case 3:
                return WeaponType.Rifle;
            case 4:
                return WeaponType.Shotgun;
            case 5:
                return WeaponType.Sword;
        }
        return null;
    }

    /**
     * Generates the input of the problem.
     *
     * @param knapsack an object representing the knapsack
     * @param itemList a container for the items that will be generated
     */
    public static void generateProblemInput(Knapsack knapsack, List<Item> itemList) {
        knapsack.setCapacity(generateKnapsackCapacity());
        int nbItems = generateNumOfItems();
        int i, choice;
        for (i = 0; i < nbItems; ++i) {
            choice = (int) (Math.random() * 3);
            switch (choice) {
                case 0:
                    itemList.add(new Book(generateName(), generateNumOfPages(), generateValue()));
                case 1:
                    itemList.add(new Food(generateName(), generateWeight()));
                case 2:
                    itemList.add(new Weapon(generateWeaponType(), generateWeight(), generateValue()));
            }
        }
    }

    /**
     * Prints the input and the output of the Knapsack Problem.
     *
     * @param itemList a list of items
     * @param knapsack an object representing the knapsack
     */
    public static void printModel(List<Item> itemList, Knapsack knapsack) {
        StringBuilder aux = new StringBuilder();
        for (Item item : itemList) {
            aux.append("\t").append(item.getClass().getSimpleName()).append(": ").append(item.getName()).
                    append(", weight=").append(item.getWeight()).append(", value=").append(item.getValue()).
                    append(" (profit factor=").append(item.profitFactor()).append(")\n");
        }
        aux.append(knapsack.toString());
        System.out.println("capacity of the knapsack = " + knapsack.getCapacity() + "\navailable items:\n" + aux);
    }

    public static void main(String[] args) {
        //This is test code for the Compulsory section!
        /*Knapsack knapsack = new Knapsack(10);
        List<Item> itemsToChoose = new ArrayList<>();
        Book book1 = new Book("Dragon Rising", 300, 5);
        itemsToChoose.add(book1);
        Book book2 = new Book("A Blade in the Dark", 300, 5);
        itemsToChoose.add(book2);
        Food food1 = new Food("Cabbage", 2);
        itemsToChoose.add(food1);
        Food food2 = new Food("Rabbit", 2);
        itemsToChoose.add(food2);
        Weapon weapon = new Weapon(WeaponType.Sword, 5, 10);
        itemsToChoose.add(weapon);
        Algorithm greedy = new GreedyAlgorithm();
        greedy.fillKnapsack(knapsack, itemsToChoose);
        printModel(itemsToChoose, knapsack);
        knapsack.emptyKnapsack();
        System.out.println("\n----------------------------------------------------------------------------------\n");
        Algorithm dynProg = new DynamicProgrammingAlgorithm();
        dynProg.fillKnapsack(knapsack, itemsToChoose);
        printModel(itemsToChoose, knapsack);*/
        //This is the test code for the Optional and Bonus Sections!
        Knapsack knapsack = new Knapsack();
        List<Item> itemsToChoose = new ArrayList<>();
        long startTime, runningTime;
        generateProblemInput(knapsack, itemsToChoose);
        Algorithm greedy = new GreedyAlgorithm();
        startTime = System.nanoTime();
        greedy.fillKnapsack(knapsack, itemsToChoose);
        runningTime = System.nanoTime() - startTime;
        printModel(itemsToChoose, knapsack);
        System.out.println("It takes " + runningTime + "ns for the Greedy algorithm!");
        knapsack.emptyKnapsack();
        System.out.println("\n----------------------------------------------------------------------------------\n");
        Algorithm dynProg = new DynamicProgrammingAlgorithm();
        startTime = System.nanoTime();
        dynProg.fillKnapsack(knapsack, itemsToChoose);
        runningTime = System.nanoTime() - startTime;
        printModel(itemsToChoose, knapsack);
        System.out.println("It takes " + runningTime + "ns for the dynamic programming algorithm!");
        knapsack.emptyKnapsack();
        System.out.println("\n----------------------------------------------------------------------------------\n");
        Algorithm spa = new BellmanFordAlgorithm();
        startTime = System.nanoTime();
        spa.fillKnapsack(knapsack, itemsToChoose);
        runningTime = System.nanoTime() - startTime;
        printModel(itemsToChoose, knapsack);
        System.out.println("It takes " + runningTime + "ns for the Bellman-Ford algorithm!");
    }
}

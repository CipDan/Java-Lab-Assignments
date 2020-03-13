package Lab4;

import java.util.*;
import java.util.stream.IntStream;
import com.github.javafaker.Faker;

public class Main {

    public static int compareByName(Resident a, Resident b) {
        return -a.getName().compareTo(b.getName());
    }

    public static void main(String[] args) {
        //This is test code for the Compulsory section!
        var residents = IntStream.rangeClosed(0, 3).mapToObj(i -> new Resident("R" + i)).toArray(Resident[]::new);
        var hospitals = IntStream.rangeClosed(0, 2).mapToObj(i -> new Hospital("H" + i, (i < 2) ? i + 1 : i)).toArray(Hospital[]::new);
        for (Hospital hospital : hospitals)
            System.out.print(hospital + " ");
        System.out.println();
        for (Resident resident : residents)
            System.out.print(resident + " ");
        System.out.println();

        List<Resident> residentList = new ArrayList<>(Arrays.asList(residents));
        residentList.sort(Main::compareByName);
        for (Resident resident : residentList)
            System.out.print(resident + " ");
        System.out.println();
        Set<Hospital> hospitalSet = new TreeSet<>(Arrays.asList(hospitals));
        for (Hospital hospital : hospitalSet)
            System.out.print(hospital + " ");
        System.out.println();

        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();
        Map<Hospital, List<Resident>> hosPrefMap = new LinkedHashMap<>();
        resPrefMap.put(residents[0], Arrays.asList(hospitals[0], hospitals[1], hospitals[2]));
        resPrefMap.put(residents[1], Arrays.asList(hospitals[0], hospitals[1], hospitals[2]));
        resPrefMap.put(residents[2], Arrays.asList(hospitals[0], hospitals[1]));
        resPrefMap.put(residents[3], Arrays.asList(hospitals[0], hospitals[2]));
        hosPrefMap.put(hospitals[0], Arrays.asList(residents[3], residents[0], residents[1], residents[2]));
        hosPrefMap.put(hospitals[1], Arrays.asList(residents[0], residents[2], residents[1]));
        hosPrefMap.put(hospitals[2], Arrays.asList(residents[0], residents[1], residents[3]));
        System.out.println(resPrefMap);
        System.out.println(hosPrefMap);

        List<Hospital> target = Arrays.asList(hospitals[0], hospitals[2]);
        residentList.stream().filter(resident -> resPrefMap.get(resident).containsAll(target)).forEach(System.out::println);
        hospitalSet.stream().filter(hospital -> hosPrefMap.get(hospital).get(0).equals(residents[0])).forEach(System.out::println);

        //This is test code for the Optional section!
        /*Faker faker = new Faker();
        var residents = IntStream.rangeClosed(0, 3).mapToObj(i -> new Resident(faker.name().fullName())).toArray(Resident[]::new);
        var hospitals = IntStream.rangeClosed(0, 2).mapToObj(i -> new Hospital(faker.medical().hospitalName(), (i < 2) ? i + 1 : i)).toArray(Hospital[]::new);
        for (Hospital hospital : hospitals)
            System.out.print(hospital + " ");
        System.out.println();
        for (Resident resident : residents)
            System.out.print(resident + " ");
        System.out.println();*/
    }
}
package Lab4;

import java.util.List;
import java.util.Set;

public class Problem {

    public static int compareByName(Resident a, Resident b) {
        return -a.getName().compareTo(b.getName());
    }

    public static boolean checkIfStableMatching(ProblemInput input, Partition partition) {
        Set<Hospital> hospitals = partition.getHospitals();
        for (Hospital hospital : hospitals) {
            List<Resident> aux = partition.findPartnerList(hospital);
            for (Resident possibleMatch : input.getHosPrefMap().get(hospital)) {
                if (!aux.contains(possibleMatch) && input.getHosPrefMap().get(hospital).indexOf(possibleMatch) <
                        input.getHosPrefMap().get(hospital).indexOf(aux.get(aux.size() - 1)))
                    if(partition.getMatching(possibleMatch) == null)
                        return false;
                    else{
                        if(input.getResPrefMap().get(possibleMatch).indexOf(hospital) < input.getResPrefMap().
                                get(possibleMatch).indexOf(partition.getMatching(possibleMatch).getHospital()))
                            return false;
                    }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ProblemInput problemInput = new ProblemInput();
        problemInput.generateInputBasic();
        //problemInput.generateInputFaker();
        //This is test code for the Optional and Bonus sections!
        //GaleShapley aux = new GaleShapley();
        FirstComeFirstServed aux = new FirstComeFirstServed();
        Partition partition = new Partition();
        aux.createMatchingList(problemInput, partition);
        System.out.println(partition);
        if(checkIfStableMatching(problemInput, partition)){
            System.out.println("Matching is stable!");
        }
        else{
            System.out.println("Matching is unstable!");
        }
    }
}
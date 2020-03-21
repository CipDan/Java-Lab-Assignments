package Lab4;

import java.util.List;
import java.util.Set;

/**
 * A representation of The Hospitals/Residents Problem (HR).
 */
public class Problem {

    /**
     * A <code>Comparator</code>.
     *
     * @param a first resident to be compared.
     * @param b second resident to be compared.
     * @return -1 if resident <b>a</b>'s name is lexicographically bigger than resident <b>b</b>'s name,
     * 0 if they are equal and -1 otherwise.
     */
    public static int compareByName(Resident a, Resident b) {
        return -a.getName().compareTo(b.getName());
    }

    /**
     * Checks if the newly obtained list of matches is "stable".
     *
     * @param input     the problem's generated input.
     * @param partition the container in which the list of matches is stored.
     * @return <code>true</code> if the matching is stable and <false>otherwise</false>.
     */
    public static boolean checkIfStableMatching(ProblemInput input, Partition partition) {
        Set<Hospital> hospitals = partition.getHospitals();
        for (Hospital hospital : hospitals) {
            List<Resident> aux = partition.findPartnerList(hospital);
            for (Resident possibleMatch : input.getHosPrefMap().get(hospital)) {
                if (!aux.contains(possibleMatch) && input.getHosPrefMap().get(hospital).indexOf(possibleMatch) <
                        input.getHosPrefMap().get(hospital).indexOf(aux.get(aux.size() - 1)))
                    if (partition.getMatching(possibleMatch) == null)
                        return false;
                    else {
                        if (input.getResPrefMap().get(possibleMatch).indexOf(hospital) < input.getResPrefMap().
                                get(possibleMatch).indexOf(partition.getMatching(possibleMatch).getHospital()))
                            return false;
                    }
            }
        }
        return true;
    }

    /**
     * <code>main</code> function.
     *
     * @param args a list of user-given arguments.
     */
    public static void main(String[] args) {
        ProblemInput problemInput = new ProblemInput();
        //This is test code for the Compulsory section!
        problemInput.generateInputBasic();
        //This is test code for the Optional and Bonus sections!
        //problemInput.generateInputFaker();
        //GaleShapley aux = new GaleShapley();
        FirstComeFirstServed aux = new FirstComeFirstServed();
        Partition partition = new Partition();
        aux.createMatchingList(problemInput, partition);
        System.out.println(partition);
        if (checkIfStableMatching(problemInput, partition)) {
            System.out.println("Matching is stable!");
        } else {
            System.out.println("Matching is unstable!");
        }
    }
}
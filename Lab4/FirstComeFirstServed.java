package Lab4;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * An implementation of a "first come, first served" algorithm.
 */
public class FirstComeFirstServed implements Algorithm {
    @Override
    public void createMatchingList(ProblemInput input, Partition partition) {
        List<Resident> resStillFree = new LinkedList<>(input.getResPrefMap().keySet());
        Map<Hospital, Integer> capacities = new LinkedHashMap<>();
        int aux;
        for (Hospital hospital : input.getHosPrefMap().keySet())
            capacities.put(hospital, hospital.getCapacity());
        for (Resident resident : resStillFree) {
            for (Hospital hospital : input.getResPrefMap().get(resident)) {
                aux = capacities.get(hospital);
                if (aux != 0) {
                    partition.addMatch(new Matching(hospital, resident));
                    capacities.put(hospital, aux - 1);
                    break;
                }
            }
        }
    }
}

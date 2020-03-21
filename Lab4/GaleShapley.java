package Lab4;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * An implementation of Gale-Shapley Algorithm.
 */
public class GaleShapley implements Algorithm {
    @Override
    public void createMatchingList(ProblemInput input, Partition partition) {
        List<Resident> resStillFree = new LinkedList<>(input.getResPrefMap().keySet());
        Map<Hospital, Integer> capacities = new LinkedHashMap<>();
        int aux;
        for (Hospital hospital : input.getHosPrefMap().keySet())
            capacities.put(hospital, hospital.getCapacity());
        while (!resStillFree.isEmpty()) {
            Resident candidate = resStillFree.remove(0);
            for (Hospital hospital : input.getResPrefMap().get(candidate)) {
                aux = capacities.get(hospital);
                if (partition.getMatching(hospital) == null || aux != 0) {
                    partition.addMatch(new Matching(hospital, candidate));
                    capacities.put(hospital, aux - 1);
                    break;
                } else {
                    List<Resident> competitorList = partition.findPartnerList(hospital);
                    for (Resident competitor : competitorList) {
                        if (input.getHosPrefMap().get(hospital).indexOf(candidate) <
                                input.getHosPrefMap().get(hospital).indexOf(competitor)) {
                            partition.getMatching(competitor).setResident(candidate);
                            resStillFree.add(competitor);
                            break;
                        }
                    }
                }
            }
        }
    }
}

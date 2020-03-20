package Lab4;

import java.util.*;

public class Partition {
    private List<Matching> partition = new ArrayList<>();

    public void addMatch(Matching match) {
        partition.add(match);
    }

    public List<Matching> getPartition() {
        return partition;
    }

    public List<Resident> findPartnerList(Hospital hospital) {
        List<Resident> res = new LinkedList<>();
        for (Matching match : partition) {
            if (match.getHospital().equals(hospital))
                res.add(match.getResident());
        }
        return res;
    }

    public Set<Hospital> getHospitals(){
        Set<Hospital> res = new TreeSet<>();
        for (Matching match : partition) {
            res.add(match.getHospital());
        }
        return res;
    }

    public Matching getMatching(Hospital hospital) {
        for (Matching match : partition) {
            if (match.getHospital().equals(hospital))
                return match;
        }
        return null;
    }

    public Matching getMatching(Resident resident) {
        for (Matching match : partition) {
            if (match.getResident().equals(resident))
                return match;
        }
        return null;
    }

    @Override
    public String toString() {
        return partition.toString();
    }
}

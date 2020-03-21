package Lab4;

import java.util.*;

/**
 * A representation of (Resident:Hospital) partition.
 */
public class Partition {
    private List<Matching> partition = new ArrayList<>();

    /**
     * Adds a matching to the partition's list.
     *
     * @param match the matching to be added.
     */
    public void addMatch(Matching match) {
        partition.add(match);
    }

    /**
     * Returns the list of matches.
     *
     * @return the partition's list of matches.
     */
    public List<Matching> getPartition() {
        return partition;
    }

    /**
     * Returns a hospital's list of matching partners.
     *
     * @param hospital a hospital.
     * @return a list of the hospital's matching partners.
     */
    public List<Resident> findPartnerList(Hospital hospital) {
        List<Resident> res = new LinkedList<>();
        for (Matching match : partition) {
            if (match.getHospital().equals(hospital))
                res.add(match.getResident());
        }
        return res;
    }

    /**
     * Returns a set of all the hospitals in the partition's list.
     *
     * @return a <code>Set</code> of all the hospitals in the partition's list.
     */
    public Set<Hospital> getHospitals() {
        Set<Hospital> res = new TreeSet<>();
        for (Matching match : partition) {
            res.add(match.getHospital());
        }
        return res;
    }

    /**
     * Returns the first found matching with the corresponding hospital.
     *
     * @param hospital a hospital
     * @return the first matching which contains the <code>Hospital</code> <b>hospital</b>.
     */
    public Matching getMatching(Hospital hospital) {
        for (Matching match : partition) {
            if (match.getHospital().equals(hospital))
                return match;
        }
        return null;
    }

    /**
     * Returns the first found matching with the corresponding resident.
     *
     * @param resident a hospital
     * @return the first matching which contains the <code>Resident</code> <b>resident</b>.
     */
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

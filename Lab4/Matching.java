package Lab4;

/**
 * A representation of a <i>(Resident:Hospital)</i> matching.
 */
public class Matching {
    private Hospital hospital;
    private Resident resident;

    /**
     * Creates a new matching.
     *
     * @param hospital the hospital.
     * @param resident the resident.
     */
    public Matching(Hospital hospital, Resident resident) {
        this.hospital = hospital;
        this.resident = resident;
    }

    /**
     * Changes the hospital in the matching.
     *
     * @param hospital the new hospital.
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    /**
     * Changes the resident in the matching.
     *
     * @param resident the new resident.
     */
    public void setResident(Resident resident) {
        this.resident = resident;
    }

    /**
     * Returns the hospital from the matching.
     *
     * @return the matching's hospital.
     */
    public Hospital getHospital() {
        return hospital;
    }

    /**
     * Returns the resident from the matching.
     *
     * @return the matching's resident.
     */
    public Resident getResident() {
        return resident;
    }

    /**
     * check if a <i>(Resident:Hospital)</i> pair is equal to the matching.
     *
     * @param res a resident.
     * @param hos a hospital.
     * @return <code>true</code> if they are equal and <code>false</code> otherwise.
     */
    public boolean checkIfEqual(Resident res, Hospital hos) {
        return hospital.equals(hos) && resident.equals(res);
    }

    @Override
    public String toString() {
        return "(" + resident.getName() +
                ":" + hospital.getName() +
                ')';
    }
}

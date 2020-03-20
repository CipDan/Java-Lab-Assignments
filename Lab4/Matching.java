package Lab4;

public class Matching {
    private Hospital hospital;
    private Resident resident;

    public Matching(Hospital hospital, Resident resident) {
        this.hospital = hospital;
        this.resident = resident;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public Resident getResident() {
        return resident;
    }

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

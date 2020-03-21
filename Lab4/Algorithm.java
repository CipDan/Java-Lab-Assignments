package Lab4;

/**
 * An interface for grouping algorithms for solving The Hospitals/Residents Problem (HR).
 */
public interface Algorithm {
    /**
     * Creates a list of matches which will be stored in <bold>partition</bold>.
     *
     * @param input     the problem's generated input.
     * @param partition the container in which the list will be saved.
     */
    void createMatchingList(ProblemInput input, Partition partition);
}

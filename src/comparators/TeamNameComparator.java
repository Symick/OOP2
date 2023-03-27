package comparators;

import practicumopdracht.models.Team;

import java.util.Comparator;

/**
 * Class that implements the Comparator interface.
 * This comparator sorts teams based on their name
 *
 * @author Julian Kruithof
 */
public class TeamNameComparator implements Comparator<Team> {
    private boolean sortDescending;

    /**
     * Constructor for comparator
     *
     * @param sortDescending if true, sort descending otherwise sort ascending.
     */
    public TeamNameComparator(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }

    @Override
    public int compare(Team o1, Team o2) {
        if (sortDescending) {
            return o2.getName().compareToIgnoreCase(o1.getName());
        }
        return o1.getName().compareToIgnoreCase(o2.getName());
    }

    public void setSortDescending(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }
}

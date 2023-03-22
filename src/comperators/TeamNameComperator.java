package comperators;

import practicumopdracht.models.Team;

import java.util.Comparator;

public class TeamNameComperator implements Comparator<Team> {
    private boolean sortDescending;

    public TeamNameComperator(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }

    @Override
    public int compare(Team o1, Team o2) {
        if (sortDescending) {
            return o2.getName().compareToIgnoreCase(o1.getName());
        }
        return  o1.getName().compareToIgnoreCase(o2.getName());
    }

    public void setSortDescending(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }
}

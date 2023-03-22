package comperators;

import practicumopdracht.models.Driver;

import java.util.Comparator;

public class DriverChampionshipComperator implements Comparator<Driver> {

    private boolean sortDescending;
    private DriverNameComperator driverNameComperator;

    public DriverChampionshipComperator(boolean sortDescending) {
        this.sortDescending = sortDescending;
        driverNameComperator = new DriverNameComperator(false);
    }

    @Override
    public int compare(Driver o1, Driver o2) {
        if (sortDescending) {
            int compare = Integer.compare(o1.getChampionships(), o2.getChampionships());
            return compare == 0 ? driverNameComperator.compare(o1, o2) : compare;
        }
        int compare = Integer.compare(o2.getChampionships(), o1.getChampionships());
        return compare == 0 ? driverNameComperator.compare(o1, o2) : compare;
    }
}

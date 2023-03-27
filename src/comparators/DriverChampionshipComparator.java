package comparators;

import practicumopdracht.models.Driver;

import java.util.Comparator;

/**
 * Class that implements the Comparator interface.
 * This comparator sorts drivers based on the amount of championships the driver won.
 * If a driver has the same amount of championships, the drivers are sorted by name alphabetically
 *
 * @author Julian Kruithof
 */
public class DriverChampionshipComparator implements Comparator<Driver> {
    private boolean sortDescending;
    private DriverNameComparator driverNameComparator;

    /**
     * Constructor for comparator
     *
     * @param sortDescending if true, sort descending otherwise sort ascending.
     */
    public DriverChampionshipComparator(boolean sortDescending) {
        this.sortDescending = sortDescending;
        driverNameComparator = new DriverNameComparator(false);
    }

    @Override
    public int compare(Driver o1, Driver o2) {
        if (sortDescending) {
            int compare = Integer.compare(o1.getChampionships(), o2.getChampionships());
            return compare == 0 ? driverNameComparator.compare(o1, o2) : compare;
        }
        int compare = Integer.compare(o2.getChampionships(), o1.getChampionships());
        return compare == 0 ? driverNameComparator.compare(o1, o2) : compare;
    }
}

package comparators;

import practicumopdracht.models.Driver;

import java.util.Comparator;

/**
 * Class that implements the Comparator interface.
 * This comparator sorts drivers based on their name
 *
 * @author Julian Kruithof
 */
public class DriverNameComparator implements Comparator<Driver> {
    private boolean sortDescending;

    /**
     * Constructor for comparator
     *
     * @param sortDescending if true, sort descending otherwise sort ascending.
     */
    public DriverNameComparator(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }

    @Override
    public int compare(Driver o1, Driver o2) {
        if (sortDescending) {
            return o2.getName().compareToIgnoreCase(o1.getName());
        }
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}

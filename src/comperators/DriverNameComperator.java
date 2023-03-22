package comperators;

import practicumopdracht.models.Driver;

import java.util.Comparator;

public class DriverNameComperator implements Comparator<Driver> {
    private boolean sortDescending;

    public DriverNameComperator(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }

    @Override
    public int compare(Driver o1, Driver o2) {
        if (sortDescending) {
            return o2.getName().compareToIgnoreCase(o1.getName());
        }
        return  o1.getName().compareToIgnoreCase(o2.getName());
    }
}

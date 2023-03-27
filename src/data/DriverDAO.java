package data;

import practicumopdracht.models.Driver;
import practicumopdracht.models.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for DriverDAO with basic operation used for each individual driver dao
 *
 * @author Julian
 */
public abstract class DriverDAO implements DAO<Driver> {
    protected List<Driver> objects;

    /**
     * Constructor
     * initiates new list
     */
    public DriverDAO() {
        objects = new ArrayList<>();
    }

    @Override
    public List<Driver> getAll() {
        return objects;
    }

    /**
     * Get all drivers driving for a certain team
     *
     * @param object - team object which drivers need to be found for
     * @return a list of drivers driving for a certain team
     */
    public List<Driver> getAllFor(Team object) {
        List<Driver> driversForTeam = new ArrayList<>();
        for (Driver driver : objects) {
            if (driver.getBelongsTo().equals(object)) {
                driversForTeam.add(driver);
            }
        }
        return driversForTeam;
    }

    @Override
    public void addOrUpdate(Driver object) {
        if (objects.contains(object)) {
            return;
        }
        objects.add(object);
    }

    @Override
    public void remove(Driver object) {
        objects.remove(object);

    }

    @Override
    public abstract boolean load();

    @Override
    public abstract boolean save();
}

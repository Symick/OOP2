package data;

import practicumopdracht.models.Driver;
import practicumopdracht.models.Team;

import java.util.ArrayList;
import java.util.List;

public abstract class DriverDAO implements DAO<Driver> {
    protected List<Driver> objects;

    public DriverDAO() {
        objects = new ArrayList<>();
    }

    @Override
    public List<Driver> getAll() {
        return objects;
    }

    public List<Driver> getAllFor(Team object) {
        List<Driver> driversForTeam = new ArrayList<>();
        for (Driver driver: objects) {
            if(driver.getBelongsTo().equals(object)) {
                driversForTeam.add(driver);
            }
        }
        return driversForTeam;
    }

    @Override
    public void addOrUpdate(Driver object) {
        if(objects.contains(object)) {
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

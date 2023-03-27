package data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Driver;

import java.time.LocalDate;

/**
 * Dummy dao for driver model
 *
 * @author Julian Kruithof
 */
public class DummyDriverDAO extends DriverDAO {
    TeamDAO dummyTeamDAO = MainApplication.getTeamDAO();

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        objects.add(new Driver(dummyTeamDAO.getById(0), "First Driver", "Max Verstappen",
                LocalDate.of(1997, 9, 30), 163, true, 2011.5, 2));
        objects.add(new Driver(dummyTeamDAO.getById(0), "Second Driver", "Sergio Perez",
                LocalDate.of(1990, 1, 26), 235, true, 1201, 0));
        objects.add(new Driver(dummyTeamDAO.getById(2), "Second Driver", "Markus Winkelhock",
                LocalDate.of(1980, 6, 13), 1, false, 0, 0));
        return true;
    }
}

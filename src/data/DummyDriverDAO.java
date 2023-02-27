package data;

import practicumopdracht.Main;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Driver;
import practicumopdracht.models.Team;

import java.time.LocalDate;

public class DummyDriverDAO extends DriverDAO {
    DummyTeamDAO dummyTeamDAO = (DummyTeamDAO) MainApplication.getTeamDAO();

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

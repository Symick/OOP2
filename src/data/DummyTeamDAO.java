package data;

import practicumopdracht.models.Team;

public class DummyTeamDAO extends TeamDAO{
    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        objects.add(new Team("Red Bull Racing", 2005, true, 5));
        objects.add(new Team("Scuderia Ferrari", 1950, true, 16));
        objects.add(new Team("Spyker F1 Team", 2007, false, 0));

        return true;
    }
}

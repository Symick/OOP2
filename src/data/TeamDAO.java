package data;


import practicumopdracht.models.Team;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for handling basic functions all TeamDAOs should handle
 * implements DAO
 *
 * @author Julian Kruithof
 */
public abstract class TeamDAO implements DAO<Team> {
    protected List<Team> objects;

    /**
     * Constructor
     * initiates new list.
     */
    public TeamDAO() {
        objects = new ArrayList<>();
    }

    /**
     * get a team from its given id
     *
     * @param id - id of a team
     * @return team corresponding to the given id
     */
    public Team getById(int id) {
        try {
            return objects.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get the id correspondig to a team
     *
     * @param team - a team to find the id of
     * @return id of the team or -1 if the team isn't in the list.
     */
    public int getIdFor(Team team) {
        return objects.contains(team) ? objects.indexOf(team) : -1;
    }

    @Override
    public List<Team> getAll() {
        return objects;
    }

    @Override
    public void addOrUpdate(Team object) {
        if (objects.contains(object)) {
            return;
        }
        objects.add(object);
    }

    @Override
    public void remove(Team object) {
        objects.remove(object);
    }

    @Override
    public abstract boolean save();

    @Override
    public abstract boolean load();
}

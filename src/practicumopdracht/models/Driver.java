package practicumopdracht.models;

import practicumopdracht.models.Team;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Model for a driver object, with a link to a Team.
 *
 * @author Julian Kruithof
 */
public class Driver {
    private Team belongsTo;
    private String role;
    private String name;
    private LocalDate birthday;
    private int completedRaces;
    private boolean active;
    private double points;
    private int championships;

    /**
     * Constructor
     *
     * @param belongsTo - team driver has last driven for
     * @param role - the role which the driver has in the team. (first, second, third, test, etc) driver
     * @param name - name of driver
     * @param birthday - birthday of drivers
     * @param completedRaces - amount of races driven in formula 1
     * @param active - is currently driving for the team
     * @param points - amount of points accumulated in formula 1
     * @param championships - amount of driver championships won
     */
    public Driver(Team belongsTo, String role, String name, LocalDate birthday, int completedRaces, boolean active, double points, int championships) {
        this.belongsTo = belongsTo;
        this.role = role;
        this.name = name;
        this.birthday = birthday;
        this.completedRaces = completedRaces;
        this.active = active;
        this.points = points;
        this.championships = championships;
    }

    public Team getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(Team belongsTo) {
        this.belongsTo = belongsTo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getCompletedRaces() {
        return completedRaces;
    }

    public void setCompletedRaces(int completedRaces) {
        this.completedRaces = completedRaces;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getChampionships() {
        return championships;
    }

    public void setChampionships(int championships) {
        this.championships = championships;
    }

    private static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("%s\nrole: %s, birthday: %s, championships: %d\ncompleted races: %d, points: %.2f, %s",
                name, role, formatDate(birthday), championships, completedRaces, points, active ? "active" : "not active");
    }
}

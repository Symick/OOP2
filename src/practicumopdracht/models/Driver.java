package practicumopdracht.models;

import practicumopdracht.models.Team;

import java.time.LocalDate;

public class Driver {
    private Team belongsTo;
    private String role;
    private String name;
    private LocalDate birthday;
    private int completedRaces;
    private boolean active;
    private double points;
    private int championships;

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

    @Override
    public String toString() {
        return "Driver{" +
                "belongsTo=" + belongsTo +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", completedRaces=" + completedRaces +
                ", active=" + active +
                ", points=" + points +
                ", championships=" + championships +
                '}';
    }
}

package practicumopdracht.models;

import java.util.Objects;

/**
 * Model for a team object
 *
 * @author Julian Kruithof
 */
public class Team {
    private String name;
    private int firstEntryYear;
    private boolean active;
    private int teamChampionships;

    /**
     * Constructor
     *
     * @param name              - name of the formula 1 team
     * @param firstEntryYear    - year the team first entered formula 1
     * @param active            - detemines if team is currently operating in formula 1
     * @param teamChampionships - amount of constructor championships won
     */
    public Team(String name, int firstEntryYear, boolean active, int teamChampionships) {
        this.name = name;
        this.firstEntryYear = firstEntryYear;
        this.active = active;
        this.teamChampionships = teamChampionships;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFirstEntryYear() {
        return firstEntryYear;
    }

    public void setFirstEntryYear(int firstEntryYear) {
        this.firstEntryYear = firstEntryYear;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTeamChampionships() {
        return teamChampionships;
    }

    public void setTeamChampionships(int teamChampionships) {
        this.teamChampionships = teamChampionships;
    }

    @Override
    public String toString() {
        return String.format("%s\nfirst year active: %d, %s, championships: %d",
                name, firstEntryYear, active ? "active" : "not active", teamChampionships);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Team team) {
            return firstEntryYear == team.firstEntryYear &&
                    active == team.active &&
                    teamChampionships == team.teamChampionships &&
                    name.equals(team.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firstEntryYear, active, teamChampionships);
    }
}

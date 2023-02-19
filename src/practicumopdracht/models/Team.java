package practicumopdracht.models;

public class Team {
    private String name;
    private int firstEntryYear;
    private boolean active;
    private int teamChampionships;

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
}

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
}

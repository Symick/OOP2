package practicumopdracht.controllers;

import practicumopdracht.MainApplication;
import practicumopdracht.views.TeamView;
import practicumopdracht.views.View;

public class TeamController extends Controller {
    private TeamView teamView;

    public TeamController() {
        this.teamView = new TeamView();

    }

    @Override
    public View getView() {
        return teamView;
    }
}

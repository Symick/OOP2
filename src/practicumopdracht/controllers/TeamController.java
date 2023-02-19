package practicumopdracht.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Team;
import practicumopdracht.views.TeamView;
import practicumopdracht.views.View;

import java.util.Optional;

public class TeamController extends Controller {
    private TeamView teamView;
    private Team team;
    public TeamController() {
        teamView = new TeamView();

        teamView.getCreateBtn().setOnAction(actionEvent -> handleCreateTeam());
        teamView.getDeleteBtn().setOnAction(actionEvent -> handleDeleteTeam());
        teamView.getSaveBtn().setOnAction(actionEvent -> handleSaveTeam());
        teamView.getSwitchViewBtn().setOnAction(actionEvent -> handleSwitchView());
    }
    private void handleCreateTeam() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nieuw");
        alert.show();

    }
    private void handleDeleteTeam() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "verwijder");
        alert.show();
    }
    private void handleSaveTeam() {

    }
    private void handleSwitchView() {
        MainApplication.switchController(new DriverController());

    }

    @Override
    public View getView() {
        return teamView;
    }
}

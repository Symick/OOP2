package practicumopdracht.controllers;

import javafx.scene.control.Alert;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Team;
import practicumopdracht.views.TeamView;
import practicumopdracht.views.View;
import java.time.LocalDate;

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
        alert.showAndWait();
    }
    private void handleSaveTeam() {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("opslaan");
        warning.setTitle("opslaan");
        StringBuilder str = new StringBuilder();

        //check if name is empty
        if (teamView.getNameTxf().getText().isBlank()) {
            str.append("There where some mistakes: \n-Team name must be filled in!\n");
            teamView.getNameTxf().setStyle("-fx-border-color: RED");
        }
        //check if first entry year is an integer
        if (!isInteger(teamView.getFirstEntryYearTxf().getText())){
            str.append("-Please enter a number in the 'first competed in year' field ! For example 2004\n");
            teamView.getFirstEntryYearTxf().setStyle("-fx-border-color: RED");
        } else {
            //check if championship is an integer
            LocalDate now = LocalDate.now();
            final int START_FORMULA_1 = 1950;
            if (Integer.parseInt(teamView.getFirstEntryYearTxf().getText()) < START_FORMULA_1
            || Integer.parseInt(teamView.getFirstEntryYearTxf().getText()) > now.getYear()) {
                str.append("-A team couldn't have entered formula 1 before " + START_FORMULA_1 + " and after " + now.getYear() + "!\n");
                teamView.getFirstEntryYearTxf().setStyle("-fx-border-color: RED");
            }
        }

        if (!isInteger(teamView.getChampionshipTxf().getText())) {
            str.append("-Please enter a number in the 'championships' field!\n");
            teamView.getChampionshipTxf().setStyle("-fx-border-color: RED");
        }

        //show correct Alert depending on error handling
        if(!str.isEmpty()) {
            warning.setHeaderText(str.toString());
            warning.showAndWait();
        } else {
            resetBorderColor(teamView.getNameTxf(), teamView.getChampionshipTxf(), teamView.getFirstEntryYearTxf());
            team = new Team(
                    teamView.getNameTxf().getText(),
                    Integer.parseInt(teamView.getFirstEntryYearTxf().getText()),
                    teamView.getIsActiveCheckbox().isSelected(),
                    Integer.parseInt(teamView.getChampionshipTxf().getText())
            );
            info.setHeaderText(team.toString());

            //reset fields
            clearTextFields(teamView.getNameTxf(), teamView.getChampionshipTxf(), teamView.getFirstEntryYearTxf());
            teamView.getIsActiveCheckbox().setSelected(false);
            info.showAndWait();
        }

    }
    private void handleSwitchView() {
        MainApplication.switchController(new DriverController());

    }

    @Override
    public View getView() {
        return teamView;
    }
}

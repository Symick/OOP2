package practicumopdracht.controllers;

import data.DriverDAO;
import data.TeamDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Driver;
import practicumopdracht.models.Team;
import practicumopdracht.views.TeamView;
import practicumopdracht.views.View;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Controller for the Team model and view
 * Extends from Controller
 *
 * @author Julian Kruithof
 */
public class TeamController extends Controller {
    private TeamView teamView;
    private TeamDAO teamDAO;
    private DriverDAO driverDAO;
    private boolean teamSelected = false;
    private int selectedTeam;
    List<Team> teams;
    /**
     * Constructor for a teamController
     * initiates a new view and add listeners to the buttons
     */
    public TeamController() {
        driverDAO = MainApplication.getDriverDAO();
        teamDAO = MainApplication.getTeamDAO();


        teamView = new TeamView();
        //fill in listview
        teams = teamDAO.getAll();
        ObservableList<Team> observableList = FXCollections.observableList(teams);
        if (teamView.getListView().getItems().isEmpty()) {
            teamView.getListView().setItems(observableList);
        }
        //listening for change of selection in listview
        teamView.getListView()
                .getSelectionModel().
                selectedItemProperty().
                addListener((observable -> {
                    if (teamView.getListView().getSelectionModel().getSelectedItem() == null){
                        handleNewTeam();
                    } else {
                        teamSelected = true;
                        selectedTeam = teamView.getListView().getSelectionModel().getSelectedIndex();
                        loadInputFields(teamView.getListView().getSelectionModel().getSelectedItem());
                    }
                }));

        teamView.getCreateBtn().setOnAction(actionEvent -> handleNewTeam());
        teamView.getDeleteBtn().setOnAction(actionEvent -> handleDeleteTeam());
        teamView.getSaveBtn().setOnAction(actionEvent -> handleSaveTeam());
        teamView.getSwitchViewBtn().setOnAction(actionEvent -> handleSwitchView());
    }

    /**
     * handle an event when new button is clicked. Deselects listview and empties inputs to let users create a new Team
     */
    private void handleNewTeam() {
        if (!teamSelected) {
            return;
        }
        clearTextFields(teamView.getNameTxf(), teamView.getChampionshipTxf(), teamView.getFirstEntryYearTxf());
        teamView.getIsActiveCheckbox().setSelected(false);
        teamView.getListView().getSelectionModel().clearSelection();
        teamSelected = false;
    }

    /**
     * handle an event when the delete button is clicked. Team is removed from listview and DAO
     */
    private void handleDeleteTeam() {

        Team team = teamView.getListView().getSelectionModel().getSelectedItem();
        if (team == null) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("You sure?");
        alert.setHeaderText("Are you sure you want to delete the team:" + team.getName());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() != ButtonType.OK) {
            return;
        }
        teamView.getListView().getItems().remove(team);
        teamDAO.remove(team);

        //remove drivers driving for the team that has been removed.
        List<Driver> drivers = driverDAO.getAllFor(team);
        for (Driver driver: drivers) {
            driverDAO.remove(driver);
        }
        teamView.getListView().getSelectionModel().clearSelection();
        teamSelected = false;
    }

    /**
     * handle event when save button is clicked. Team is either stored or updated in DAO and lisview
     */
    private void handleSaveTeam() {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Save");
        warning.setTitle("Save");
        StringBuilder str = new StringBuilder();

        //check if name is empty
        if (teamView.getNameTxf().getText().isBlank()) {
            str.append("-Team name must be filled in!\n");
            teamView.getNameTxf().setStyle("-fx-border-color: RED");
        }
        //check if first entry year is an integer
        if (!isInteger(teamView.getFirstEntryYearTxf().getText())){
            str.append("-Please enter a number in the 'first competed in year' field ! For example 2004\n");
            teamView.getFirstEntryYearTxf().setStyle("-fx-border-color: RED");
        } else {
            //check if Local date is bigger than 1950 and lower than the current year is an integer
            LocalDate now = LocalDate.now();
            final int START_FORMULA_1 = 1950;
            if (Integer.parseInt(teamView.getFirstEntryYearTxf().getText()) < START_FORMULA_1
            || Integer.parseInt(teamView.getFirstEntryYearTxf().getText()) > now.getYear()) {
                str.append("-A team couldn't have entered formula 1 before " + START_FORMULA_1 + " and after " + now.getYear() + "!\n");
                teamView.getFirstEntryYearTxf().setStyle("-fx-border-color: RED");
            }
        }
        //check if championship is an integer
        if (!isInteger(teamView.getChampionshipTxf().getText())) {
            str.append("-Please enter a number in the 'championships' field!\n");
            teamView.getChampionshipTxf().setStyle("-fx-border-color: RED");
        }

        //show correct Alert depending on error handling
        if(!str.isEmpty()) {
            str.insert(0, "There where some mistakes: \n");
            warning.setHeaderText(str.toString());
            warning.showAndWait();
        } else {
            resetBorderColor(teamView.getNameTxf(), teamView.getChampionshipTxf(), teamView.getFirstEntryYearTxf());
            Team team;
            //check if team there is a team selected, if so update the model, else create a new team model.
            if (teamSelected) {
                team = teamView.getListView().getSelectionModel().getSelectedItem();
                team.setName(teamView.getNameTxf().getText());
                team.setTeamChampionships(Integer.parseInt(teamView.getChampionshipTxf().getText()));
                team.setFirstEntryYear(Integer.parseInt(teamView.getFirstEntryYearTxf().getText()));
                team.setActive(teamView.getIsActiveCheckbox().isSelected());
                teamView.getListView().refresh();
            } else {
                team = new Team(teamView.getNameTxf().getText(), Integer.parseInt(teamView.getFirstEntryYearTxf().getText()),
                        teamView.getIsActiveCheckbox().isSelected(), Integer.parseInt(teamView.getChampionshipTxf().getText()));
                teamView.getListView().getItems().add(team);
            }
            teamDAO.addOrUpdate(team);
        }

    }

    /**
     * handle event when switch view button is clicked. start a new Driverview depending on the selected Team model
     */
    private void handleSwitchView() {
        if (teamSelected) {
            MainApplication.switchController(new DriverController(teamView.getListView().getItems().get(selectedTeam)));
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection");
            alert.setHeaderText("Please select a team in the list before switching");
        }
    }

    private void loadInputFields(Team team) {
        teamView.getNameTxf().setText(team.getName());
        teamView.getFirstEntryYearTxf().setText(Integer.toString(team.getFirstEntryYear()));
        teamView.getChampionshipTxf().setText(Integer.toString(team.getTeamChampionships()));
        teamView.getIsActiveCheckbox().setSelected(team.isActive());
    }

    /**
     * get the current view in place
     * @return a teamView instance
     */
    @Override
    public View getView() {
        return teamView;
    }
}

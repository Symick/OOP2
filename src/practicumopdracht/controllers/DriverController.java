package practicumopdracht.controllers;

import comperators.DriverChampionshipComperator;
import comperators.DriverNameComperator;
import data.DriverDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Driver;
import practicumopdracht.models.Team;

import practicumopdracht.views.DriverView;
import practicumopdracht.views.View;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * class for the controller of the Driver entity
 * extends Controller
 *
 * @author Julian Kruithof
 */
public class DriverController extends Controller {
    private DriverView driverView;
    private DriverDAO driverDAO;
    private boolean driverSelected;
    private Comparator<Driver> comparator = new DriverNameComperator(false);
    /**
     * Constructor for a DriverController
     * initiates a new view and add listeners to the buttons
     */
    public DriverController(Team team) {
        driverView = new DriverView();
        driverDAO = MainApplication.getDriverDAO();
        //load in combobox
        List<Team> teams = MainApplication.getTeamDAO().getAll();
        ObservableList<Team> observableTeams = FXCollections.observableList(teams);
        driverView.getComboBox().setItems(observableTeams);
        driverView.getComboBox().getSelectionModel().select(team);

        //change listview depending on selected in combobox
        driverView.getComboBox().setOnAction(actionEvent -> loadListView(driverView.getComboBox().getSelectionModel().getSelectedItem()));

        //load in drivers from a team with was selected before view switch
        loadListView(team);

        //handle the event when a driver is selected
        driverView.getListView()
                .getSelectionModel().
                selectedItemProperty().
                addListener((observable -> {
                    if (driverView.getListView().getSelectionModel().getSelectedItem() == null) {
                        handleNewDriver();
                    } else {
                        driverSelected = true;
                        loadInputFields(driverView.getListView().getSelectionModel().getSelectedItem());
                    }
                }));

        driverView.getRadioButtonGroup().getToggles().get(0).setSelected(true);
        driverView.getRadioButtonGroup().selectedToggleProperty().addListener((observable ->
                initializeComperator((RadioButton) driverView.getRadioButtonGroup().getSelectedToggle())));


        // add eventlisteners to the all the buttons in the view
        driverView.getCreateBtn().setOnAction(actionEvent -> handleNewDriver());
        driverView.getDeleteBtn().setOnAction(actionEvent -> handleDeleteDriver());
        driverView.getSaveBtn().setOnAction(actionEvent -> handleSaveDriver());
        driverView.getSwitchViewBtn().setOnAction(actionEvent -> handleSwitchView());
    }

    /**
     * Handle event when the new button is clicked.
     * delected model in listview and clear the inputs to give the user the option to create a new
     * Driver modal
     */
    private void handleNewDriver() {
        if (!driverSelected) {
            return;
        }

        driverView.getListView().getSelectionModel().clearSelection();
        clearTextFields(driverView.getChampionshipsTfx(), driverView.getRoleTfx(), driverView.getPointsTfx(), driverView.getCompletedRacesTfx(),
                driverView.getNameTxf());
        driverView.getIsActiveCheckbox().setSelected(false);
        driverView.getBirthdatePicker().setValue(null);
        driverSelected = false;
    }

    /**
     * handle event when delete button is clicked delete model from the listview
     */
    private void handleDeleteDriver() {

        Driver driver = driverView.getListView().getSelectionModel().getSelectedItem();
        if (driver == null) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("You sure?");
        alert.setHeaderText("Are you sure you want to delete the driver: " + driver.getName());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() != ButtonType.OK) {
            return;
        }
        driverView.getListView().getItems().remove(driver);
        driverDAO.remove(driver);
        driverView.getListView().getSelectionModel().clearSelection();
        sortListView();
        driverSelected = false;
    }

    /**
     * handle event when switch button is clicked. aka switch back to masterview
     */
    private void handleSwitchView() {
        MainApplication.switchController(new TeamController());
    }

    /**
     * Handle event when save button is clicked. aka adding or updating a model to the listview
     */
    private void handleSaveDriver() {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Save");
        warning.setTitle("Save");
        //get input values
        String name = driverView.getNameTxf().getText();
        String role = driverView.getRoleTfx().getText();
        String championships = driverView.getChampionshipsTfx().getText();
        String points = driverView.getPointsTfx().getText();
        String completedRaces = driverView.getCompletedRacesTfx().getText();


        StringBuilder str = new StringBuilder();

        //check if name is empty
        if (name.isBlank()) {
            str.append("-Name must be filled in!\n");
            driverView.getNameTxf().setStyle("-fx-border-color: RED");
        }

        //check if role is empty
        if (role.isBlank()) {
            str.append("-Role must be filled in!\n");
            driverView.getRoleTfx().setStyle("-fx-border-color: RED");
        }

        //check if championships is an integer
        if (!isInteger(championships)) {
            str.append("-Please enter a number in the 'championships' field!\n");
            driverView.getChampionshipsTfx().setStyle("-fx-border-color: RED");
        }

        //check if completed races is an integer
        if (!isInteger(completedRaces)) {
            str.append("-Please enter a number in the 'completed races' field!\n");
            driverView.getCompletedRacesTfx().setStyle("-fx-border-color: RED");
        }

        // check if points is a double
        if (!isDouble(points)) {
            str.append("-Please enter a decimal number in the 'points' field!\n");
            driverView.getPointsTfx().setStyle("-fx-border-color: RED");
        }

        // check if birthday is a valid date
        if (driverView.getBirthdatePicker().getValue() == null) {
            str.append("-Please enter a valid birthdate! Use the format dd-mm-yyyy\n");
            driverView.getBirthdatePicker().setStyle("-fx-border-color: RED");
        }

        //if there are error messages show error alert otherwise create driver and show alert
        if (!str.isEmpty()) {
            str.insert(0, "There where some mistakes: \n");
            warning.setHeaderText(str.toString());
            warning.showAndWait();
        } else {
            resetBorderColor(driverView.getChampionshipsTfx(), driverView.getRoleTfx(), driverView.getPointsTfx(), driverView.getCompletedRacesTfx(),
                    driverView.getNameTxf());
            driverView.getBirthdatePicker().setStyle("-fx-border-color: none");

            //update driver if one is selected else add a new driver
            Driver driver;
            if (driverSelected) {
                driver = driverView.getListView().getSelectionModel().getSelectedItem();
                driver.setName(driverView.getNameTxf().getText());
                driver.setRole(driverView.getRoleTfx().getText());
                driver.setBirthday(driverView.getBirthdatePicker().getValue());
                driver.setChampionships(Integer.parseInt(driverView.getChampionshipsTfx().getText()));
                driver.setCompletedRaces(Integer.parseInt(driverView.getCompletedRacesTfx().getText()));
                driver.setPoints(Double.parseDouble(driverView.getPointsTfx().getText()));
                driver.setActive(driverView.getIsActiveCheckbox().isSelected());
                driverView.getListView().refresh();
            } else {
                driver = new Driver(driverView.getComboBox().getSelectionModel().getSelectedItem(),
                        driverView.getRoleTfx().getText(), driverView.getNameTxf().getText(),
                        driverView.getBirthdatePicker().getValue(),
                        Integer.parseInt(driverView.getCompletedRacesTfx().getText()),
                        driverView.getIsActiveCheckbox().isSelected(),
                        Double.parseDouble(driverView.getPointsTfx().getText()),
                        Integer.parseInt(driverView.getChampionshipsTfx().getText())
                );
                driverView.getListView().getItems().add(driver);

                clearTextFields(driverView.getChampionshipsTfx(), driverView.getRoleTfx(), driverView.getPointsTfx(), driverView.getCompletedRacesTfx(),
                        driverView.getNameTxf());
                driverView.getIsActiveCheckbox().setSelected(false);
                driverView.getBirthdatePicker().setValue(null);
            }
            driverDAO.addOrUpdate(driver);
            sortListView();

        }

    }

    /**
     * load in all the inputfields for the given driver
     *
     * @param driver driver object selected in listview
     */
    private void loadInputFields(Driver driver) {
        driverView.getNameTxf().setText(driver.getName());
        driverView.getRoleTfx().setText(driver.getRole());
        driverView.getBirthdatePicker().setValue(driver.getBirthday());
        driverView.getChampionshipsTfx().setText(Integer.toString(driver.getChampionships()));
        driverView.getCompletedRacesTfx().setText(Integer.toString(driver.getCompletedRaces()));
        driverView.getPointsTfx().setText(Double.toString(driver.getPoints()));
        driverView.getIsActiveCheckbox().setSelected(driver.isActive());
    }

    /**
     * Load in the listview for a given team
     *
     * @param team - team selected in combobox
     */
    private void loadListView(Team team) {
        List<Driver> drivers = driverDAO.getAllFor(team);
        ObservableList<Driver> observableList = FXCollections.observableList(drivers);
        driverView.getListView().setItems(observableList);
        sortListView();
    }

    private void initializeComperator(RadioButton button) {
        switch (button.getText()) {
            case "name (Z-A)":
                comparator = new DriverNameComperator(true);
                sortListView();
                break;
            case "championship (ASC)":
                comparator = new DriverChampionshipComperator(false);
                sortListView();
                break;
            case "championship (DESC)":
                comparator = new DriverChampionshipComperator(true);
                sortListView();
                break;
            default:
                comparator = new DriverNameComperator(false);
                sortListView();
                break;
        }
    }
    private void sortListView() {
        FXCollections.sort(driverView.getListView().getItems(), comparator);
    }

    @Override
    public View getView() {
        return driverView;
    }
}

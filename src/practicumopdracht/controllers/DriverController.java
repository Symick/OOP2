package practicumopdracht.controllers;

import javafx.scene.control.Alert;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Driver;
import practicumopdracht.models.Team;

import practicumopdracht.views.DriverView;
import practicumopdracht.views.View;

import java.time.LocalDate;

/**
 * class for the controller of the Driver entity
 * extends Controller
 *
 * @author Julian Kruithof
 */
public class DriverController extends Controller {
    private DriverView driverView;
    private LocalDate birthday;

    /**
     * Constructor for a DriverController
     * initiates a new view and add listeners to the buttons
     */
    public DriverController() {
        driverView = new DriverView();

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("New");
        alert.showAndWait();
    }

    /**
     * handle event when delete button is clicked delete model from the listview
     */
    private void handleDeleteDriver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Delete");
        alert.showAndWait();
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
        } else {
            birthday = driverView.getBirthdatePicker().getValue();
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

            Driver driver = new Driver(new Team("Mercedes", 2009, true, 9), role, name, birthday, Integer.parseInt(completedRaces),
                    driverView.getIsActiveCheckbox().isSelected(), Double.parseDouble(points), Integer.parseInt(championships));
            info.setHeaderText(driver.toString());
            info.showAndWait();

            clearTextFields(driverView.getChampionshipsTfx(), driverView.getRoleTfx(), driverView.getPointsTfx(), driverView.getCompletedRacesTfx(),
                    driverView.getNameTxf());
            driverView.getBirthdatePicker().setValue(null);
        }

    }

    @Override
    public View getView() {
        return driverView;
    }
}

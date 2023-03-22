package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Driver;
import practicumopdracht.models.Team;
import practicumopdracht.utils.LocalDateConverter;

import java.awt.*;

/**
 * view of a driver
 *
 * @author Julian
 */
public class DriverView extends View {
    private final double SPACING = 12;
    private final String GREEN_COLOR = "#26a514";
    private final String RED_COLOR = "#d81e05";

    private Button createBtn, saveBtn, deleteBtn, switchViewBtn;
    private TextField nameTxf, roleTfx, championshipsTfx, pointsTfx, completedRacesTfx;
    private DatePicker birthdatePicker;
    private CheckBox isActiveCheckbox;
    private ComboBox<Team> teams;
    private ListView<Driver> drivers;
    private ToggleGroup  radioButtonGroup;

    /**
     * Create the look of the driver view
     *
     * @return Parent object to create a scene
     */
    @Override
    protected Parent initializeView() {
        //create vBox pane
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(SPACING));

        /*Create a gridPane with styling
         *
         * The gridPane is used for input field asking the user for information about a F1-team
         * */
        GridPane gridPane = new GridPane();
        gridPane.setVgap(7.5);
        gridPane.setHgap(10);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(25);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(25);
        ColumnConstraints column3 = new ColumnConstraints();
        column2.setPercentWidth(25);
        ColumnConstraints column4 = new ColumnConstraints();
        column2.setPercentWidth(25);

        gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);
        gridPane.addColumn(0,
                new Label("Choose team:"),
                new Label("Name:"),
                new Label("Role:"),
                new Label("Birthday:"),
                new Label("Completed races:"),
                new Label("Active:"));

        //initialize input fields
        nameTxf = new TextField();
        championshipsTfx = new TextField();
        pointsTfx = new TextField();
        completedRacesTfx = new TextField();
        roleTfx = new TextField();
        birthdatePicker = new DatePicker();
        isActiveCheckbox = new CheckBox();

        birthdatePicker.setConverter(new LocalDateConverter());

        teams = new ComboBox<>();
        teams.setPrefWidth(Double.MAX_VALUE);
        gridPane.add(teams, 1, 0, 3, 1);
        gridPane.add(nameTxf, 1, 1, 3, 1);
        gridPane.add(roleTfx, 1, 2, 3, 1);
        gridPane.add(birthdatePicker, 1, 3);
        gridPane.add(new Label("Championships:"), 2, 3);
        gridPane.add(championshipsTfx, 3, 3);
        gridPane.add(completedRacesTfx, 1, 4);
        gridPane.add(new Label("Points:"), 2, 4);
        gridPane.add(pointsTfx, 3, 4);
        gridPane.add(isActiveCheckbox, 1, 5);
        saveBtn = createButton("Save", GREEN_COLOR);
        saveBtn.setPrefWidth(320);

        HBox teamCrudContainer = new HBox();
        teamCrudContainer.setSpacing(SPACING);
        drivers = new ListView<>();
        drivers.setPrefWidth(500);

        VBox buttonContainer = new VBox();
        createBtn = createButton("New driver", GREEN_COLOR);
        deleteBtn = createButton("Delete driver", RED_COLOR);
        switchViewBtn = createButton("View teams", null);
        buttonContainer.setSpacing(50);
        buttonContainer.getChildren().addAll(createBtn, deleteBtn, switchViewBtn);
        teamCrudContainer.getChildren().addAll(drivers, buttonContainer);

        //change text color of buttons
        setWhiteTextColor(saveBtn, createBtn, deleteBtn);

        //add radiobuttons to the view
        HBox radioButtonContainer = new HBox();
        radioButtonGroup = new ToggleGroup();
        RadioButton rb1 = new RadioButton("name (A-Z)");
        RadioButton rb2 = new RadioButton("name (Z-A)");
        RadioButton rb3 = new RadioButton("championship (ASC)");
        RadioButton rb4 = new RadioButton("championship (DESC)");

        radioButtonGroup.getToggles().addAll(rb1, rb2, rb3, rb4);
        radioButtonContainer.getChildren().addAll(rb1, rb2, rb3, rb4);
        radioButtonContainer.setSpacing(SPACING);

        vBox.getChildren().addAll(gridPane, saveBtn, teamCrudContainer, radioButtonContainer);
        vBox.setSpacing(SPACING);


        return vBox;
    }

    public Button getCreateBtn() {
        return createBtn;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public Button getSwitchViewBtn() {
        return switchViewBtn;
    }

    public TextField getNameTxf() {
        return nameTxf;
    }

    public TextField getRoleTfx() {
        return roleTfx;
    }

    public TextField getChampionshipsTfx() {
        return championshipsTfx;
    }

    public TextField getPointsTfx() {
        return pointsTfx;
    }

    public TextField getCompletedRacesTfx() {
        return completedRacesTfx;
    }

    public DatePicker getBirthdatePicker() {
        return birthdatePicker;
    }

    public CheckBox getIsActiveCheckbox() {
        return isActiveCheckbox;
    }

    public ComboBox<Team> getComboBox() {
        return teams;
    }

    public ListView<Driver> getListView() {
        return drivers;
    }

    public ToggleGroup getRadioButtonGroup() {
        return radioButtonGroup;
    }
}

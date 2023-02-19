package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Driver;
import practicumopdracht.models.Team;

/**
 * view of a driver
 *
 * @author Julian
 */
public class DriverView extends View {
    private final double SPACING = 12;
    private final String GREEN_COLOR = "#26a514";
    private final String RED_COLOR = "#d81e05";

    private Button createBtn;
    private Button saveBtn;
    private Button deleteBtn;
    private Button switchViewBtn;

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
                new Label("role:"),
                new Label("Birthday:"),
                new Label("Completed races:"),
                new Label("Active:"));

        ComboBox<Team> teams = new ComboBox<>();
        teams.setPrefWidth(Double.MAX_VALUE);
        gridPane.add(teams, 1, 0, 3, 1);
        gridPane.add(new TextField(), 1, 1, 3, 1);
        gridPane.add(new TextField(), 1, 2, 3, 1);
        gridPane.add(new DatePicker(), 1, 3);
        gridPane.add(new Label("Championships:"), 2, 3);
        gridPane.add(new TextField(), 3, 3);
        gridPane.add(new TextField(), 1, 4);
        gridPane.add(new Label("Points:"), 2, 4);
        gridPane.add(new TextField(), 3, 4);
        gridPane.add(new CheckBox(), 1, 5);
        saveBtn = createButton("Save", GREEN_COLOR);
        saveBtn.setPrefWidth(320);

        HBox teamCrudContainer = new HBox();
        teamCrudContainer.setSpacing(SPACING);
        ListView<Driver> drivers = new ListView<>();
        drivers.setPrefWidth(500);

        VBox buttonContainer = new VBox();
        createBtn = createButton("New driver", GREEN_COLOR);
        deleteBtn = createButton("Delete driver", RED_COLOR);
        switchViewBtn = createButton("Manage teams", null);
        buttonContainer.setSpacing(50);
        buttonContainer.getChildren().addAll(createBtn, deleteBtn, switchViewBtn);
        teamCrudContainer.getChildren().addAll(drivers, buttonContainer);

        //change text color of buttons
        setWhiteTextColor(saveBtn, createBtn, deleteBtn);

        vBox.getChildren().addAll(gridPane, saveBtn, teamCrudContainer);
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
}

package practicumopdracht.views;


import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Team;

/**
 * View of team
 *
 * @author Julian Kruithof
 */
public class TeamView extends View {
    private final double SPACING = 12;
    private final String GREEN_COLOR = "#26a514";
    private final String RED_COLOR = "#d81e05";

    private Button createBtn;
    private Button saveBtn;
    private Button deleteBtn;
    private Button switchViewBtn;

    /**
     * initialize the view of a Team
     *
     * @return parent object to create a scene
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
        column2.setPercentWidth(75);
        gridPane.getColumnConstraints().addAll(column1, column2);
        gridPane.addRow(0, new Label("team name:"), new TextField());
        gridPane.addRow(1, new Label("Started in year:"), new TextField());
        gridPane.addRow(2, new Label("Championships:"), new TextField());
        gridPane.addRow(3, new Label("team is currently active"), new CheckBox());

        saveBtn = createButton("Opslaan", GREEN_COLOR);
        saveBtn.setPrefWidth(320);

        HBox teamCrudContainer = new HBox();
        teamCrudContainer.setSpacing(SPACING);
        ListView<Team> teams = new ListView<>();
        teams.setPrefWidth(500);

        /*
         * styling of a vertical container containing buttons
         */
        VBox buttonContainer = new VBox();
        createBtn = createButton("Nieuw team", GREEN_COLOR);
        deleteBtn = createButton("Verwijder team", RED_COLOR);
        switchViewBtn = createButton("bekijk Coureurs", null);
        buttonContainer.setSpacing(50);
        buttonContainer.getChildren().addAll(createBtn, deleteBtn, switchViewBtn);
        teamCrudContainer.getChildren().addAll(teams, buttonContainer);

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

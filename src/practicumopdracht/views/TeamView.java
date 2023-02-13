package practicumopdracht.views;

import javafx.collections.ObservableMap;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.HLineTo;
import practicumopdracht.models.Team;


public class TeamView extends View{
    private final double SPACING = 12;
    private final String GREEN_COLOR = "#26a514";
    private final String RED_COLOR = "#d81e05";

    @Override
    protected Parent initializeView() {


        //create vBox pane
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(SPACING));

        /*Create a gridPane with styling
        *
        * The gridpane is used for inputfield asking the user for information about a F1-team
        * */
        GridPane gridPane = new GridPane();
        gridPane.setVgap(7.5);
        gridPane.setHgap(5);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(25);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(75);
        gridPane.getColumnConstraints().addAll(column1, column2);
        gridPane.addRow(0, new Label("teamname:"), new TextField());
        gridPane.addRow(1, new Label("Started in year:"), new TextField());
        gridPane.addRow(2, new Label("Championships:"), new TextField());
        gridPane.addRow(3, new Label("team is currently active") ,new CheckBox());

        Button saveBtn = createButton("Opslaan", GREEN_COLOR);
        saveBtn.setPrefWidth(320);

        HBox teamCrudContainer = new HBox();
        teamCrudContainer.setSpacing(SPACING);
        ListView teams = new ListView<Team>();
        teams.setPrefWidth(500);

        VBox buttonContainer = new VBox();
        Button createBtn = createButton("Nieuw team", GREEN_COLOR);
        Button deleteBtn = createButton("Verwijder team", RED_COLOR);
        Button switchViewBtn = createButton("bekijk Coureurs", null);
        buttonContainer.setSpacing(50);
        buttonContainer.getChildren().addAll(createBtn, deleteBtn, switchViewBtn);
        teamCrudContainer.getChildren().addAll(teams,buttonContainer);

        vBox.getChildren().addAll(gridPane, saveBtn, teamCrudContainer );
        vBox.setSpacing(SPACING);

        return vBox;
    }


}

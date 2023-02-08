package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Driver;


public class TeamView extends View{
    @Override
    protected Parent initializeView() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(7.5);
        gridPane.setHgap(5);

        VBox vBox = new VBox(gridPane);
        vBox.setPadding(new Insets(12));

        gridPane.addRow(0, new Label("teamname:"), new TextField());
        gridPane.addRow(1, new Label("Started in year:"), createTextField(16));
        gridPane.addRow(2, new Label("Championships:"), createTextField(16));
        gridPane.addRow(3, new Label("team is currently active") ,new CheckBox());

        vBox.getChildren().add(new Button("opslaan"));
        vBox.getChildren().add(new ListView<Driver>());
        return vBox;
    }
    private TextField createTextField(int prefColumnCount) {
        TextField textField = new TextField();
        textField.setPrefColumnCount(prefColumnCount);
        return textField;
    }
}

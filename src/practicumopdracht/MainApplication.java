package practicumopdracht;

import data.BinaryTeamDAO;
import data.DriverDAO;
import data.ObjectDriverDAO;
import data.TeamDAO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.TeamController;

import java.util.Optional;


public class MainApplication extends Application {
    private final static int HEIGHT = 500;
    private final static int WIDTH = 640;
    private static Stage stage;
    private static TeamDAO teamDAO = new BinaryTeamDAO();
    private static DriverDAO driverDAO = new ObjectDriverDAO();
    private final String TITLE = String.format("Practicumopdracht OOP2 - %s", Main.studentNaam);

    /**
     * Switch the active controller and view
     *
     * @param controller - The new controller which needs to be initialized
     */
    public static void switchController(Controller controller) {
        //check if there is already a scene created.
        if (stage.getScene() == null) {
            stage.setScene(new Scene(controller.getView().getRoot()));
        } else {
            //set the new scene to the width and height of the old view. This makes sure that the application doesn't resize.
            Scene oldScene = stage.getScene();
            stage.setScene(new Scene(controller.getView().getRoot(), oldScene.getWidth(), oldScene.getHeight()));
            stage.sizeToScene();

        }
    }

    public static DriverDAO getDriverDAO() {
        return driverDAO;
    }

    public static TeamDAO getTeamDAO() {
        return teamDAO;
    }

    @Override
    public void start(Stage stage) {
        MainApplication.stage = stage;
        if (!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        stage.setMinHeight(HEIGHT);
        stage.setHeight(HEIGHT);
        stage.setMinWidth(WIDTH);
        stage.setWidth(WIDTH);
        stage.setTitle(TITLE);
        switchController(new TeamController());
        stage.show();

        //If the application is closed with the x button in the top right, ask the user if they want to save their changes
        stage.setOnCloseRequest(event -> {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Sure you want to leave?");
            confirm.setHeaderText("Save information before closing the application?");
            Optional<ButtonType> result = confirm.showAndWait();

            //check if ok button is clicked if not abort save
            if (result.isPresent() && result.get() != ButtonType.OK) {
                Platform.exit();
                return;
            }
            teamDAO.save();
            driverDAO.save();
            Platform.exit();
        });
    }
}

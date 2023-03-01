package practicumopdracht;

import data.*;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.TeamController;



public class MainApplication extends Application {
    private final static int HEIGHT = 480;
    private final static int WIDTH = 640;
    private final String TITLE = String.format("Practicumopdracht OOP2 - %s", Main.studentNaam);
    private static Stage stage;
    private static TeamDAO teamDAO = new TextTeamDAO();
    private static DriverDAO driverDAO = new TextDriverDAO();

    @Override
    public void start(Stage stage) {
        MainApplication.stage = stage;
        if (!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }
        stage.setHeight(HEIGHT);
        stage.setWidth(WIDTH);
        stage.setTitle(TITLE);
        switchController(new TeamController());
        stage.show();
    }

    public static void switchController(Controller controller) {
        //check if there is already a scene created.
        if (stage.getScene() == null) {
            stage.setScene(new Scene(controller.getView().getRoot()));
        }else {
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
}

package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.TeamController;


public class MainApplication extends Application {
    private final int HEIGHT = 480;
    private final int WIDTH = 640;
    private final String TITLE = String.format("Practicumopdracht OOP2 - %s", Main.studentNaam);
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        MainApplication.stage = stage;
        if (!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        switchController(new TeamController());
        stage.show();
    }

    public static void switchController(Controller controller) {
        stage.setScene(new Scene(controller.getView().getRoot()));
    }

}

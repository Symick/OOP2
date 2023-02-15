package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.views.DriverView;
import practicumopdracht.views.TeamView;
import practicumopdracht.views.View;

public class MainApplication extends Application {
    private final View INITIAL_VIEW = new TeamView();
    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", Main.studentNaam));
        stage.setWidth(640);
        stage.setHeight(480);
        stage.setScene(new Scene(INITIAL_VIEW.getRoot()));
        stage.show();
    }

}

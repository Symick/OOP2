package practicumopdracht.views;

import javafx.scene.Parent;
import javafx.scene.control.Button;

public class DriverView extends View{
    @Override
    protected Parent initializeView() {
        return new Button("test");
    }
}

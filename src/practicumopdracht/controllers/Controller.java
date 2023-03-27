package practicumopdracht.controllers;

import javafx.scene.control.TextField;
import practicumopdracht.views.View;

/**
 * abstract controller class for all basic functions of all controllers
 *
 * @author Julian Kruithof
 */
public abstract class Controller {
    /**
     * clear the text from the textFields
     *
     * @param textFields variable amount of textField elements
     */
    protected static void clearTextFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setText("");
        }
    }

    /**
     * reset the border color for all textFields
     *
     * @param textFields variable amount of textField elements
     */
    protected static void resetBorderColor(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setStyle("-fx-border-color: none");
        }
    }

    public abstract View getView();
}

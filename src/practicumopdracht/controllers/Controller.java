package practicumopdracht.controllers;

import javafx.scene.control.TextField;
import practicumopdracht.views.View;

/**
 * abstract controller class for all basic functions of all controllers
 *
 * @author Julian Kruithof
 */
public abstract class Controller {
    public abstract View getView();

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

    /**
     * check if the given string is a parsable integer
     *
     * @param string
     * @return true if string is an integer, false if not
     */
    protected static boolean isInteger(String string) {
        try {
            int num = Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if a string is parsable to a double
     * @param string
     * @return true if string is a double, false if not
     */
    protected static boolean isDouble(String string) {
        try {
            double num = Double.parseDouble(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

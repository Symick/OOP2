package practicumopdracht.controllers;

import javafx.scene.control.TextField;
import practicumopdracht.views.View;

public abstract class Controller {
    public abstract View getView();

    protected static void clearTextFields(TextField ...textFields) {
        for (TextField textField : textFields) {
            textField.setText("");
        }
    }

    protected static void resetBorderColor(TextField ...textFields) {
        for (TextField textField : textFields) {
            textField.setStyle("-fx-border-color: none");
        }
    }
    protected static boolean isInteger(String string) {
        try {
            int num = Integer.parseInt(string);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
    protected static boolean isDouble(String string) {
        try {
            double num = Double.parseDouble(string);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

}

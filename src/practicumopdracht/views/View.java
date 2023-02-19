package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * abstract class to for the general setup for a view.
 *
 * @author Julian Kruithof
 */
public abstract class View {

    private Parent root;

    public View() {
        root = initializeView();
    }

    protected abstract Parent initializeView();


    public Parent getRoot() {
        return root;
    }

    /**
     * create a button with styling
     * Border radius of 15px, padding of 5 and 12 and a preferred width of 150
     *
     * @param text  text inside the button
     * @param color color of the button.
     * @return a Styled button
     */
    protected Button createButton(String text, String color) {
        Button button = new Button(text);
        button.setPadding(new Insets(5, 12, 5, 12));
        button.setMinWidth(100);
        button.setPrefWidth(150);
        StringBuilder str = new StringBuilder("-fx-border-radius: 15px;");
        if (color != null) {
            str.append(String.format("-fx-background-color: %s", color));
        }
        button.setStyle(str.toString());
        return button;
    }

    /**
     * change the text color of a variable amount of buttons to white, using the spread operator.
     * @param buttons a variable amount of buttons
     */
    protected void setWhiteTextColor(Button ...buttons) {
        for (Button button: buttons) {
            button.setTextFill(Color.WHITE);
        }
    }

}

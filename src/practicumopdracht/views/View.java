package practicumopdracht.views;

import javafx.scene.Parent;

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
}

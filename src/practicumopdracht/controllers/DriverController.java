package practicumopdracht.controllers;

import practicumopdracht.views.DriverView;
import practicumopdracht.views.View;

public class DriverController extends Controller{
    private DriverView driverView;

    public DriverController() {
        this.driverView = new DriverView();
    }

    @Override
    public View getView() {
        return driverView;
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import eventplannerappDELETETHISLATER.EventPlannerApp;
import javafx.event.Event;
import model.Location;
import viewmodel.ViewModel;

/**
 *
 * @author User
 */
public class ShowLocationInfoHandler extends VMEventHandler 
{

    Location relatedLocation;
    
    public ShowLocationInfoHandler(ViewModel parent, Location relatedLocation) 
    {
        super(parent);
        this.relatedLocation = relatedLocation;
    }

    @Override
    public void handle(Event event) 
    {
        Location[] params = {relatedLocation};
        EventPlannerApp.switchViewModel("/view/LocationInfoView.fxml", params); //TODO
    }
    
}

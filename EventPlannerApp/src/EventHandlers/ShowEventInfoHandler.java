/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import eventplannerappDELETETHISLATER.EventPlannerApp;
import model.Event;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;
import viewmodel.EventInfoViewModel;
import viewmodel.ViewModel;

/**
 *
 * @author User
 */
public class ShowEventInfoHandler extends VMEventHandler
{
    Event relatedEvent;
    
    public ShowEventInfoHandler(ViewModel parent, Event e) 
    {
        super(parent);
        relatedEvent = e;
    }

    @Override
    public void handle(javafx.event.Event event) 
    {
        Event[] params = {relatedEvent};
        EventPlannerApp.switchViewModel("/view/EventInfoView.fxml", params);
        int n= 1;
    }
    
}

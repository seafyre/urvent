/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import eventplannerappDELETETHISLATER.EventPlannerApp;
import model.Event;
import viewmodel.ViewModel;

/**
 *
 * @author Admin
 */
public class ShowUserInviteHandler extends VMEventHandler
{
    Event relatedEvent;
    
    public ShowUserInviteHandler(ViewModel parent, Event e) 
    {
        super(parent);
        relatedEvent = e;
    }

    @Override
    public void handle(javafx.event.Event event) 
    {
        Event[] params = {relatedEvent};
        EventPlannerApp.switchViewModel("/view/CreateInvitationView.fxml", params);
    }
    
}

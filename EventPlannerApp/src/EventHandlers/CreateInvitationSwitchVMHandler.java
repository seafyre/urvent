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
 * @author User
 */
public class CreateInvitationSwitchVMHandler extends VMEventHandler
{
    Event relatedEvent;
    
    public CreateInvitationSwitchVMHandler(ViewModel parent, Event relatedEvent) 
    {
        super(parent);
        this.relatedEvent = relatedEvent;
    }

    @Override
    public void handle(javafx.event.Event event) 
    {
        Event[] params = {relatedEvent};
        EventPlannerApp.switchViewModel("/view/CreateInvitationView.fxml", params);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import eventplannerappDELETETHISLATER.EventPlannerApp;
import javafx.event.Event;
import tools.APICommand;
import viewmodel.CreateEventViewModel;
import viewmodel.ViewModel;

/**
 *
 * @author User
 */
public class CreateEventConfirmButtonEventHandler extends VMEventHandler
{
    
    public CreateEventConfirmButtonEventHandler(ViewModel parent) 
    {
        super(parent);
    }

    @Override
    public void handle(Event event) 
    {
        CreateEventViewModel p = (CreateEventViewModel) parent;
        p.createNewEvent();
    }
    
}

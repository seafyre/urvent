/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import javafx.event.Event;
import viewmodel.EventInfoViewModel;
import viewmodel.ViewModel;

/**
 *
 * @author ReppoH33G
 */
public class DeleteEventButtonHandler extends VMEventHandler {
    
    public DeleteEventButtonHandler(ViewModel parent) 
    {
        super(parent);
    }

    @Override
    public void handle(Event event) 
    {
        EventInfoViewModel p = (EventInfoViewModel) parent;
        p.deleteEvent();
    }
    
}

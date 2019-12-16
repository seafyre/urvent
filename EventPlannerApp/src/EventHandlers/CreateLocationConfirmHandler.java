/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import javafx.event.Event;
import viewmodel.CreateLocationViewModel;
import viewmodel.ViewModel;

/**
 *
 * @author User
 */
public class CreateLocationConfirmHandler extends VMEventHandler
{

    public CreateLocationConfirmHandler(ViewModel parent) 
    {
        super(parent);
    }

    @Override
    public void handle(Event event) 
    {
        CreateLocationViewModel p = (CreateLocationViewModel)parent;
        p.createLocation();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import javafx.event.Event;
import javafx.event.EventHandler;
import viewmodel.CreateAccountViewModel;
import viewmodel.ViewModel;

/**
 *
 * @author User
 */
public class CreateAccountConfirmEventHandler extends VMEventHandler
{

    public CreateAccountConfirmEventHandler(ViewModel parent) 
    {
        super(parent);
    }

    @Override
    public void handle(Event event) 
    {
        CreateAccountViewModel p = (CreateAccountViewModel)parent;
        p.createNewUser();
    }
    
}

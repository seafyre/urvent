/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import javafx.event.Event;
import viewmodel.UserScreenEditViewModel;
import viewmodel.ViewModel;

/**
 *
 * @author Admin
 */
public class EditUserSaveButtonHandler extends VMEventHandler
{

    public EditUserSaveButtonHandler(ViewModel parent) 
    {
        super(parent);
    }

    @Override
    public void handle(Event event)
    {
        UserScreenEditViewModel p = (UserScreenEditViewModel)this.parent;
        p.updateUser();
    }
    
}

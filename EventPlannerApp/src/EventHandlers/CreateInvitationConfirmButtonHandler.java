/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import javafx.event.Event;
import viewmodel.CreateInvitationViewModel;
import viewmodel.ViewModel;

/**
 *
 * @author User
 */
public class CreateInvitationConfirmButtonHandler extends VMEventHandler
{

    public CreateInvitationConfirmButtonHandler(ViewModel parent) 
    {
        super(parent);
    }

    @Override
    public void handle(Event event) 
    {
        CreateInvitationViewModel p = (CreateInvitationViewModel) parent;
        p.createInvitation();
    }
    
}

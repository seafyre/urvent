/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import model.Invitation;
import viewmodel.UserInvitationsConfirmedViewModel;
import viewmodel.ViewModel;

/**
 *
 * @author Admin
 */
public class ShowTicketViewEventHandler extends VMEventHandler
{
    Invitation invitation;
    
    public ShowTicketViewEventHandler(ViewModel parent, Invitation invitation) 
    {
        super(parent);
        this.invitation = invitation;
    }

    @Override
    public void handle(javafx.event.Event event) 
    {
        Invitation[] param = {invitation};
        EventPlannerApp.app.paramDump = param;
        ((UserInvitationsConfirmedViewModel)parent).openTicketView(invitation);
    }
    
}

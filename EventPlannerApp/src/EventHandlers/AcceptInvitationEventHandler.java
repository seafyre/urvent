/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import eventplannerappDELETETHISLATER.EventPlannerApp;
import javafx.event.Event;
import javafx.scene.control.Alert;
import model.Invitation;
import viewmodel.ViewModel;

/**
 *
 * @author Admin
 */
public class AcceptInvitationEventHandler extends VMEventHandler
{
    Invitation invitation;
    
    public AcceptInvitationEventHandler(ViewModel parent, Invitation invitation) 
    {
        super(parent);
        this.invitation = invitation;
    }

    @Override
    public void handle(Event event) 
    {
        boolean success = invitation.accept();
        if(success == true)
        {
            EventPlannerApp.app.switchViewModel("/view/UserInvitationsView.fxml");
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Accept Invite Failed");
            a.showAndWait();
        }
    }
    
}

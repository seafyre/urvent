/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.ShowEventInfoHandler;
import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Event;
import model.Invitation;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UserInvitationsConfirmedViewModel extends ViewModel implements Initializable 
{
    @FXML
    private Button homeBtn;
    
    @FXML
    private VBox invitationsListBox;
    
    ArrayList<Invitation> invitations = new ArrayList<Invitation>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EventPlannerApp.app.setActiveVM(this);
        loadData();
        createHandlers();
        createEventButtons();
    }    

    @Override
    protected void loadData() 
    {
        try
        {
            ArrayList<JSONObject> obj = HTTP.getArray(APICommand.getInvitationByUserID(EventPlannerApp.app.getActiveUser().getID()));
            for(JSONObject n : obj)
            {
                Invitation inv = new Invitation(n);
                invitations.add(inv);
            }   
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void createHandlers() 
    {
        homeBtn.setOnAction(new SwitchViewModelHandler("/view/HomeView.fxml",this));
    }
    
    private Button getInvitationButton(Invitation invitationModel)
    {
        String inviteName = invitationModel.getName();
        Button btn = new Button(inviteName);
        btn.setOnAction(new ShowEventInfoHandler(this, new Event(HTTP.get(APICommand.getEventByID(invitationModel.getRelatedEvent()))))); //TODO
        btn.getStyleClass().add("basicButtonDark"); //TODO
        btn.setPrefWidth(256);
        return btn;
    }
    
    private void createEventButtons()
    {
        for(Invitation n : invitations)
        {
            if(n.getAccepted() == 1)
            {
                Button btn = getInvitationButton(n);
                invitationsListBox.getChildren().add(btn);   
            }
        }
    }
    
}

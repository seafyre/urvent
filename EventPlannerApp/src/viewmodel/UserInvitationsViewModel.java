/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.AcceptInvitationEventHandler;
import EventHandlers.ShowEventInfoHandler;
import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Event;
import model.Invitation;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;
import tools.TextUtil;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UserInvitationsViewModel extends ViewModel implements Initializable 
{
    @FXML
    private ScrollPane rootPane;
    
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
        removeHBar(rootPane);
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
    
    private void createEventButtons()
    {
        for(Invitation n : invitations)
        {
            if(n.getAccepted() == 0)
            {
                HBox btn = getInvitationBox(n);
                invitationsListBox.getChildren().add(btn);   
            }
        }
        if(invitationsListBox.getChildren().size() == 0)
        {
            invitationsListBox.getChildren().add(TextUtil.getNothingHereLabel()); 
        }
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
    
    private HBox getInvitationBox(Invitation invitationModel)
    {
        HBox hBox = new HBox();
        Button btn = getInvitationButton(invitationModel);
        Button btnAccept = new Button("Accept");
        btnAccept.getStyleClass().add("basicButtonDark");
        btnAccept.setOnAction(new AcceptInvitationEventHandler(this, invitationModel));
        Button btnDecline = new Button("Decline");
        btnDecline.getStyleClass().add("basicButtonDark");
        hBox.getChildren().add(btn);
        hBox.getChildren().add(btnAccept);
        hBox.getChildren().add(btnDecline);
        return hBox;
    }
    
}

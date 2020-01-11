/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.ShowEventInfoHandler;
import EventHandlers.ShowTicketViewEventHandler;
import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
public class UserInvitationsConfirmedViewModel extends ViewModel implements Initializable 
{
    @FXML
    private ScrollPane rootPane;
    
    @FXML
    private Button homeBtn;
    
    @FXML
    private VBox invitationsListBox;
    
    Parent ticketView;
    
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
        Button ticketBtn = new Button("Get Ticket");
        ticketBtn.getStyleClass().add("basicButtonDark"); //TODO
        ticketBtn.setPrefWidth(256);
        ticketBtn.setOnAction(new ShowTicketViewEventHandler(this, invitationModel));
        hBox.getChildren().add(btn);
        hBox.getChildren().add(ticketBtn);
        
        return hBox;
    }
    
    private void createEventButtons()
    {
        for(Invitation n : invitations)
        {
            if(n.getAccepted() == 1)
            {
                Button btn = getInvitationButton(n);
                invitationsListBox.getChildren().add(getInvitationBox(n));   
            }
        }
        if(invitationsListBox.getChildren().size() == 0)
        {
            invitationsListBox.getChildren().add(TextUtil.getNothingHereLabel()); 
        }
    }
    
    public void openTicketView(Invitation invitation)
    {
        try 
        {
            Stage secondaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            ticketView = (Parent)loader.load(getClass().getResource("/view/TicketView.fxml"));
            Scene sc = new Scene(ticketView);
            secondaryStage.setScene(sc);
            secondaryStage.show();
        } 
        catch (Exception e) 
        {
            Logger.getLogger(UserInvitationsConfirmedViewModel.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}

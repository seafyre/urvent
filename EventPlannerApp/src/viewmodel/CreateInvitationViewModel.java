/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.CreateInvitationConfirmButtonHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import model.Event;
import model.User;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CreateInvitationViewModel extends ViewModel implements Initializable 
{
    @FXML
    private ScrollPane rootPane;
        
    @FXML
    private TextField mailTxtFld;
    
    @FXML
    private Button submitBtn;
    
    @FXML
    private Button cancelBtn;
    
    Event event;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadData();
        createHandlers();
        removeHBar(rootPane);
    }    

    @Override
    protected void loadData() 
    {
        event = (Event)EventPlannerApp.app.paramDump[0];
        clearParamDump();
    }

    @Override
    protected void createHandlers() 
    {
        submitBtn.setOnAction(new CreateInvitationConfirmButtonHandler(this));
    }
    
    public void createInvitation()
    {
        try
        {
            User guest = new User(HTTP.get(APICommand.getUserByMail(this.mailTxtFld.getText(), EventPlannerApp.app.getActiveUser().getloginToken())));
            JSONObject reply = HTTP.get(APICommand.insertNewInvitation(event.getID(), 1, EventPlannerApp.app.getActiveUser().getID(), guest.getID()));
            if((Boolean)reply.get("succ") == true)
            {
                Event[] params = {event};
                EventPlannerApp.switchViewModel("/view/EventInfoView.fxml", params);
            }
            else if((Boolean)reply.get("succ") == false)
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Couldn't invite User");
                a.showAndWait();
            }
        }
        catch(Exception e)
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Critical Error");
            a.showAndWait();
        }

    }
    
}

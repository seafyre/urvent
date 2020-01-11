/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import model.User;
import tools.APICommand;
import tools.HTTP;

/**
 * FXML Controller class
 *
 * @author Simon FH
 */
public class HomeViewModel extends ViewModel implements Initializable 
{
    @FXML
    private ScrollPane rootPane;
    
    @FXML
    private Label welcomeLbl;
    
    @FXML
    private Button myEventsBtn;
    
    @FXML
    private Button myInvitationsBtn;
    
    @FXML
    private Button myAcceptedInvitationsBtn;
    
    @FXML
    private Button myAccountBtn;
    
    @FXML
    private Button calendarBtn;
    
    @FXML
    private Button myLocationsBtn;
    
    User user = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EventPlannerApp.app.setActiveVM(this);
        loadData();
        setLabels();
        createHandlers();
        removeHBar(rootPane);
    }    
    
    private void setLabels ()
    {
        welcomeLbl.setText("Hello "+ user.getName());
    }
    
    @Override
    protected void loadData() 
    {
        int userID = EventPlannerApp.app.getActiveUser().getID();
        String userMail = EventPlannerApp.app.getActiveUser().getMail();
        String userToken = EventPlannerApp.app.getActiveUser().getloginToken();
        user = new User(HTTP.get(APICommand.getUserByID(userID, userMail, userToken)));
    }

    @Override
    protected void createHandlers() 
    {
        myEventsBtn.setOnAction(new SwitchViewModelHandler("/view/UserOwnedEventsView.fxml",this));
        myInvitationsBtn.setOnAction(new SwitchViewModelHandler("/view/UserInvitationsView.fxml",this));
        myAcceptedInvitationsBtn.setOnAction(new SwitchViewModelHandler("/view/UserInvitationsConfirmedView.fxml",this));
        myAccountBtn.setOnAction(new SwitchViewModelHandler("/view/UserScreenView.fxml",this));
        myLocationsBtn.setOnAction(new SwitchViewModelHandler("/view/UserOwnedLocationsView.fxml",this));
    }
}

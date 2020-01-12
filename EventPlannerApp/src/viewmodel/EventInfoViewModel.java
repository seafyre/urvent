/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.CreateInvitationSwitchVMHandler;
import EventHandlers.SwitchViewModelHandler;
import EventHandlers.DeleteEventButtonHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Event;
import model.Location;
import tools.APICommand;
import tools.HTTP;
import tools.TextUtil;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EventInfoViewModel extends ViewModel implements Initializable 
{
    @FXML
    private ScrollPane rootPane;
    
    @FXML
    private Button homeBtn;
    
    @FXML
    private Label evtNameLbl;
    
    @FXML
    private Label evtDescrLbl;
    
    @FXML
    private Label evtDateLbl;
    
    @FXML
    private Label evtLocationLbl;
    
    @FXML
    private Button editEventBtn;
    
    @FXML
    private Button inviteBtn;
    
    @FXML
    private WebView webView;
    
    @FXML 
    private Button deleteBtn;
    
    private Event event;
    private Location location;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EventPlannerApp.app.setActiveVM(this);
        loadData();
        createHandlers();
        setLabelText();
        setEditPrivileges();
        loadGMaps(location.getCoordinates()[0],location.getCoordinates()[1]);
        removeHBar(rootPane);
    }    

    @Override
    protected void loadData() 
    {
        setEvent((Event)(EventPlannerApp.app.paramDump[0]));
        setLocation(loadLocation(event.getLocation()));
        clearParamDump();
    }
    
    @Override
    protected void createHandlers() 
    {
        if(event.getOwner() != EventPlannerApp.app.getActiveUser().getID())
        {
            homeBtn.setOnAction(new SwitchViewModelHandler("/view/UserInvitationsView.fxml",this));
            homeBtn.setText("Back");
        }
        else
        {
            homeBtn.setOnAction(new SwitchViewModelHandler("/view/HomeView.fxml",this));
        }
        inviteBtn.setOnAction(new CreateInvitationSwitchVMHandler(this, event));
        if(event.getOwner() != EventPlannerApp.app.getActiveUser().getID())
        {
            deleteBtn.setManaged(false);//dont show delete button if user is not the owner of the event
        }
        else
        {
            deleteBtn.setOnAction(new DeleteEventButtonHandler(this));//otherwise call deleteEvent onclick
        }
    }
    
    public void deleteEvent()
    {
        HTTP.get(APICommand.deleteEventByID(event.getID()));
    }
    
    private void setEditPrivileges()
    {
        if(event.getOwner() != EventPlannerApp.app.getActiveUser().getID())
        {
            inviteBtn.setDisable(true);
            editEventBtn.setDisable(true);
        }
    }
    
    private void setLabelText()
    {
        evtNameLbl.setText(event.getName());
        this.evtDescrLbl.setText(TextUtil.format(event.getDescr(), 80));
        this.evtLocationLbl.setText(location.getName());
        this.evtDateLbl.setText(event.getDate());
    }
    
    public void setEvent(Event e)
    {
        event = e;
    }
    
    private void setLocation(Location l)
    {
        location = l;
    }
    
    private Location loadLocation(int ID)
    {
        Location l = new Location(HTTP.get(APICommand.getLocationByID(ID)));
        return l;
    }
    
    private void loadGMaps(float latitude, float longitude)
    {
        WebEngine eng = webView.getEngine();
        eng.load("https://www.google.com/maps/@?api=1&map_action=map&center=" + latitude + "," + longitude + "&zoom=12&basemap=terrain");
    }
    
}

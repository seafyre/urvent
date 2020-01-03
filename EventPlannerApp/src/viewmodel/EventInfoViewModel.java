/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.CreateInvitationSwitchVMHandler;
import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Event;
import model.Location;
import tools.APICommand;
import tools.HTTP;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EventInfoViewModel extends ViewModel implements Initializable 
{
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
        homeBtn.setOnAction(new SwitchViewModelHandler("/view/HomeView.fxml",this));
        inviteBtn.setOnAction(new CreateInvitationSwitchVMHandler(this, event));
    }
    
    private void setLabelText()
    {
        evtNameLbl.setText(event.getName());
        this.evtDescrLbl.setText(event.getDescr());
        this.evtLocationLbl.setText(location.getName());
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
    
}

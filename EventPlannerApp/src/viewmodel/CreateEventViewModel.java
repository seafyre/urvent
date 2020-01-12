/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.CreateEventConfirmButtonEventHandler;
import EventHandlers.SelectLocationComBoxHandler;
import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import model.Location;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CreateEventViewModel extends ViewModel  implements Initializable 
{
    @FXML
    private ScrollPane rootPane;
    
    @FXML
    private Button homeBtn;
    
    @FXML
    private Button submitBtn;  

    @FXML
    private Button cancelBtn;
    
    @FXML
    private TextField nameTxtFld;
    
    @FXML
    private TextField descrTxtFld;
            
    @FXML
    private DatePicker datePicker;
            
    @FXML
    private ComboBox locationSelectionComBox;
    
    public ArrayList<Location> locations = new ArrayList<Location>();
    public Location selectedLocation = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EventPlannerApp.app.setActiveVM(this);
        loadData();
        createHandlers();
        setupUI();
        removeHBar(rootPane);
    }    

    @Override
    protected void loadData() 
    {
        loadMyLocations();
    }

    @Override
    protected void createHandlers() 
    {
        homeBtn.setOnAction(new SwitchViewModelHandler("/view/HomeView.fxml",this));
        submitBtn.setOnAction(new CreateEventConfirmButtonEventHandler(this));
        cancelBtn.setOnAction(new SwitchViewModelHandler("/view/UserOwnedEventsView.fxml",this));
        locationSelectionComBox.setOnAction(new SelectLocationComBoxHandler(this, locationSelectionComBox));
    }
    
    private void setupUI()
    {
        for(Location n : locations)
        {
            locationSelectionComBox.getItems().add(n.getName());
        }
    }
    
    private void loadMyLocations()
    {
        ArrayList<JSONObject> locationJsons = HTTP.getArray(APICommand.getLocationByUser(EventPlannerApp.app.getActiveUser().getID()));
        for(JSONObject n : locationJsons)
        {
            Location l = new Location(n);
            locations.add(l);
        }
    }
    
    public void createNewEvent()
    {
       JSONObject reply = HTTP.get(APICommand.insertNewEvent(EventPlannerApp.app.getActiveUser().getID() ,nameTxtFld.getText(), descrTxtFld.getText(), this.selectedLocation.getID(), this.datePicker.getValue().toString())); //TODO choose location
       Boolean success = (Boolean) reply.get("succ");
       if(success == true)
       {
           System.out.println("created event!");
           EventPlannerApp.switchViewModel("/view/UserOwnedEventsView.fxml");
       }
       else
       {
           Alert a = new Alert(Alert.AlertType.ERROR);
           a.setContentText("Create Event Failed");
           a.showAndWait();
       }
    }

}

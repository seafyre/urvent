/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.ShowEventInfoHandler;
import EventHandlers.ShowLocationInfoHandler;
import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.Event;
import model.Location;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;
import tools.TextUtil;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UserOwnedLocationsViewModel extends ViewModel implements Initializable 
{
    @FXML
    private ScrollPane rootPane;

    @FXML
    private VBox locationsBox;
    
    @FXML
    private Button newLocationBtn;
    
    @FXML
    private Button homeBtn;
    /**
     * Initializes the controller class.
     */
    
    private ArrayList<Location> locations = new ArrayList<Location>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadData();
        createHandlers();
        injectLocationButtonsToUI();
        removeHBar(rootPane);
    }    

    @Override
    protected void loadData() 
    {
        loadLocations();
    }

    @Override
    protected void createHandlers() 
    {
        newLocationBtn.setOnAction(new SwitchViewModelHandler("/view/CreateLocationView.fxml",this));
        homeBtn.setOnAction(new SwitchViewModelHandler("/view/HomeView.fxml",this)); 
    }
    
    private void loadLocations()
    {
        ArrayList<JSONObject> out = HTTP.getArray(APICommand.getLocationByUser(EventPlannerApp.app.getActiveUser().getID()));
        for(JSONObject n : out)
        {
            locations.add(new Location(n));
        }
    }
    
    private void injectLocationButtonsToUI()
    {
        boolean hasLocations = false;
        for(Location n : locations)
        {
            locationsBox.getChildren().add(getLocationButton(n));
            hasLocations = true;
        }
        if(hasLocations == false)
        {
             locationsBox.getChildren().add(TextUtil.getNothingHereLabel()); 
        }
    }
    
    private Button getLocationButton(Location locationModel)
    {
        String locationName = locationModel.getName();
        Button btn = new Button(locationName);
        btn.setOnAction(new ShowLocationInfoHandler(this, locationModel));
        btn.getStyleClass().add("basicButtonDark");
        btn.setPrefWidth(256);
        return btn;
    }
    
}

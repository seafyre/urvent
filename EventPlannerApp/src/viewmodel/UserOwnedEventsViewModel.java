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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.Event;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;
import tools.TextUtil;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UserOwnedEventsViewModel extends ViewModel implements Initializable 
{
    @FXML
    private ScrollPane rootPane;
    
    @FXML
    private Button homeBtn;
    
    @FXML
    private Button newEventBtn;
    
    @FXML
    private VBox eventsBox;
    
    ArrayList<Event> events = new ArrayList<Event>();
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
        EventPlannerApp.app.setActiveVM(this);
        loadEvents();
        injectEventButtonsToUI();
    }

    @Override
    protected void createHandlers() 
    {
        newEventBtn.setOnAction(new SwitchViewModelHandler("/view/CreateEventView.fxml", this));
        homeBtn.setOnAction(new SwitchViewModelHandler("/view/HomeView.fxml", this));
    }
    
    private void loadEvents()
    {
        ArrayList<JSONObject> out = HTTP.getArray(APICommand.getEventByUser(EventPlannerApp.app.getActiveUser().getID()));
        for(JSONObject n : out)
        {
            events.add(new Event(n));
        }
    }
    
    private void injectEventButtonsToUI()
    {
        boolean hasEvents = false;
        for(Event n : events)
        {
            eventsBox.getChildren().add(getEventButton(n));
            hasEvents = true;
        }
        if(hasEvents == false)
        {
             eventsBox.getChildren().add(TextUtil.getNothingHereLabel()); 
        }
    }
    
    private Button getEventButton(Event eventModel)
    {
        String eventName = eventModel.getName();
        Button btn = new Button(eventName);
        btn.setOnAction(new ShowEventInfoHandler(this, eventModel)); //TODO
        btn.getStyleClass().add("basicButtonDark"); //TODO
        btn.setPrefWidth(256);
        return btn;
    }
    
}

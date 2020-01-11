/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.CreateLocationConfirmHandler;
import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CreateLocationViewModel extends ViewModel implements Initializable
{
    @FXML
    private ScrollPane rootPane;
    
    @FXML
    private Button homeBtn;
    
    @FXML
    private TextField nameTxtFld;
    
    @FXML
    private TextField descrTxtFld;
    
    @FXML
    private TextField coordinatesTxtFld;
    
    @FXML
    private Button submitBtn;
    
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        createHandlers();
        removeHBar(rootPane);
    }    

    @Override
    protected void loadData() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void createHandlers() 
    {
        homeBtn.setOnAction(new SwitchViewModelHandler("/view/HomeView.fxml",this));
        submitBtn.setOnAction(new CreateLocationConfirmHandler(this));
        cancelBtn.setOnAction(new SwitchViewModelHandler("/view/UserOwnedLocationsView.fxml",this));
    }
    
    public void createLocation()
    {
       JSONObject reply = HTTP.get(APICommand.insertNewLocation(nameTxtFld.getText(), descrTxtFld.getText(), coordinatesTxtFld.getText(), EventPlannerApp.app.getActiveUser().getID())); //TODO choose location
       Boolean success = (Boolean) reply.get("succ");
       if(success == true)
       {
           System.out.println("created location!");
           EventPlannerApp.switchViewModel("/view/UserOwnedLocationsView.fxml");
       }
       else
       {
           Alert a = new Alert(Alert.AlertType.ERROR);
           a.setContentText("Create Location Failed");
           a.showAndWait();
       }
    }
    
}

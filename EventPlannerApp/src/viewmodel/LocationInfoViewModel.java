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
import model.Location;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LocationInfoViewModel extends ViewModel implements Initializable 
{
    @FXML
    private Button homeBtn;
    
    @FXML
    private Label locNameLbl;
    
    @FXML
    private Label locDescrLbl;
    
    @FXML
    private Label locCoordsLbl;
    
    Location location;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadData();
        setLabels();
        createHandlers();
    }    

    @Override
    protected void loadData() 
    {
        location = (Location) EventPlannerApp.app.paramDump[0];
        clearParamDump();
    }

    @Override
    protected void createHandlers() 
    {
        homeBtn.setOnAction(new SwitchViewModelHandler("/view/HomeView.fxml",this));
    }
    
    private void setLabels()
    {
        locNameLbl.setText(location.getName());
        locDescrLbl.setText(location.getDescr());
        locCoordsLbl.setText(location.getCoordinates());
    }
    
}

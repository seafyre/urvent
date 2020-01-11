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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Location;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LocationInfoViewModel extends ViewModel implements Initializable 
{
    @FXML
    private ScrollPane rootPane;
    
    @FXML
    private Button homeBtn;
    
    @FXML
    private Label locNameLbl;
    
    @FXML
    private Label locDescrLbl;
    
    @FXML
    private Label locCoordsLbl;
    
    @FXML
    private WebView webView;
    
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
        loadGMaps(location.getCoordinates()[0],location.getCoordinates()[1]);
        removeHBar(rootPane);
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
        float lat = location.getCoordinates()[0];
        float longi = location.getCoordinates()[1];
        String coordsStr = String.valueOf(lat) + "," + String.valueOf(longi);
        locCoordsLbl.setText(coordsStr);
    }
    
    private void loadGMaps(float latitude, float longitude)
    {
        WebEngine eng = webView.getEngine();
        eng.load("https://www.google.com/maps/@?api=1&map_action=map&center=" + latitude + "," + longitude + "&zoom=12&basemap=terrain");
    }
    
}

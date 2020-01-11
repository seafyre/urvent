/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;

/**
 *
 * @author Admin
 */
public abstract class ViewModel
{
    protected abstract void loadData();
    protected abstract void createHandlers();
    public Object[] params;
    
    public void removeHBar(ScrollPane sp)
    {
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
    
    protected void clearParamDump()
    {
        EventPlannerApp.app.paramDump = null;
    }
}

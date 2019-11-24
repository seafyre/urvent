/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import eventplannerappDELETETHISLATER.EventPlannerApp;
import javafx.event.Event;
import javafx.event.EventHandler;
import viewmodel.ViewModel;

/**
 *
 * @author Admin
 */
public class SwitchViewModelHandler extends VMEventHandler implements EventHandler
{
    private String viewPath;
    
    public SwitchViewModelHandler(String viewPath, ViewModel parent)
    {
        super(parent);
        this.viewPath = viewPath;
    }
    
    @Override
    public void handle(Event event) 
    {
        EventPlannerApp.app.switchViewModel(viewPath);
    }
    
}

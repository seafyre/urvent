/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template this.in the editor.
 */
package EventHandlers;

import eventplannerappDELETETHISLATER.EventPlannerApp;
import javafx.event.EventHandler;
import viewmodel.ViewModel;

/**
 *
 * @author Admin
 */
public abstract class VMEventHandler implements EventHandler
{
    protected ViewModel parent;
    
    public VMEventHandler(ViewModel parent)
    {
        this.parent = parent;
        EventPlannerApp.app.setActiveVM(parent);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import eventplannerappDELETETHISLATER.EventPlannerApp;

/**
 *
 * @author Admin
 */
public abstract class ViewModel 
{
    protected abstract void loadData();
    protected abstract void createHandlers();
    public Object[] params;
    
    protected void clearParamDump()
    {
        EventPlannerApp.app.paramDump = null;
    }
}

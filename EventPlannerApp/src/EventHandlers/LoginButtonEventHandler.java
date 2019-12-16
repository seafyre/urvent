/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import viewmodel.LoginViewModel;
import viewmodel.ViewModel;

/**
 *
 * @author User
 */
public class LoginButtonEventHandler extends VMEventHandler
{

    public LoginButtonEventHandler(ViewModel parent)
    {
        super(parent);
    }
    
    @Override
    public void handle(Event event) 
    {
        try
        {
            LoginViewModel p = (LoginViewModel)parent;
            p.tryLogin();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    
}

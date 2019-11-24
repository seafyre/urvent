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
public class LoginButtonEventHandler extends VMEventHandler implements EventHandler
{

    public LoginButtonEventHandler(ViewModel parent)
    {
        super(parent);
        int n = 1+1; //TODO remove this shite
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
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Login Failed");
            a.showAndWait();
        }

    }
    
}

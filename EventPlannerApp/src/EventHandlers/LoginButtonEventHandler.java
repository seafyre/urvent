/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author User
 */
public class LoginButtonEventHandler implements EventHandler
{

    @Override
    public void handle(Event event) 
    {
        System.out.println("Click!");
    }
    
}

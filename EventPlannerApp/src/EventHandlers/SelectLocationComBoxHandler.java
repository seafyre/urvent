/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import javafx.event.Event;
import javafx.scene.control.ComboBox;
import viewmodel.CreateEventViewModel;
import viewmodel.ViewModel;

/**
 *
 * @author User
 */
public class SelectLocationComBoxHandler extends VMEventHandler
{

    ComboBox parentBox;
            
    public SelectLocationComBoxHandler(ViewModel parent, ComboBox parentBox) 
    {
        super(parent);
        this.parentBox = parentBox;
    }

    @Override
    public void handle(Event event) 
    {
        Event e = event;
        CreateEventViewModel p = (CreateEventViewModel) parent;
        p.selectedLocation = p.locations.get(parentBox.getSelectionModel().getSelectedIndex());
        System.out.println("Selected: " + parentBox.getSelectionModel().getSelectedItem() + " Index: " + parentBox.getSelectionModel().getSelectedIndex() + "parentList: " + p.locations.get(parentBox.getSelectionModel().getSelectedIndex()).getName());
    }
    
}

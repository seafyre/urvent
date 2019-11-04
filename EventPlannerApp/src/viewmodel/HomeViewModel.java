/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.User;
import tools.APICommand;
import tools.HTTP;

/**
 * FXML Controller class
 *
 * @author Simon FH
 */
public class HomeViewModel extends ViewModel implements Initializable 
{
    @FXML
    private Label welcomeLbl;
    
    User user=null;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadData();
        setLabels();
    }    
    
    private void setLabels ()
    {
        welcomeLbl.setText("Hello "+ user.getName());
    }
    
    @Override
    protected void loadData() 
    {
        user=new User(HTTP.get(APICommand.getUserByID(1)));
    }
}

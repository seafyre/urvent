/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;
import tools.HTTP;
import tools.JSON;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SampleViewModel implements Initializable 
{

    @FXML
    private Label label;
    
    @FXML
    private Label label3;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        label.setText("programmatically assigned text");
        //System.out.println(HTTP.get("").toString());
        //JSONObject testJSON = JSON.readJSON("{\"ID\":\"1\",\"name\":\"test\",\"mail\":\"teastmail@test.tst\",\"password\":\"pw1\",\"loginToken\":\"\"}"); //Test JSON
        User testUser = new User(HTTP.get(""));
        User testUser2 = new User(HTTP.get(""));
        label3.setText(testUser.getMail());
        //{"Command" : "getUser", "Param" : "1"}
        System.out.println(testUser.getMail());
    }

    /**
     * 
     * diese funktion macht dies und das
     * @param aParam blabla
     * @return dasundas
     */
    private String aFunction(int aParam)
    {
        return null;
    }
    
}

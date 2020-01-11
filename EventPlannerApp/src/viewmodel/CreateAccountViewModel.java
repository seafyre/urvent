/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.CreateAccountConfirmEventHandler;
import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import model.User;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CreateAccountViewModel extends ViewModel implements Initializable 
{   
    @FXML
    private Button confirmBtn;
    
    @FXML
    private Button cancelBtn;
    
    @FXML
    private TextField nameTxtFld;
    
    @FXML
    private TextField mailTxtFld;
    
    @FXML
    private TextField pwTxtFld1;
    
    @FXML
    private TextField pwTxtFld2;
    
    @FXML
    private ScrollPane rootPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EventPlannerApp.app.setActiveVM(this);
        createHandlers();
        removeHBar(rootPane);
    }    

    @Override
    protected void loadData() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void createHandlers() 
    {
        cancelBtn.setOnAction(new SwitchViewModelHandler("/view/LoginView.fxml",this));
        confirmBtn.setOnAction(new CreateAccountConfirmEventHandler(this));
    }
    
    
    private Boolean validatePassword()
    {
        Boolean valid = false;
        String pw1 = pwTxtFld1.getText();
        String pw2 = pwTxtFld2.getText();
        
        if(pw1.contentEquals(pw2))
        {
            valid = true;
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Passwor invalid");
            a.showAndWait();
        }
        
        return valid;
    }
    
    public void createNewUser()
    {
        if(validatePassword() == true)
        {
            String mail = mailTxtFld.getText();
            String name = nameTxtFld.getText();
            String pw = pwTxtFld1.getText();
            JSONObject result = HTTP.get(APICommand.insertNewUser(mail, name, pw));
            Boolean success = (Boolean)result.get("succ");
            if(success == true)
            {
                EventPlannerApp.switchViewModel("/view/LoginView.fxml");
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Account Creation Failed");
                a.showAndWait();
            }
        }
    }
}

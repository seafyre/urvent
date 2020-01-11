/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.LoginButtonEventHandler;
import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class LoginViewModel extends ViewModel implements Initializable 
{
    
    @FXML
    private TextField mailTxtFld;
    
    @FXML
    private TextField passwordTxtFld;
    
    @FXML
    private Button loginBtn;
    
    @FXML
    private Button createAccBtn;
    
    @FXML
    private ScrollPane rootPane;
            
    //User user = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadData();
        setLabels();
        createHandlers();
        removeHBar(rootPane);
    }    

    @Override
    protected void loadData() 
    {

    }

    @Override
    protected void createHandlers() 
    {
        loginBtn.setOnAction(new LoginButtonEventHandler(this));
        createAccBtn.setOnAction(new SwitchViewModelHandler("/view/CreateAccountView.fxml", this));
    }
    
    private void setLabels()
    {

    }
    
    public void tryLogin()
    {
        String mail = mailTxtFld.getText();
        String pw = passwordTxtFld.getText();
        JSONObject reply = HTTP.get(APICommand.tryLogin(mail, pw));
        Boolean success = (Boolean)(reply.get("succ"));
        String token = reply.get("param").toString();
        if(success == true)
        {
            User u = new User(HTTP.get(APICommand.getUserByMail(mail, token)));
            EventPlannerApp.app.setActiveUser(u);
            //HTTP.get(APICommand.getUserByID(u.getID(), u.getMail(), u.getloginToken()));
            EventPlannerApp.switchViewModel("/view/HomeView.fxml");
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Login Failed");
            a.showAndWait();
        }
    }
    
}

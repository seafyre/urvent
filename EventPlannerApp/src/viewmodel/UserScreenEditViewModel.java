/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.EditUserSaveButtonHandler;
import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class UserScreenEditViewModel extends ViewModel implements Initializable 
{
    @FXML
    private Button homeBtn;
    
    @FXML
    private Label usermailLbl;
    
    @FXML
    private TextField usernameTxtFld;
    
    @FXML
    private TextField userDescrTxtFld;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private Button cancelBtn;
    
    User user = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadData();
        createHandlers();
        setTextFieldContent();
    }    

    @Override
    protected void loadData() 
    {
        int userID = EventPlannerApp.app.getActiveUser().getID();
        String userMail = EventPlannerApp.app.getActiveUser().getMail();
        String userToken = EventPlannerApp.app.getActiveUser().getloginToken();
        user = new User(HTTP.get(APICommand.getUserByID(userID, userMail, userToken)));
    }

    @Override
    protected void createHandlers() 
    {
        homeBtn.setOnAction(new SwitchViewModelHandler("/view/HomeView.fxml",this));
        saveBtn.setOnAction(new EditUserSaveButtonHandler(this));
        cancelBtn.setOnAction(new SwitchViewModelHandler("/view/UserScreenView.fxml",this));
    }
    
    private void setTextFieldContent()
    {
        usermailLbl.setText(user.getMail() + " (Mail cannot be edited)");
        usernameTxtFld.setText(user.getName());
        userDescrTxtFld.setText(user.getDescr());
    }
    
    public void updateUser()
    {
        JSONObject reply = HTTP.get(APICommand.editUser(user.getID(), usernameTxtFld.getText(),  userDescrTxtFld.getText()));
        Boolean success = (Boolean)(reply.get("succ"));
        if(success == true)
        {
            User u = new User(HTTP.get(APICommand.getUserByMail(user.getMail(), user.getloginToken())));
            EventPlannerApp.app.setActiveUser(u);
            EventPlannerApp.switchViewModel("/view/UserScreenView.fxml");
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Edit Failed");
            a.showAndWait();
        }
    }
    
}

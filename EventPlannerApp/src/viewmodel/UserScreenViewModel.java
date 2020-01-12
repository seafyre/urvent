/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.User;
import tools.APICommand;
import tools.HTTP;
import tools.TextUtil;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UserScreenViewModel extends ViewModel implements Initializable 
{
    @FXML
    private ScrollPane rootPane;
    
    @FXML
    private Button homeBtn;
    
    @FXML
    private ImageView profileImg;
    
    @FXML
    private Label usernameLbl;
    
    @FXML
    private Text userDescrTxt;
    
    @FXML
    private Label usermailLbl;
    
    @FXML
    private Button editBtn;
    
    User user = null;
    Image profileImage = null;
    /**
     * Initializes the controller class. Don't touch the parameters!
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EventPlannerApp.app.setActiveVM(this);
        loadData();
        setLabels();
        createHandlers();
        removeHBar(rootPane);
    }    
    
    @Override
    protected void loadData()
    {
        int userID = EventPlannerApp.app.getActiveUser().getID();
        String userMail = EventPlannerApp.app.getActiveUser().getMail();
        String userToken = EventPlannerApp.app.getActiveUser().getloginToken();
        user = new User(HTTP.get(APICommand.getUserByID(userID, userMail, userToken)));
        profileImage = loadProfileImage();
        profileImg.setImage(profileImage);
    }
    
    private Image loadProfileImage()
    {
        String src = HTTP.ADDRESS + "userPics/" + user.getID() + ".png";
        Image img = new Image(src, 128,128, false, false);
        System.out.println(src);
        if(img.getPixelReader() == null)
        {
            src = HTTP.ADDRESS + "userPics/" + user.getID() + ".jpg";
            img = new Image(src, 128,128, false, false);
        }
        
        return img;
    }
    
    private void setLabels()
    {
        usernameLbl.setText(user.getName());
        usermailLbl.setText(user.getMail());
        if(user.getDescr() != null)
        {
            userDescrTxt.setText(TextUtil.format(user.getDescr(),100));   
        }
    }

    @Override
    protected void createHandlers() 
    {
        homeBtn.setOnAction(new SwitchViewModelHandler("/view/HomeView.fxml", this));
        editBtn.setOnAction(new SwitchViewModelHandler("/view/UserScreenEditView.fxml", this));
    }
}

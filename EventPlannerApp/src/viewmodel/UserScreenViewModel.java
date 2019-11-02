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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.User;
import tools.APICommand;
import tools.HTTP;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UserScreenViewModel extends ViewModel implements Initializable {
    
    @FXML
    private ImageView profileImg;
    
    @FXML
    private Label usernameLbl;
    
    User user = null;
    Image profileImage = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadData();
    }    
    
    @Override
    protected void loadData()
    {
        user = new User(HTTP.get(APICommand.getUserByID(1)));
        profileImage = loadProfileImage();
        profileImg.setImage(profileImage);
    }
    
    private Image loadProfileImage()
    {
        String src = HTTP.ADDRESS + "userPics/" + user.id + ".png";
        Image img = new Image(src, 128,128, false, false);
        System.out.println(src);
        
        return img;
    }
    
    private void setLabels()
    {
     
    }
}

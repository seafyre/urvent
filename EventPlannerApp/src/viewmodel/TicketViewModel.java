/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Invitation;
import tools.QRGen;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TicketViewModel extends ViewModel implements Initializable 
{
    @FXML
    private ImageView imgView;
    
    Invitation invitation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        invitation = (Invitation)(EventPlannerApp.app.paramDump[0]);
        clearParamDump();
        generateQRCode();
    }    
    
    private void generateQRCode()
    {
        Image img = SwingFXUtils.toFXImage(QRGen.generateQRCode(invitation.toString()), null);
        imgView.setImage(img);
    }
    
    @Override
    protected void loadData() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void createHandlers() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

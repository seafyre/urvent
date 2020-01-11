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
import model.Ticket;
import tools.APICommand;
import tools.HTTP;
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
    Ticket ticket;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        invitation = (Invitation)(EventPlannerApp.app.paramDump[0]);
        clearParamDump();
        loadData();
        generateQRCode(getTicketString());
    }    
    
    private void generateQRCode(String codeContent)
    {
        
        Image img = SwingFXUtils.toFXImage(QRGen.generateQRCode(codeContent), null); //TODO
        imgView.setImage(img);
    }
    
    private String getTicketString()
    {
        return ticket.getID() + "," + ticket.getCode();
    }
    
    @Override
    protected void loadData() 
    {
        ticket = new Ticket(HTTP.get(APICommand.getTicketByInvitationID(invitation.getID())));
    }

    @Override
    protected void createHandlers() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

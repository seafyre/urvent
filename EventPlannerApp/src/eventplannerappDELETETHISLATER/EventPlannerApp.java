/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventplannerappDELETETHISLATER;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import viewmodel.EventInfoViewModel;
import viewmodel.ViewModel;

/**
 *
 * @author Admin
 */
public class EventPlannerApp extends Application 
{
    public static EventPlannerApp app;
    public static Stage MainStage;
    public static FXMLLoader loader;
    
    public Object[] paramDump;
    private ViewModel activeVM;
    private User activeUser;
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        //Singleton Pattern, never instanciate another App or Mainstage
        app = this;
        MainStage = stage;
        loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/view/LoginView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    public static void switchViewModel(String viewPath)
    {
        app.setScene(viewPath);
    }
    
    public static void switchViewModel(String viewPath, Object[] params)
    {
        app.paramDump = params;
        app.setScene(viewPath);
        int n = 1;
        
    }
    
    private void setScene(String viewPath)
    {
        try 
        {
            Parent sceneRoot = loader.load(getClass().getResource(viewPath));
            Scene scene = new Scene(sceneRoot);
            MainStage.setScene(scene);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EventPlannerApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setActiveUser(User user)
    {
        this.activeUser = user;
    }
    
    public User getActiveUser()
    {
        return this.activeUser;
    }
    
    public void setActiveVM(ViewModel viewModel)
    {
        this.activeVM = viewModel;
        System.out.println(this.activeVM.toString());
    }
    
    public ViewModel getActiveVM()
    {
        return this.activeVM;
    }
}

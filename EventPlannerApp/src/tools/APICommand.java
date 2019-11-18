/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author User
 */
public class APICommand 
{
    private static String commandPrefix = "payload=";
    
    public static String getUserByID(int ID)
    {
        String command = commandPrefix + "{\"cmd\":\"getUserByID\",\"csv\":\"false\",\"param\":\"" + ID +"\"}";
        return command;
    }
    
    public static String getEventByID(int ID)
    {
        String command = commandPrefix + "{\"cmd\":\"getEventByID\",\"csv\":\"false\",\"param\":\"" + ID +"\"}";
        return command;        
    }
    
    public static String getEventByUser(int ID)
    {
        String command = commandPrefix + "{\"cmd\":\"getEventByUser\",\"csv\":\"false\",\"param\":\"" + ID +"\"}";
        return command;        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import eventplannerappDELETETHISLATER.EventPlannerApp;

/**
 *
 * @author User
 */
public class APICommand 
{
    private static String commandPrefix = "payload=";
    
    public static String getUserByID(int ID, String mail, String token)
    {
        String command = commandPrefix + "{\"cmd\":\"getUserByID\",\"csv\":\"false\",\"param\":\"" + ID +"\",\"user\":\"" + mail + "\",\"token\":\"" + token + "\"}";
        return command;
    }
    
    public static String editUser(int ID, String name, String descr)
    {
        String command = commandPrefix + "{\"cmd\":\"editUser\",\"csv\":\"false\",\"param\":\"" + ID + "\",\"name\":\"" + name + "\",\"descr\":\"" + descr + "\"}";
        return command;
    }
    
    public static String deleteEventByID(int ID)
    {
        String command = commandPrefix + "{\"cmd\":\"deleteEventByID\",\"csv\":\"false\",\"param\":\"" + ID + "\"}";
        return command;
    }
    
    public static String getUserByMail(String mail, String token)
    {
        String command = commandPrefix + "{\"cmd\":\"getUserByMail\",\"csv\":\"false\",\"param\":\"" + mail +"\",\"user\":\"" + mail + "\",\"token\":\"" + token + "\"}";
        return command;
    }
    
    public static String getEventByID(int ID)
    {
        String command = commandPrefix + "{\"cmd\":\"getEventByID\",\"csv\":\"false\",\"param\":\"" + ID +"\"}";
        return command;        
    }
    
    public static String getLocationByID(int ID)
    {
        String command = commandPrefix + "{\"cmd\":\"getLocationByID\",\"csv\":\"false\",\"param\":\"" + ID +"\"}";
        return command;        
    }
    
    public static String getEventByUser(int ID)
    {
        String command = commandPrefix + "{\"cmd\":\"getEventByUser\",\"csv\":\"false\",\"param\":\"" + ID +"\"}";
        return command;        
    }
    
    public static String getTicketByID(int ID)
    {
        String command = commandPrefix + "{\"cmd\":\"getTicketByID\",\"csv\":\"false\",\"param\":\"" + ID +"\"}";
        return command;        
    }
    
    public static String getTicketByInvitationID(int ID)
    {
        String command = commandPrefix + "{\"cmd\":\"getTicketByInvitationID\",\"csv\":\"false\",\"param\":\"" + ID +"\"}";
        return command;        
    }
    
    public static String tryLogin(String mail, String password)
    {
        String command = commandPrefix + "{\"cmd\":\"login\",\"csv\":\"false\",\"param\":\"" + "" +"\",\"um\":\"" + mail + "\",\"pw\":\"" + password +"\"}";
        return command;        
    }
    
    public static String insertNewUser(String mail, String name, String password)
    {
        String command = commandPrefix + "{\"cmd\":\"insertNewUser\",\"csv\":\"false\",\"param\":\"" + "" + "\",\"un\":\"" + name + "\",\"um\":\"" + mail + "\",\"pw\":\"" + password +"\"}";
        return command;
    }
    
    //owner,name,descr,location
    public static String insertNewEvent(int owner, String name, String descr, int location, String date)
    {
        String command = commandPrefix + "{\"cmd\":\"insertNewEvent\",\"csv\":\"false\",\"param\":\"\",\"owner\":\"" + owner + "\",\"name\":\"" + name + "\",\"descr\":\"" + descr + "\",\"location\":\"" + location + "\",\"date\":\"" + date + "\"}";
        return command;
    }
    
    public static String insertNewInvitation(int relatedEvent, int relatedTicket, int host, int guest)
    {
        String command = commandPrefix + "{\"cmd\":\"insertNewInvitation\",\"csv\":\"false\",\"param\":\"\",\"relatedEvent\":\"" + relatedEvent + "\",\"relatedTicket\":\"" + relatedTicket + "\",\"host\":\"" + host + "\",\"guest\":\"" + guest + "\"}";
        return command;
    }
    
    public static String getInvitationByUserID(int ID)
    {
        String command = commandPrefix + "{\"cmd\":\"getInvitationsByUserID\",\"csv\":\"false\",\"param\":\"" + ID +"\"}";
        return command;        
    }
    
    //$name, $descr, $coordinates, $owner
    public static String insertNewLocation(String name, String descr, String coordinates, int owner)
    {
        String command = commandPrefix + "{\"cmd\":\"insertNewLocation\",\"csv\":\"false\",\"param\":\"\",\"name\":\"" + name + "\",\"descr\":\"" + descr + "\",\"coordinates\":\"" + coordinates + "\",\"owner\":\"" + owner + "\"}";
        return command;
    }
    
    public static String getLocationByUser(int ID)
    {
        String command = commandPrefix + "{\"cmd\":\"getLocationByUser\",\"csv\":\"false\",\"param\":\"" + ID +"\"}";
        return command;        
    }
    
    public static String acceptInvitation(int ID, int eventID, int ownerID)
    {
        String command = commandPrefix + "{\"cmd\":\"acceptInvitation\",\"csv\":\"false\",\"param\":\"" + ID +"\",\"event\":\"" + eventID + "\",\"owner\":\"" + ownerID + "\"}";
        return command;        
    }
    
}

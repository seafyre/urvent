/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import eventplannerappDELETETHISLATER.EventPlannerApp;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;

/**
 *
 * @author Nick
 */
public class Invitation extends Model 
{
    public Invitation(JSONObject json)
    {
        this.id = Integer.valueOf((String)json.get("ID"));
        this.name = (String) json.get("name");
        this.relatedEvent = Integer.valueOf((String)json.get("relatedEvent"));
        if(json.get("relatedTicket") != null)
        {
            this.relatedTicket = Integer.valueOf((String)json.get("relatedTicket"));   
        }
        this.host = Integer.valueOf((String)json.get("host"));
        this.guest = Integer.valueOf((String)json.get("guest"));
        this.accepted = Integer.valueOf((String)json.get("accepted"));
    }
    
    public int relatedEvent;
    public int relatedTicket;
    public int host;
    public int guest;
    public int accepted;
    
    public int getRelatedEvent() 
    {
        return relatedEvent;
    }

    public int getRelatedTicket() 
    {
        return relatedTicket;
    }

    public int getHost() 
    {
        return host;
    }

    public int getGuest() 
    {
        return guest;
    }
    
    public int getAccepted() 
    {
        return accepted;
    }
    
    public boolean accept()
    {
        JSONObject reply = HTTP.get(APICommand.acceptInvitation(id, relatedEvent, EventPlannerApp.app.getActiveUser().getID()));
        Boolean success = (Boolean)reply.get("succ");
        return success;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.json.simple.JSONObject;

/**
 *
 * @author Nick
 */
public class Invitation extends Model 
{
    public Invitation(JSONObject json)
    {
        this.relatedEvent = (int)json.get("relatedEvent");
        this.relatedTicket = (int)json.get("relatedTicket");
        this.host = (int)json.get("host");
        this.guest = (int)json.get("guest");
    }
    
    public int relatedEvent;
    public int relatedTicket;
    public int host;
    public int guest;
    
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
}

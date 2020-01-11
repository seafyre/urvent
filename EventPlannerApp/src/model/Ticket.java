/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.json.simple.JSONObject;

/**
 *
 * @author Nick, Hendrik
 */
public class Ticket extends Model 
{
    private int owner;
    private int event;
    private String code;
    private boolean redeeemed;

    public Ticket(JSONObject json) 
    {
        this.id = Integer.valueOf((String)json.get("ID"));
        this.owner = Integer.valueOf((String)json.get("owner"));
        this.event = Integer.valueOf((String)json.get("event"));
        this.code = (String)json.get("code");
        this.redeeemed = Boolean.getBoolean((String)json.get("redeeemed"));
    }

    public int getOwner() 
    {
        return owner;
    }

    public int getEvent() 
    {
        return event;
    }

    public String getCode() 
    {
        return code;
    }

    public boolean isRedeeemed() 
    {
        return redeeemed;
    }

    public int getId() 
    {
        return id;
    }
    
    
}

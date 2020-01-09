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
public class Event extends Model
{
    private String descr;
    private int location;
    private int owner;
    private User[] invitedUsers;
    
    public Event(JSONObject json)
    {
        this.id = Integer.valueOf((String)json.get("ID"));
        this.name = (String) json.get("name");
        this.descr = (String) json.get("descr");
        this.owner = Integer.valueOf((String)json.get("owner"));
        this.location = Integer.valueOf((String)json.get("location"));
    }
    
    public String getDescr()
    {
        return this.descr;
    }
    
    public int getLocation()
    {
        return this.location;
    }
    
    public int getOwner()
    {
        return this.owner;
    }
    
    
}

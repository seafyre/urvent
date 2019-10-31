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
    public String desc;
    public int location;
    public User[] invitedUsers;
    
    public Event(JSONObject json)
    {
        this.desc = (String) json.get("desc");
        this.location = Integer.valueOf((String)json.get("location"));
    }
    
}

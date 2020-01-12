/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
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
    private String date;
    
    public Event(JSONObject json)
    {
        this.id = Integer.valueOf((String)json.get("ID"));
        this.name = (String) json.get("name");
        this.descr = (String) json.get("descr");
        this.owner = Integer.valueOf((String)json.get("owner"));
        this.location = Integer.valueOf((String)json.get("location"));
        this.date = (String) json.get("date");
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
    
    public String getDate()
    {
        return date;
    }
    
    
}

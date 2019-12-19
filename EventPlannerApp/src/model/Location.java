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
public class Location extends Model 
{
    private String desc;
    private String coordinates;
    private int owner;
    
    public Location(JSONObject json)
    {
        this.id = Integer.valueOf((String)json.get("ID"));
        this.name = (String)json.get("name");
        this.desc = (String) json.get("descr");
        this.owner = Integer.valueOf((String)json.get("owner"));
        this.coordinates = (String) json.get("coordinates");
    }
    
    public String getDescr()
    {
        return desc;
    }
    
    public String getCoordinates()
    {
        return coordinates;
    }
    
    public int getOwner()
    {
        return owner;
    }
}

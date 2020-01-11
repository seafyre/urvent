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
    
    /**
     * 
     * @return array containing lat and long
     */
    public float[] getCoordinates()
    {
        String[] coordSplit = this.coordinates.split(",");
        float[] coords = new float[2];
        coords[0] = Float.valueOf(coordSplit[0]);
        coords[1] = Float.valueOf(coordSplit[1]);
        return coords;
    }
    
    public int getOwner()
    {
        return owner;
    }
}

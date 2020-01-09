/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Hendrik
 */
public class JSON 
{
    private static JSONParser parser = new JSONParser();
    
    public static JSONObject readJSON(String jsonString)
    {
        System.out.println("parsing: " + jsonString);
        JSONObject json = null;
        try 
        {
            json = (JSONObject)parser.parse(jsonString);
        } 
        catch (ParseException ex) 
        {
            ex.printStackTrace();
        }
        return json;
    }
    
    public static JSONArray readJSONArray(String jsonString)
    {
        JSONArray array = null;
        try 
        {
            array = (JSONArray)parser.parse(jsonString);
        } 
        catch (ParseException ex) 
        {
            ex.printStackTrace();
        }
        
        return array;
    }
}

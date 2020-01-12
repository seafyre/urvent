package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.io.FileNotFoundException;

/**
 *
 * @author Nick, Hendrik
 */
public class User extends Model 
{
    private String mail;
    private String loginToken;
    private String descr;

    /**
     * Constructor, creates a new Model from a JSONObject
     * @param json a JSONObject supplied by the HTTP Class 
     */
    public User(JSONObject json)
    {
        id = Integer.valueOf((String) json.get("ID"));
        name = (String) json.get("name");
        descr = (String) json.get("descr");
        mail = (String) json.get("mail");
        loginToken = (String) json.get("loginToken");
        
    }
    
    public String getMail()
    {
        return this.mail;
    }
    
    public String getDescr()
    {
        return this.descr;
    }
    
    public String getloginToken()
    {
        return this.loginToken;
    }

}

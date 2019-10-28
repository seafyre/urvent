package app;

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
    private int eventsHostes[];
    private int eventsInvited[];
    private int socialScore;
    private int ticketsOwned[];

    public String getMail()
    {
        return null;
    }

}
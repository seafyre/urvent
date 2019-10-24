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
 * @author Nick
 */
public class User extends Model {


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

    public String getterTest()
    {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("localhost/urvent/debug.php")) {

            System.out.println("in Reading method...");

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            //System.out.println(jsonObject);

            String name = (String) jsonObject.get("ID");
            System.out.println(name);

            return name;

            //long age = (Long) jsonObject.get("name");
            //System.out.println(age);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "Didn't work! :(";
        
        //2nd version
        public static void main(String[] args) {
                
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader("user.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray userList = (JSONArray) obj;
            System.out.println(userList);
             
            //Iterate over employee array
            userList.forEach( use -> parseUserObject( (JSONObject) use ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
        private static void parseUserObject(JSONObject user)
    {
        //Get object within list
        JSONObject employeeObject = (JSONObject) user.get("id");
         
        //Get name
        String name = (String) employeeObject.get("name");   
        System.out.println(name);
         
        //Get mail
        String mail = (String) employeeObject.get("mail"); 
        System.out.println(mail);
         
        //Get password
        String password = (String) employeeObject.get("password");   
        System.out.println(password);
    }
        //New user
        JSONObject userDetails = new JSONObject();
        employeeDetails.put("id", "2");
        employeeDetails.put("name", "Steven");
        employeeDetails.put("mail", "hi@dumm.com");
    }

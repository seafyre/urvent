/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Hendrik
 */
public class HTTP 
{
    
    public static String API_ADDRESS = "http://localhost/urvent/api.php";
    public static String ADDRESS = "http://localhost/urvent/";

    private static HttpURLConnection getConnection(String URLString)
    {
        HttpURLConnection con = null;
        try
        {
            URL url = new URL(URLString);
            con = (HttpURLConnection) url.openConnection();
        }
        catch (Exception e)
        {
            System.err.print("getConnection threw an error!");
        }
        return con;
    }

    public static String post(String payload)
    {
        HttpURLConnection con = null;
        int contentLength = payload.getBytes().length;
        String response = "NoResponse";
        try
        {
            con = getConnection(API_ADDRESS);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Length", Integer.toString(contentLength));
            con.setRequestProperty("Content-Language","en-US");
            con.setChunkedStreamingMode(0);
            con.setDoOutput(true);

            DataOutputStream dataOutputStream = new DataOutputStream(con.getOutputStream());
            dataOutputStream.writeBytes(payload);
            dataOutputStream.flush();
            dataOutputStream.close();

            InputStream inputStream = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                responseBuilder.append(line);
                System.out.println("reading " + line);
            }
            response = responseBuilder.toString();
            reader.close();
        }
        catch (Exception e)
        {
            System.err.print("post failed!");
        }
        finally
        {
            if (con != null)
            {
                con.disconnect();
            }
        }

        return response;
    }
    
    public static JSONObject get(String payload)
    {
        HttpURLConnection con = null;
        int contentLength = payload.getBytes().length;
        System.out.println(payload);
        String response = "NoResponse";
        try
        {
            con = getConnection(API_ADDRESS);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Length", Integer.toString(contentLength));
            con.setRequestProperty("Content-Language","en-US");
            con.setChunkedStreamingMode(0);
            con.setDoOutput(true);

            DataOutputStream dataOutputStream = new DataOutputStream(con.getOutputStream());
            dataOutputStream.writeBytes(payload);
            dataOutputStream.flush();
            dataOutputStream.close();

            InputStream inputStream = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                responseBuilder.append(line);
                System.out.println("reading " + line);
            }
            response = responseBuilder.toString();
            reader.close();
        }
        catch (Exception e)
        {
            System.err.print("post failed!");
        }
        finally
        {
            if (con != null)
            {
                con.disconnect();
            }
        }
        System.out.println(response);
        JSONObject json = null;
        try
        {
            json = JSON.readJSON(response);    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
        
        return json;
    }
    
    public static ArrayList<JSONObject> getArray(String payload)
    {
        HttpURLConnection con = null;
        int contentLength = payload.getBytes().length;
        System.out.println(payload);
        String response = "NoResponse";
        try
        {
            con = getConnection(API_ADDRESS);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Length", Integer.toString(contentLength));
            con.setRequestProperty("Content-Language","en-US");
            con.setChunkedStreamingMode(0);
            con.setDoOutput(true);

            DataOutputStream dataOutputStream = new DataOutputStream(con.getOutputStream());
            dataOutputStream.writeBytes(payload);
            dataOutputStream.flush();
            dataOutputStream.close();

            InputStream inputStream = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                responseBuilder.append(line);
                System.out.println("reading " + line);
            }
            response = responseBuilder.toString();
            reader.close();
        }
        catch (Exception e)
        {
            System.err.print("post failed!");
        }
        finally
        {
            if (con != null)
            {
                con.disconnect();
            }
        }
        System.out.println(response);
        
        ArrayList<JSONObject> resultList = new ArrayList<JSONObject>();
        JSONArray jsonArr = JSON.readJSONArray(response);
        for(Object n : jsonArr)
        {
            JSONObject jsonObj = JSON.readJSON(n.toString());
            resultList.add(jsonObj);
        }
        
        return resultList;
    }
    
}

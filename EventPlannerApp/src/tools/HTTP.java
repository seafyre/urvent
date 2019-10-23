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

/**
 *
 * @author Hendrik
 */
public class HTTP 
{
    public static String ADDRESS = "http://localhost/urvent/debug.php";

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
            con = getConnection(ADDRESS);
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
    
    //TODO
    public static String get(String payload)
    {
        HttpURLConnection con = null;
        int contentLength = payload.getBytes().length;
        String response = "NoResponse";
        try
        {
            con = getConnection(ADDRESS);
            con.setRequestMethod("GET");
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
}

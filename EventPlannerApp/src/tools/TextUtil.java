/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author Admin
 */
public class TextUtil 
{
    public static String format(String in, int charsPerLine)
    {
        try
        {
            String out = in;
            StringBuilder sb = new StringBuilder(out);
            int pointer = 0;
            for(int n = 0; n < sb.length(); n++)
            {
                pointer++;
                if(pointer == charsPerLine)
                {
                    sb.insert(n, "\n");
                    pointer = 0;
                }
            }

            return sb.toString();   
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}

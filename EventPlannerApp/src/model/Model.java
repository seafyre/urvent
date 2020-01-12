/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Nick, Hendrik
 */
public abstract class Model 
{
    protected int id;
    protected String name;
    
    public String getName()
    {
        return this.name;
    }
    
    public int getID()
    {
        return this.id;
    }
}

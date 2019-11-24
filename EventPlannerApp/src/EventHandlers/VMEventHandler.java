/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventHandlers;

import viewmodel.ViewModel;

/**
 *
 * @author Admin
 */
public abstract class VMEventHandler 
{
    protected ViewModel parent;
    
    public VMEventHandler(ViewModel parent)
    {
        this.parent = parent;
    }
}

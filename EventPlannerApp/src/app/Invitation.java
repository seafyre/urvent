/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author Nick
 */
public class Invitation extends Model {
    public int relatedTicketID;
    public User host;
    public User guest;
}

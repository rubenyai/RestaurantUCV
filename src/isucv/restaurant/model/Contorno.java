/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.model;

/**
 *
 * @author Flemidra
 */
public class Contorno 
{
    private Float Price;
    private Integer Time;
    private Boolean Visible;
    
    public Contorno(Float P, Integer T, Boolean V)
    {
        Price = P;
        Time = T;
        Visible = V;
    }
    
}

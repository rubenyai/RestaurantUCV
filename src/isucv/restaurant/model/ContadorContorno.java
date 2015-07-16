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
public class ContadorContorno 
{
    private Integer Count;
    private Contorno Side;
    
    public ContadorContorno(Contorno Side)
    {
        this.Side = Side;
        Count = 0;
    }
    
    public void AddCount(Integer Count)
    {
        this.Count = Count;
    }
}

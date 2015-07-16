/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.model;

import java.util.ArrayList;

/**
 *
 * @author Flemidra
 */
public class ContadorEspecialidad 
{
    private Especialidad Speciality;
    private Integer Count;
    private ArrayList<ContadorContorno> Sides;
    
    public ContadorEspecialidad(Especialidad Name)
    {
        ArrayList<ContadorContorno> Sides = new ArrayList<>();
        Count = 0;
        Speciality = Name;
    }
    
    public void AddCount(Integer Count)
    {
        this.Count = Count;
    }
    
    public void SetSides(ArrayList<ContadorContorno> Sides)
    {
        this.Sides = (ArrayList<ContadorContorno>) Sides.clone();
    }    
}

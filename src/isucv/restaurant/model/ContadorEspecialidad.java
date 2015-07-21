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
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    public Especialidad Speciality;
    public Integer Count;
    public ArrayList<ContadorContorno> Sides;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public ContadorEspecialidad(Especialidad Name)
    {
        Sides = new ArrayList<>();
        Count = 0;
        Speciality = Name;
    }
    
    public void AddCount(Integer Count)
    {
        this.Count += Count;
    }
    
    public void SetSides(ArrayList<ContadorContorno> Sides)
    {
        this.Sides = Sides;
    }    
}

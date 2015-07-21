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
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    public Float Price;
    //public Integer Time; TIEMPO CONSTANTE!
    public Boolean Visible;
    public String Name;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public Contorno(String N, Float P,/* Integer T,*/ Boolean V)
    {
        Price = P;
        //Time = T;
        Visible = V;
        Name = N;
    }
    
    public Float GetPrice(){
            return this.Price;
    }
    
    public Boolean GetVisible(){
            return this.Visible;
    }
    
    public String GetName(){
            return this.Name;
    }
}

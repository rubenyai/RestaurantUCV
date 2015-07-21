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
public class Pedido 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    public Integer ID;
    private String Status;
    public ArrayList<ContadorEspecialidad> Specialities = new ArrayList<>();
    public ArrayList<ContadorContorno> Sides = new ArrayList<>();
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
        
    public void SetStatus(String Status)
    {
        this.Status = Status;
    }
    
    public int GetID(){
            return this.ID;
    }
    
    public String GetStatus(){
            return this.Status;
    }
    
    public ArrayList<ContadorEspecialidad> GetSpecialities(){
            return this.Specialities;
    }
    
    public ArrayList<ContadorContorno> GetSides(){
            return this.Sides;
    }
 }

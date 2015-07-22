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
    
    private Integer id;
    private String status;
    private ArrayList<ContadorEspecialidad> specialities;
    private ArrayList<ContadorContorno> sides;
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
           
    public int GetId(){
            return this.id;
    }
    
    public String GetStatus(){
            return this.status;
    }
    
    public ArrayList<ContadorEspecialidad> GetSpecialities(){
            return this.specialities;
    }
    
    public ArrayList<ContadorContorno> GetSides(){
            return this.sides;
    }
    
    public void SetId(int idd){
            this.id=idd;
    }
    
    public void SetStatus(String Status)
    {
        this.status = Status;
    }
    
    public void SetSpecialities(ArrayList<ContadorEspecialidad> Spec)
    {
        this.specialities = Spec;
    }
    
    public void SetSides(ArrayList<ContadorContorno> Con)
    {
        this.sides = Con;
    }
 }

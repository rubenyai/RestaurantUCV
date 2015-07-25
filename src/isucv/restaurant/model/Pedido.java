/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public class Pedido 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private Integer id;
    private String status;
    private ArrayList<ContadorEspecialidad> specialities = new ArrayList<>();
    private ArrayList<ContadorContorno> sides = new ArrayList<>();
    
    
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
    
    public void SetStatus(String stat)
    {
        this.status = stat;
    }
    
    public void SetSpecialities(ArrayList<ContadorEspecialidad> spec)
    {
        this.specialities = spec;
    }
    
    public void SetSides(ArrayList<ContadorContorno> con)
    {
        this.sides = con;
    }
 }

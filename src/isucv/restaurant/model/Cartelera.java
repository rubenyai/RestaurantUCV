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
public class Cartelera 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    public ArrayList<Especialidad> Specialities;
    public ArrayList<Contorno> Sides;
    public Integer TotalSpecialities;
    public Integer TotalSides;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public void UpdateSpecialities(ArrayList<Especialidad> Esp)
    {
        //Se actualizan las especialidades pasandole por parametro un ArrayList con las especialidades completas
        Specialities = Esp;
        //Se totaliza el numero de especialidades dentro de la cartelera
        TotalSpecialities = Specialities.size();
    }
   
    public void UpdateSides(ArrayList<Contorno> Con)
    {
        //Se actualizan los contornos pasandole por parametro un ArrayList con los contornos completos
        Sides = Con;
        //Se totaliza el numero de contornos dentro de la cartelera
        TotalSides = Sides.size();
    }
    
    public ArrayList<Especialidad> GetSpecialities(){
            return this.Specialities;
    }
    
    public ArrayList<Especialidad> GetSides(){
            return this.Specialities;
    }
        
    public int GetTotalSpecialities(){
            return this.TotalSpecialities;
    }
    
    public int GetTotalSides(){
            return this.TotalSides;
    }
}

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
    
    private ArrayList<Especialidad> Speciality;
    private ArrayList<Contorno> Sides;
    private Integer TotalSpecialities;
    private Integer TotalSides;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public void UpdateSpecialities(ArrayList<Especialidad> Esp)
    {
        //Se actualizan las especialidades pasandole por parametro un ArrayList con las especialidades completas
        Speciality = Esp;
        //Se totaliza el numero de especialidades dentro de la cartelera
        TotalSpecialities = Speciality.size();
    }
   
    public void UpdateSides(ArrayList<Contorno> Con)
    {
        //Se actualizan los contornos pasandole por parametro un ArrayList con los contornos completos
        Sides = Con;
        //Se totaliza el numero de contornos dentro de la cartelera
        TotalSides = Sides.size();
    }
    
}

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
public class Cartelera 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private ArrayList<Especialidad> specialities;
    private ArrayList<Contorno> sides;
    private Integer totalspecialities;
    private Integer totalsides;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public void UpdateSpecialities(ArrayList<Especialidad> esp)
    {
        //Se actualizan las especialidades pasandole por parametro un ArrayList con las especialidades completas
        specialities = esp;
        //Se totaliza el numero de especialidades dentro de la cartelera
        totalspecialities = specialities.size();
    }
   
    public void UpdateSides(ArrayList<Contorno> con)
    {
        //Se actualizan los contornos pasandole por parametro un ArrayList con los contornos completos
        sides = con;
        //Se totaliza el numero de contornos dentro de la cartelera
        totalsides = sides.size();
    }
    
    public ArrayList<Especialidad> GetSpecialities(){
            return this.specialities;
    }
    
    public ArrayList<Contorno> GetSides(){
            return this.sides;
    }
        
    public int GetTotalSpecialities(){
            return this.totalspecialities;
    }
    
    public int GetTotalSides(){
            return this.totalsides;
    }
    
    public void SetSpecialities(ArrayList<Especialidad> spec){
            this.specialities=spec;
    }
    
    public void SetSides(ArrayList<Contorno> con){
            this.sides=con;
    }
    
    public void SetTotalSpecialities(int totalspe){
            this.totalspecialities=totalspe;
    }
    
    public void SetTotalSides(int totalcon){
            this.totalsides=totalcon;
    }
}

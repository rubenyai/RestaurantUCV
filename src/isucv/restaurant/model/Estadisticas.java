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
public class Estadisticas 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    public ArrayList<ContadorEspecialidad> TopSpecialities;
    public ArrayList<ContadorContorno> TopSides;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public void Reset()
    {   
        //mientras se implementa los add dejar este codigo para q no explote
        TopSpecialities=new ArrayList <ContadorEspecialidad> ();
        TopSides=new ArrayList <ContadorContorno> ();
        //borrar estas dos lineas al implementar los add
        
        if(TopSpecialities.isEmpty()==false)
        {
            TopSpecialities.clear();
        }
        if(TopSides.isEmpty()==false)
        {
             TopSides.clear();
        }
    }
    
    public void AddSpeciality(Especialidad Speciality, Integer Count)
    {
        
    }
    
    public void AddSides(Contorno Sides, Integer Count)
    {
        
    }
}

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
public class Estadisticas 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private ArrayList<ContadorEspecialidad> topspecialities= new ArrayList<>();
    private ArrayList<ContadorContorno> topsides= new ArrayList<>();
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public void Reset()
    {   
        topspecialities=new ArrayList<ContadorEspecialidad> ();
        topsides=new ArrayList<ContadorContorno> ();
    }
    
    public void AddSpeciality(Especialidad Speciality, Integer Count)
    {
        ContadorEspecialidad contesp;
        //Creamos el objeto
        contesp = new ContadorEspecialidad(Speciality);
        //Introducimos el count
        contesp.AddCount(Count);
        //Introducimos los contornos del mismo
    }
    
    public void AddSides(Contorno Sides, Integer Count)
    {
        ContadorContorno contcon;
        //Creamos el objeto
        contcon = new ContadorContorno(Sides);
        //Introducimos el count
        contcon.AddCount(Count);
    }
    
    public ArrayList<ContadorEspecialidad> GetTopSpecialities(){
            return this.topspecialities;
    }
    
    public ArrayList<ContadorContorno> GetTopSides(){
            return this.topsides;
    }
}

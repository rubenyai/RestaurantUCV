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
public class ContadorEspecialidad 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private Especialidad speciality;
    private Integer count;
    private ArrayList<ContadorContorno> sides;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public ContadorEspecialidad(Especialidad Name)
    {
        sides = new ArrayList<>();
        count = 0;
        speciality = Name;
    }
    
    public void AddCount(Integer Count)
    {
        this.count += Count;
    }
    
    public void SetSides(ArrayList<ContadorContorno> Sides)
    {
        this.sides = Sides;
    }
    
    public Especialidad GetSpeciality(){
            return this.speciality;
    }
    
    public int GetCount(){
            return this.count;
    }
    
    public ArrayList<ContadorContorno> GetSides(){
            return this.sides;
    }
    
    public void SetCount(int cou){
            count=cou;
    }
}

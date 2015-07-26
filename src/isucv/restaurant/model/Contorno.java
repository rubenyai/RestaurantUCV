/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isucv.restaurant.model;

/**
 *
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */
public class Contorno 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private Float price;
    //public Integer Time; TIEMPO CONSTANTE!
    private Boolean visible;
    private String name;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public Contorno(String N, Float P,/* Integer T,*/ Boolean V)
    {
        price = P;
        //Time = T;
        visible = V;
        name = N;
        
        if (price == null)
            price = 0.0f;
        
        if (visible == null)
            visible = false;
    }
    
    public Float GetPrice(){
            return this.price;
    }
    
    public Boolean GetVisible(){
            return this.visible;
    }
    
    public String GetName(){
            return this.name;
    }
    
    public void SetPrice(Float pr){
            price=pr;
    }
            
    public void SetVisible(Boolean vis){
            visible=vis;
    }
    
    public void SetName(String nam){
            name=nam;
    }
}

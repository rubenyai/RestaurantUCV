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
public class Especialidad 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private Float price;
    private Integer totalSides;
    private Integer time;
    private Boolean visible;
    private String name;
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public Especialidad (String N, Float P, Integer S, Integer T, Boolean V)
    {
        price = P;
        totalSides = S;
        time = T;
        visible = V;
        name = N;
        
        if (price == null)
            price = 0.0f;
        
        if (totalSides == null)
            totalSides = 0;
        
        if (time == null)
            time = 0;
        
        if (visible == null)
            visible = false;
    }
    public Float GetPrice(){
            return this.price;
    }
    
    public int GetTotalSides(){
            return this.totalSides;
    }
    
    public int GetTime(){
            return this.time;
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
    
    public void SetTotalSides(int totalsid){
            totalSides=totalsid;
    }
    
    public void SetTime(int tim){
            time=tim;
    }
    
    public void SetVisible(Boolean vis){
            visible=vis;
    }
    
    public void SetName(String nam){
            name=nam;
    }
}

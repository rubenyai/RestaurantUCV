/*
 * Copyright (C) 2015
 *  Fabian Ramos
 *  Ruben Maza
 *  David Contreras
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package isucv.restaurant.model;

/**
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
    
    
    
    /*////////////////////////////////
    //    GET / SETS ELEMENTALES    //
    */////////////////////////////////
    
    public Float GetPrice() { return this.price; }
    public int GetTotalSides() { return this.totalSides; }
    public int GetTime() { return this.time; }
    public Boolean GetVisible() { return this.visible; }
    public String GetName() { return this.name; }
    public void SetPrice(Float pr) { price=pr; }
    public void SetTotalSides(int totalsid) { totalSides=totalsid; }
    public void SetTime(int tim) { time=tim; }
    public void SetVisible(Boolean vis) { visible=vis; }
    public void SetName(String nam) { name=nam; }
    
    
    
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
}

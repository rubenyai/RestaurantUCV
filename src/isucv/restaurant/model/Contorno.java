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

public class Contorno 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private Float price;
    private Boolean visible;
    private String name;
    
    
    
    /*////////////////////////////////
    //    GET / SETS ELEMENTALES    //
    */////////////////////////////////
    
    public Float GetPrice() { return this.price; }
    public void SetPrice(Float pr) { price=pr; }     
    public Boolean GetVisible() { return this.visible; }
    public void SetVisible(Boolean vis) { visible=vis; }
    public String GetName() { return this.name; }
    public void SetName(String nam) { name=nam; }
    
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public Contorno(String N, Float P, Boolean V)
    {
        price = P;
        visible = V;
        name = N;
        
        if (price == null)
            price = 0.0f;
        
        if (visible == null)
            visible = false;
    }
}

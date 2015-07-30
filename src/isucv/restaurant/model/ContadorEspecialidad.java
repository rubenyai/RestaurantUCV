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

import java.util.ArrayList;

/**
 * @author Equipo Ingenieria de Software <David Contreras, Fabian Ramos, Ruben Maza>
 */

public class ContadorEspecialidad 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private final Especialidad speciality;
    private Integer count;
    private ArrayList<ContadorContorno> sides;
    
    
    
    /*////////////////////////////////
    //    GET / SETS ELEMENTALES    //
    */////////////////////////////////
    
    public Especialidad GetSpeciality() { return this.speciality; }
    public ArrayList<ContadorContorno> GetSides() { return this.sides; }
    public void SetSides(ArrayList<ContadorContorno> Sides) { this.sides = Sides; }
    public int GetCount() { return this.count; }
    public void SetCount(int cou) { count=cou; }
    
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public ContadorEspecialidad(Especialidad Name)
    {
        sides = new ArrayList<>();
        count = 0;
        speciality = Name;
    }
    
    public void AddCount(Integer Count) { this.count += Count; }
}

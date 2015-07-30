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

public class Pedido 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private Integer id;
    private String status;
    private ArrayList<ContadorEspecialidad> specialities = new ArrayList<>();
    private ArrayList<ContadorContorno> sides = new ArrayList<>();
    
    
    
    /*////////////////////////////////
    //    GET / SETS ELEMENTALES    //
    */////////////////////////////////
           
    public int GetId() { return this.id; }
    public String GetStatus() { return this.status; }
    public ArrayList<ContadorEspecialidad> GetSpecialities() { return this.specialities; }
    public ArrayList<ContadorContorno> GetSides() { return this.sides; }
    public void SetId(int idd) { this.id=idd; }
    public void SetStatus(String stat) { this.status = stat; }
    public void SetSpecialities(ArrayList<ContadorEspecialidad> spec) { this.specialities = spec; }
    public void SetSides(ArrayList<ContadorContorno> con) { this.sides = con; }
 }

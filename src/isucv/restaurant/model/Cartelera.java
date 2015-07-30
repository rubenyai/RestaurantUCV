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


/*
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
    
    
    
    /*////////////////////////////////
    //    GET / SETS ELEMENTALES    //
    */////////////////////////////////
    
    public ArrayList<Especialidad> GetSpecialities() { return this.specialities; }
    public ArrayList<Contorno> GetSides() { return this.sides; }  
    public int GetTotalSpecialities() { return this.totalspecialities; }
    public int GetTotalSides() { return this.totalsides; }
    public void SetSpecialities(ArrayList<Especialidad> spec) { this.specialities=spec; }
    public void SetSides(ArrayList<Contorno> con) { this.sides=con; }
    public void SetTotalSpecialities(int totalspe) { this.totalspecialities=totalspe; }
    public void SetTotalSides(int totalcon) { this.totalsides=totalcon; }
    
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    // Actualizar Especialidades
    public void UpdateSpecialities(ArrayList<Especialidad> esp)
    {
        //Se actualizan las especialidades pasandole por parametro un ArrayList con las especialidades completas
        specialities = esp;
        //Se totaliza el numero de especialidades dentro de la cartelera
        totalspecialities = specialities.size();
    }
   
    // Actualizar Contornos
    public void UpdateSides(ArrayList<Contorno> con)
    {
        //Se actualizan los contornos pasandole por parametro un ArrayList con los contornos completos
        sides = con;
        //Se totaliza el numero de contornos dentro de la cartelera
        totalsides = sides.size();
    }
}

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

public class Estadisticas 
{
    /*///////////////////////////
    //    ATRIBUTOS INTERNOS   //
    *////////////////////////////
    
    private ArrayList<ContadorEspecialidad> topSpecialities = new ArrayList<>();
    private ArrayList<ContadorContorno> topSides = new ArrayList<>();
    
    
    
    /*////////////////////////////////
    //    GET / SETS ELEMENTALES    //
    */////////////////////////////////
    
    public ArrayList<ContadorEspecialidad> GetTopSpecialities() { return this.topSpecialities; }
    public ArrayList<ContadorContorno> GetTopSides() { return this.topSides; }
    
    
    
    /*//////////////
    //   METODOS  //
    *///////////////
    
    public void Reset()
    {   
        topSpecialities=new ArrayList<> ();
        topSides=new ArrayList<> ();
    }
    
    // Agrega una Especialidad a las Estadisticas
    public void AddSpeciality(Especialidad Speciality, Integer Count)
    {
        ContadorEspecialidad contesp;
        //Creamos el objeto
        contesp = new ContadorEspecialidad(Speciality);
        //Introducimos el count
        contesp.AddCount(Count);
        //Introducimos los contornos del mismo
        topSpecialities.add(contesp);
    }
     // Agrega un Contorno a las Estadisticas
    public void AddSides(Contorno Sides, Integer Count)
    {
        ContadorContorno contcon;
        //Creamos el objeto
        contcon = new ContadorContorno(Sides);
        //Introducimos el count
        contcon.AddCount(Count);
        //Introducimos los contornos
        topSides.add(contcon);
    }
}
